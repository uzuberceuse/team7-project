package sparta.AIBusinessProject.domain.review.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import sparta.AIBusinessProject.domain.review.dto.ReviewDto;
import sparta.AIBusinessProject.domain.review.service.ReviewService;

import java.util.UUID;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


        /* 리뷰 등록
           등록 결과 확인
        */
        @PostMapping
        public ReviewDto createReview(@RequestBody ReviewDto reviewDto,
                                      @RequestHeader(value = "X-User-Id") String user_id){

            return reviewService.createReview(reviewDto, user_id);
        }

        /* 리뷰 수정
           수정 결과 확인
        */
        @PatchMapping("/{review_id}")
        public ReviewDto updateReview(@RequestBody ReviewDto reviewDto,
                                      @RequestHeader(value = "X-User-Id") String user_id,
                                      @PathVariable UUID review_id) {

            return reviewService.updateReview(reviewDto, review_id, user_id);
        }

        /* 리뷰 삭제
           삭제 결과는 T/F
        */
        @DeleteMapping("/{review_id")
        public Boolean deleteProduct(@RequestHeader(value = "X-User-Id") String user_id,
                                     @PathVariable UUID review_id) {

            return reviewService.deleteReview(review_id, user_id);
        }

        // 리뷰 목록 조회
        @GetMapping
        public Page<ReviewDto> getReviews(ReviewDto reviewDto, Pageable pageable) {

            return reviewService.getReviews(reviewDto, pageable);
        }
}
