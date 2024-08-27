package sparta.AIBusinessProject.domain.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.AIBusinessProject.domain.category.dto.CreateCategoryRequestDto;
import sparta.AIBusinessProject.domain.category.dto.CategoryResponseDto;
import sparta.AIBusinessProject.domain.category.service.CategoryService;
import sparta.AIBusinessProject.domain.product.dto.ProductResponseDto;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // 카테고리 목록 조회
    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>>getCategories(){
        return ResponseEntity.ok(categoryService.getCategories());
    }

    // 카테고리 추가
    @PostMapping
    public Boolean createCategory(
            final @RequestBody CreateCategoryRequestDto request
    ){
        categoryService.createCategory(request);
        return true;
    }

    // 카테고리 수정
    @PatchMapping("/{id}")
    public Boolean updateCategory(@PathVariable Long id, @RequestBody CreateCategoryRequestDto request){
        return categoryService.updateCategory(id,request);
    }


    // 카테고리 삭제
//    @DeleteMapping("/{category_id}"){
//
//    }


}
