package sparta.AIBusinessProject.domain.product.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.AIBusinessProject.domain.product.dto.ProductRequestDto;
import sparta.AIBusinessProject.domain.product.dto.ProductResponseDto;
import sparta.AIBusinessProject.domain.product.entity.Product;
import sparta.AIBusinessProject.domain.product.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

        private final ProductService productService;

        public ProductController(ProductService productService){
            this.productService = productService;
        }


        /* 상품 등록
           등록 결과 확인
        */
        @PostMapping
        public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto,
                                                @RequestHeader String user_id){

                return ;
        }

        /* 상품 등록
           등록 결과 확인
        */
        @PatchMapping("/{product_id}")
        public ProductResponseDto updateProduct(@RequestBody ProductRequestDto productRequestDto,
                                                @RequestHeader String user_id){
                return ;
        }

        /* 상품 삭제
           삭제 결과는 T/F
        */
        @DeleteMapping("/{product_id}")
        public Boolean deleteProduct(@RequestBody ProductRequestDto productRequestDto,
                                     @RequestHeader String user_id){
                return true;
        }

        // 상품 목록 조회
        @GetMapping
        public ResponseEntity<List<ProductResponseDto>> getProducts(){
                return ;
        }

        // 상품 상세 조회
        @GetMapping("/{product_id}")
        public ProductResponseDto getProduct(){
                return ;
        }
}
