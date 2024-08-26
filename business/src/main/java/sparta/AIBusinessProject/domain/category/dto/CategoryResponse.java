package sparta.AIBusinessProject.domain.category.dto;

import lombok.*;
import sparta.AIBusinessProject.domain.category.entity.Category;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access= AccessLevel.PRIVATE)
public class CategoryResponse {
    private String name;

    public static CategoryResponse of(final Category category) {
        return CategoryResponse.builder()
                .name(category.getCategoryName())
                .build();
    }
}
