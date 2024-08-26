package sparta.AIBusinessProject.domain.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.AIBusinessProject.domain.category.dto.CategoryResponse;
import sparta.AIBusinessProject.domain.category.dto.CreateCategoryRequest;
import sparta.AIBusinessProject.domain.category.repository.CategoryRepository;
import sparta.AIBusinessProject.domain.category.entity.Category;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    // 카테고리 목록 조회 비즈니스 로직
    public List<CategoryResponse> getCategories(){
        return categoryRepository.findAll().stream()
                .map(CategoryResponse::of)
                .collect(Collectors.toList());
    }

    // 카테고리 추가 비즈니스 로직
    public void createCategory(final CreateCategoryRequest request){
        categoryRepository.save(Category.create(request.getName()));
    }

}
