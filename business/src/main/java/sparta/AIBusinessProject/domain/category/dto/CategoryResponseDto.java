package sparta.AIBusinessProject.domain.category.dto;

import lombok.*;
import sparta.AIBusinessProject.domain.category.entity.Category;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access= AccessLevel.PRIVATE)
public class CategoryResponseDto {
    private String name;

    public static CategoryResponseDto of(final Category category) {
        return CategoryResponseDto.builder()
                .name(category.getCategoryName())
                .build();
    }
}
