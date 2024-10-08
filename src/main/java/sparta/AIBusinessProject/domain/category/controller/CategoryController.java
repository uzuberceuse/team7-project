package sparta.AIBusinessProject.domain.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_OWNER') or hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<List<CategoryResponseDto>>getCategories(){
        List<CategoryResponseDto> categories=categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }

    // 카테고리 추가
    @PostMapping
    @Secured({"ROLE_MANAGER"})
    public ResponseEntity<CategoryResponseDto> createCategory(
            final @RequestBody CreateCategoryRequestDto request,
            @RequestParam String username
    ){
        CategoryResponseDto category=categoryService.createCategory(request,username);
        return ResponseEntity.ok(category);
    }

    // 카테고리 수정
    @Secured({"ROLE_MANAGER"})
    @PatchMapping("/{category_id}")
    public ResponseEntity<Void> updateCategory(
            @PathVariable UUID category_id,
            @RequestBody CreateCategoryRequestDto request,
            @RequestParam String username
    ){
        categoryService.updateCategory(category_id,request,username);
        return ResponseEntity.noContent().build();
    }

    // 카테고리 삭제
    @Secured({"ROLE_MANAGER"})
    @DeleteMapping("/{category_id}/delete")
    public ResponseEntity<String> deleteOrder(@PathVariable UUID category_id, @RequestParam String username) {
        categoryService.deleteCategory(category_id,username);
        return ResponseEntity.ok("Category deleted successfully.");
    }
}
