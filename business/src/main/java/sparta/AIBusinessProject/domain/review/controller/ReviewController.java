package sparta.AIBusinessProject.domain.review.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.AIBusinessProject.domain.product.dto.ProductRequestDto;
import sparta.AIBusinessProject.domain.review.dto.ReviewDto;
import sparta.AIBusinessProject.domain.review.service.ReviewService;

import java.util.List;

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
                                      @RequestHeader String user_id) {
            return ;
        }


        /* 리뷰 수정
           수정 결과 확인
        */
        @PatchMapping("/{review_id}")
        public ReviewDto updateReview(@PathVariable String review_id,
                                      @RequestBody ReviewDto reviewDto,
                                      @RequestHeader String user_id) {
            return ;
        }

        /* 리뷰 삭제
           삭제 결과는 T/F
        */
        @DeleteMapping("/{review_id")
        public Boolean deleteProduct(@PathVariable String review_id,
                                     @RequestBody ProductRequestDto productRequestDto,
                                     @RequestHeader String user_id) {
            return ;
        }


        // 리뷰 목록 조회
        @GetMapping
        public ResponseEntity<List<ReviewDto>> getReviews() {
            return ;
        }
}
