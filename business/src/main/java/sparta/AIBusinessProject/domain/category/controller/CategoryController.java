package sparta.AIBusinessProject.domain.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.AIBusinessProject.domain.category.dto.CreateCategoryRequest;
import sparta.AIBusinessProject.domain.category.dto.CategoryResponse;
import sparta.AIBusinessProject.domain.category.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // 카테고리 목록 조회
    @GetMapping
    public ResponseEntity<List<CategoryResponse>>getCategories(){
        return ResponseEntity.ok(categoryService.getCategories());
    }

    // 카테고리 추가
    @PostMapping
    public Boolean createCategory(
            final @RequestBody CreateCategoryRequest request
    ){
        categoryService.createCategory(request);
        return true;
    }

}
