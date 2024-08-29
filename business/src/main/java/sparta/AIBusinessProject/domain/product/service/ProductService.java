package sparta.AIBusinessProject.domain.product.service;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sparta.AIBusinessProject.domain.product.dto.ProductListResponseDto;
import sparta.AIBusinessProject.domain.product.dto.ProductRequestDto;
import sparta.AIBusinessProject.domain.product.dto.ProductResponseDto;
import sparta.AIBusinessProject.domain.product.entity.Product;
import sparta.AIBusinessProject.domain.product.repository.ProductRepository;

import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    // 상품 등록
    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto requestDto, String user_id){
        Product product = Product.createProduct(requestDto, user_id);
        Product createdProduct = productRepository.save(product);

        return toResponseDto(createdProduct);
    }

    // 상품 수정
    // 이미 null값인 것은 안나오도록
    @Transactional
    public ProductResponseDto updateProduct(ProductRequestDto requestDto, UUID product_id, String user_id){
        Product product = productRepository.findById(product_id)
                .filter(p -> p.getDeleted_at() == null)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 상품을 찾을 수 없거나 이미 삭제된 상태입니다."));

        product.updateProduct(requestDto.getProductName(), requestDto.getDetails(), requestDto.getPrice(), requestDto.isStatus(), user_id);
        Product updatedProduct = productRepository.save(product);

        return toResponseDto(updatedProduct);
    }

    // 상품 삭제
    // 실제로 DB에서 삭제하는 것이 아닌 삭제 필드에 데이터가 들어가면 삭제라고 판단
    // 조회 시 시간값이 있다면 삭제된 것으로 판단하겠음
    @Transactional
    public Boolean deleteProduct(UUID product_id, String user_id) {
        try {
            Product product = productRepository.findById(product_id)
                    .filter(p -> p.getDeleted_at() == null)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 상품을 찾을 수 없거나 이미 삭제된 상태입니다."));

            product.deleteProduct(user_id);
            productRepository.save(product);
            return true;
        } catch (Exception e) { return false; }
    }

    // 상품 목록 조회
    // 페이징 적용
    public Page<ProductListResponseDto> getProducts(ProductListResponseDto listResponseDto, Pageable pageable){
        return productRepository.getProducts(listResponseDto, pageable);
    }

    // 상품 상세 조회
    @Transactional(readOnly = true)
    public ProductResponseDto getProductById(UUID product_id) {
        Product product = productRepository.findById(product_id)
                .filter(p -> p.getDeleted_at() == null)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 상품을 찾을 수 없거나 이미 삭제된 상태입니다."));
        return toResponseDto(product);
    }


    // ProductResponseDTO 변환 메서드
    public ProductResponseDto toResponseDto(Product product) {
        return new ProductResponseDto(
                product.getProduct_id(),
                product.getProductName(),
                product.getDetails(),
                product.getPrice(),
                product.isStatus(),
                product.getCreated_at(),
                product.getCreated_by(),
                product.getUpdated_at(),
                product.getUpdated_by(),
                product.getDeleted_at(),
                product.getDeleted_by()
        );
    }
}
