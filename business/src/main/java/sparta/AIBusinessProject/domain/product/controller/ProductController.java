package sparta.AIBusinessProject.domain.product.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sparta.AIBusinessProject.domain.product.dto.ProductListResponseDto;
import sparta.AIBusinessProject.domain.product.dto.ProductRequestDto;
import sparta.AIBusinessProject.domain.product.dto.ProductResponseDto;
import sparta.AIBusinessProject.domain.product.service.ProductService;

import java.util.UUID;

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
        /* 아직 gateway를 확인하지 않아 임시로 코드를 짬
           기본적으로 로그인한 상태에서 user_id와 role 체크
         */
        @PostMapping
        public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto,
                                                @RequestHeader(value = "X-User-Id") String user_id,
                                                @RequestHeader(value = "X-Role") String role){
                // MANAGER, STORE 권한을 가져야만 create 가능
                if(!"MANGER".equals(role) && !"STORE".equals(role)){
                        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "접근이 허용되지 않습니다.");
                }

                return productService.createProduct(requestDto, user_id);
        }

        /* 상품 수정
           수정 결과 확인
        */
        @PatchMapping("/{product_id}")
        public ProductResponseDto updateProduct(@RequestBody ProductRequestDto requestDto,
                                                @RequestHeader(value = "X-User-Id") String user_id,
                                                @RequestHeader(value = "X-Role") String role,
                                                @PathVariable UUID product_id){
                // MANAGER, STORE 권한을 가져야만 update 가능
                if(!"MANGER".equals(role) && !"STORE".equals(role)){
                        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "접근이 허용되지 않습니다.");
                }

                return productService.updateProduct(requestDto, product_id, user_id);
        }

        /* 상품 삭제
           삭제 결과는 T/F
        */
        @DeleteMapping("/{product_id}")
        public Boolean deleteProduct(@RequestHeader(value = "X-User-Id") String user_id,
                                     @RequestHeader(value = "X-Role") String role,
                                     @PathVariable UUID product_id){

                // MANAGER, STORE 권한을 가져야만 delete 가능
                if(!"MANGER".equals(role) && !"STORE".equals(role)){
                        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "접근이 허용되지 않습니다.");
                }

                return productService.deleteProduct(product_id, user_id);
        }

        // 상품 목록 조회
        @GetMapping
        public Page<ProductListResponseDto> getProducts(ProductListResponseDto listResponseDto, Pageable pageable){

                return productService.getProducts(listResponseDto, pageable);
        }

        // 상품 상세 조회
        @GetMapping("/{product_id}")
        public ProductResponseDto getProduct(@PathVariable UUID product_id){

                return productService.getProductById(product_id);
        }
}
