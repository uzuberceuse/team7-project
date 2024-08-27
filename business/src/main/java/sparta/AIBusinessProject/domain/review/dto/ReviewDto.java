package sparta.AIBusinessProject.domain.review.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.AIBusinessProject.domain.review.entity.ReviewRatingTypeEnum;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private UUID user_id;
    private UUID order_id;
    private UUID store_id;
    private String content;
    private ReviewRatingTypeEnum rating;
}
