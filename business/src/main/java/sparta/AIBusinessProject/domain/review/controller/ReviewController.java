package sparta.AIBusinessProject.domain.review.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sparta.AIBusinessProject.domain.review.dto.ReviewRequestDto;
import sparta.AIBusinessProject.domain.review.dto.ReviewResponseDto;
import sparta.AIBusinessProject.domain.review.service.ReviewService;
import sparta.AIBusinessProject.global.security.UserDetailsImpl;

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
        public ReviewResponseDto createReview(@RequestBody ReviewRequestDto requestDto,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails){
            // MASTER, CUSTOMER 권한을 가져야만 create 가능
            if(!"ROLE_CUSTOMER".equals(userDetails.getUser().getRole()) &&
                !"ROLE_MASTER".equals(userDetails.getUser().getRole())) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "접근이 허용되지 않습니다.");
            }

            return reviewService.createReview(requestDto);
        }

        /* 리뷰 수정
           수정 결과 확인
        */
        @PatchMapping("/{review_id}")
        public ReviewResponseDto updateReview(@RequestBody ReviewRequestDto requestDto,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails,
                                             @PathVariable UUID reviewId) {

            // MASTER, CUSTOMER 권한을 가져야만 update 가능
            if(!"ROLE_CUSTOMER".equals(userDetails.getUser().getRole()) &&
                    !"ROLE_MASTER".equals(userDetails.getUser().getRole())) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "접근이 허용되지 않습니다.");
            }

            if(!userDetails.getUser().getUserId().equals(requestDto.getUserId()) ||
                "ROLE_MASTER".equals(userDetails.getUser().getRole()) ) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "해당 리뷰 수정 권한이 없습니다.");
            }

            return reviewService.updateReview(requestDto, reviewId);
        }

        /* 리뷰 삭제
           삭제 결과는 T/F
        */
        @DeleteMapping("/{review_id")
        public Boolean deleteProduct(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                     @PathVariable UUID reviewId) {

            // MASTER, CUSTOMER 권한을 가져야만 delete 가능
            if(!"ROLE_CUSTOMER".equals(userDetails.getUser().getRole()) &&
                    !"ROLE_MASTER".equals(userDetails.getUser().getRole())) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "접근이 허용되지 않습니다.");
            }

            if(!userDetails.getUser().getUserId().equals(userDetails.getUser().getUserId()) ||
                    "ROLE_MASTER".equals(userDetails.getUser().getRole()) ) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "해당 리뷰 삭제 권한이 없습니다.");
            }

            return reviewService.deleteReview(review_id, String.valueOf(userDetails.getUser().getUserId()));

        }

        // 리뷰 목록 조회
        @GetMapping
        public Page<ReviewResponseDto> getReviews(ReviewResponseDto responseDto, Pageable pageable) {

            return reviewService.getReviews(responseDto, pageable);
        }
}
