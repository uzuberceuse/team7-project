package sparta.AIBusinessProject.domain.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sparta.AIBusinessProject.domain.review.dto.ReviewDto;

public interface ReviewRepositoryCustom {

    // 리뷰 조회 시 페이징 적용
    Page<ReviewDto> getReviews(ReviewDto reviewDto, Pageable pageable);
}
