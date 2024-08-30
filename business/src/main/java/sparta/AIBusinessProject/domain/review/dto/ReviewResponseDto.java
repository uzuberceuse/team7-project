package sparta.AIBusinessProject.domain.review.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.AIBusinessProject.domain.review.entity.Review;
import sparta.AIBusinessProject.domain.review.entity.ReviewRatingTypeEnum;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {

    private UUID userId;
    private UUID storeId;
    private String content;
    private ReviewRatingTypeEnum rating;

    public static ReviewResponseDto toResponseDto(Review review) {
        return new ReviewResponseDto(
                review.getUser().getUser_id(),
                review.getStore().getStoreId(),
                review.getContent(),
                review.getRating()
        );
    }
}
