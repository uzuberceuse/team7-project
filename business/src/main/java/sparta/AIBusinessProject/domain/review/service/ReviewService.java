package sparta.AIBusinessProject.domain.review.service;

import org.springframework.stereotype.Service;
import sparta.AIBusinessProject.domain.review.dto.ReviewDto;
import sparta.AIBusinessProject.domain.review.repository.ReviewRepository;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    // 리뷰 등록
    public ReviewDto createReview(ReviewDto reviewDto, String user_id) {
        try {
            return ;
        } catch (Exception e) {
            return null;
        }
    }

    // 리뷰 수정
    public ReviewDto updateReview(ReviewDto reviewDto, String user_id) {
        try {
            return ;
        } catch (Exception e) {
            return null;
        }
    }

    // 리뷰 삭제
    public boolean deleteReview(String review_id, String user_id) {
        try {

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 리뷰 조회
    public ReviewDto getReview() {
        return ;
    }
}
