package sparta.AIBusinessProject.domain.review.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.AIBusinessProject.domain.review.entity.ReviewRatingTypeEnum;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDto {

    private UUID userId;
    private UUID orderId;
    private UUID storeId;
    private String content;
    private ReviewRatingTypeEnum rating;

}
