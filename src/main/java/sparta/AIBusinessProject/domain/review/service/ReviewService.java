package sparta.AIBusinessProject.domain.review.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import sparta.AIBusinessProject.domain.order.entity.Order;
import sparta.AIBusinessProject.domain.order.repository.OrderRepository;
import sparta.AIBusinessProject.domain.review.dto.ReviewRequestDto;
import sparta.AIBusinessProject.domain.review.dto.ReviewResponseDto;
import sparta.AIBusinessProject.domain.review.entity.Review;
import sparta.AIBusinessProject.domain.review.repository.ReviewRepository;
import sparta.AIBusinessProject.domain.store.entity.Store;
import sparta.AIBusinessProject.domain.store.repository.StoreRepository;
import sparta.AIBusinessProject.domain.user.entity.User;
import sparta.AIBusinessProject.domain.user.repository.UserRepository;

import java.util.UUID;

@Service
public class ReviewService {


    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository,
                         OrderRepository orderRepository, StoreRepository storeRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.storeRepository = storeRepository;
    }


    // 리뷰 등록
    @Transactional
    public ReviewResponseDto createReview(ReviewRequestDto requestDto) {

            User user = userRepository.findById(requestDto.getUserId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

            Order order = orderRepository.findById(requestDto.getOrderId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));

            Store store = storeRepository.findById(requestDto.getStoreId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found"));

            Review review = Review.createReview(requestDto, user, order, store);

            return ReviewResponseDto.toResponseDto(reviewRepository.save(review));

    }

    // 리뷰 수정
    @Transactional
    public ReviewResponseDto updateReview(ReviewRequestDto requestDto, UUID reviewId) {
            Review review = reviewRepository.findById(reviewId)
                    .filter(p -> p.getUpdated_at() == null)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 리뷰를 찾을 수 없거나 이미 삭제된 상태입니다."));

            review.updateReview(review.getContent(), review.getRating(), String.valueOf(review.getUser().getUser_id()));

            return ReviewResponseDto.toResponseDto(review);

    }

    // 리뷰 삭제
    // 실제로 DB에서 삭제하는 것이 아닌 삭제 필드에 데이터가 들어가면 삭제라고 판단
    // 조회 시 시간값이 있다면 삭제된 것으로 판단하겠음
    @Transactional
    public Boolean deleteReview(UUID reviewId, String userId) {
        try {
            Review review = reviewRepository.findById(reviewId)
                    .filter(p -> p.getDeleted_at() == null)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 리뷰를 찾을 수 없거나 이미 삭제된 상태입니다."));

            review.deleteReview(userId);
            return true;
        } catch (Exception e) { return false; }
    }

    // 리뷰 조회
    public Page<ReviewResponseDto> getReviews(UUID storeId, int page, int size, String sortBy, boolean isAsc){

        // 페이지 정렬
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);

        // 페이징 처리
        Pageable pageable = PageRequest.of(page, size, sort);

        return reviewRepository.findAllByStore_StoreId(storeId, pageable).map(ReviewResponseDto::toResponseDto);
    }

    public Page<ReviewResponseDto> getMyReviews(User user, int page, int size, String sortBy, boolean isAsc) {

        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);

        // 페이징 처리
        Pageable pageable = PageRequest.of(page, size, sort);

        return reviewRepository.findAllByUser(user, pageable).map(ReviewResponseDto::toResponseDto);
    }

}
