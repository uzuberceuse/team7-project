package sparta.AIBusinessProject.domain.review.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private String user_id;
    private String order_id;
    private String store_id;
    private String content;
    private Integer rating;
}
