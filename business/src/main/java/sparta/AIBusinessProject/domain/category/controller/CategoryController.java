package sparta.AIBusinessProject.domain.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.AIBusinessProject.domain.category.dto.CreateCategoryRequestDto;
import sparta.AIBusinessProject.domain.category.dto.CategoryResponseDto;
import sparta.AIBusinessProject.domain.category.service.CategoryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // 카테고리 목록 조회
    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>>getCategories(){
        List<CategoryResponseDto> categories=categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }

    // 카테고리 추가
    @PostMapping
    public ResponseEntity<Void> createCategory(
            final @RequestBody CreateCategoryRequestDto request
    ){
        categoryService.createCategory(request);
        return ResponseEntity.noContent().build();
    }

    // 카테고리 수정
    @PatchMapping("/{categoryId}")
    public ResponseEntity<Void> updateCategory(@PathVariable Long categoryId, @RequestBody CreateCategoryRequestDto request){
        categoryService.updateCategory(categoryId,request);
        return ResponseEntity.noContent().build();
    }

    // 카테고리 삭제
    // put? delete?
    @PutMapping("/{categoryId}/delete")
    public ResponseEntity<String> deleteOrder(@PathVariable UUID categoryId, @RequestParam String userId) {
        categoryService.deleteCategory(categoryId,userId);
        return ResponseEntity.ok("Category deleted successfully.");
    }
}
