package sparta.AIBusinessProject.domain.review.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import sparta.AIBusinessProject.domain.review.dto.ReviewDto;
import sparta.AIBusinessProject.domain.review.entity.Review;
import sparta.AIBusinessProject.domain.review.repository.ReviewRepository;

import java.util.UUID;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    // 리뷰 등록
    @Transactional
    public ReviewDto createReview(ReviewDto reviewDto, String user_id) {
            Review review = Review.createReview(reviewDto, user_id);
            Review createdReview = reviewRepository.save(review);

            return toResponseDto(createdReview);

    }

    // 리뷰 수정
    @Transactional
    public ReviewDto updateReview(ReviewDto reviewDto, UUID review_id, String user_id) {
            Review review = reviewRepository.findById(review_id)
                    .filter(p -> p.getUpdated_at() == null)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 리뷰를 찾을 수 없거나 이미 삭제된 상태입니다."));

            review.updateReview(reviewDto.getContent(), reviewDto.getRating(), user_id);
            Review updatedReview = reviewRepository.save(review);

            return toResponseDto(updatedReview);

    }

    // 리뷰 삭제
    // 실제로 DB에서 삭제하는 것이 아닌 삭제 필드에 데이터가 들어가면 삭제라고 판단
    // 조회 시 시간값이 있다면 삭제된 것으로 판단하겠음
    @Transactional
    public Boolean deleteReview(UUID review_id, String user_id) {
        try {
            Review review = reviewRepository.findById(review_id)
                    .filter(p -> p.getDeleted_at() == null)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 리뷰를 찾을 수 없거나 이미 삭제된 상태입니다."));

            review.deleteReview(user_id);
            reviewRepository.save(review);
            return true;
        } catch (Exception e) { return false; }
    }

    // 리뷰 조회
    public Page<ReviewDto> getReviews(ReviewDto reviewDto, Pageable pageable) {
        return reviewRepository.getReviews(reviewDto, pageable);
    }

    // ReviewDTO 변환 메서드
    public ReviewDto toResponseDto(Review review){
        return new ReviewDto(
                review.getUser_id(),
                review.getOrder_id(),
                review.getStore_id(),
                review.getContent(),
                review.getRating()
        );
    }
}
