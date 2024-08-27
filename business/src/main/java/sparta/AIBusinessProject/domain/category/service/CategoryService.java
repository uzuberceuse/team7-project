package sparta.AIBusinessProject.domain.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sparta.AIBusinessProject.domain.category.dto.CategoryResponseDto;
import sparta.AIBusinessProject.domain.category.dto.CreateCategoryRequestDto;
import sparta.AIBusinessProject.domain.category.repository.CategoryRepository;
import sparta.AIBusinessProject.domain.category.entity.Category;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    // 카테고리 목록 조회 비즈니스 로직
    public List<CategoryResponseDto> getCategories(){
        return categoryRepository.findAll().stream()
                .map(CategoryResponseDto::of)
                .collect(Collectors.toList());
    }

    // 카테고리 생성 비즈니스 로직
    public void createCategory(CreateCategoryRequestDto request) {

    }

    // 카테고리 수정 비즈니스 로직
    public void updateCategory(Long categoryId, CreateCategoryRequestDto request) {
    }

    // 카테고리 삭제 비즈니스 로직
    public void deleteCategory(UUID categoryId,String deletedBy) {
        Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()->new IllegalArgumentException("Category Not Found"));

        category.chanageDeleted(deletedBy);
        categoryRepository.save(category);
    }
}
