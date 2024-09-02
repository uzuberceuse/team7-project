package sparta.AIBusinessProject.domain.product.service;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    // 상품 등록
    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto requestDto, String userId){
        Product product = Product.createProduct(requestDto, userId);
        return ProductResponseDto.toResponseDto(productRepository.save(product));
    }

    // 상품 수정
    // 이미 null값인 것은 안나오도록
    @Transactional
    public ProductResponseDto updateProduct(ProductRequestDto requestDto, UUID productId, String  userId){
        Product product = productRepository.findById(productId)
                .filter(p -> p.getDeleted_at() == null)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 상품을 찾을 수 없거나 이미 삭제된 상태입니다."));

        product.updateProduct(requestDto.getProductName(), requestDto.getDetails(), requestDto.getPrice(), requestDto.isStatus(), userId);

        return ProductResponseDto.toResponseDto(product);
    }

    // 상품 삭제
    // 실제로 DB에서 삭제하는 것이 아닌 삭제 필드에 데이터가 들어가면 삭제라고 판단
    // 조회 시 시간값이 있다면 삭제된 것으로 판단하겠음
    @Transactional
    public Boolean deleteProduct(UUID productId, String  userId) {
        try {
            Product product = productRepository.findById(productId)
                    .filter(p -> p.getDeleted_at() == null)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 상품을 찾을 수 없거나 이미 삭제된 상태입니다."));

            product.deleteProduct(userId);
            return true;
        } catch (Exception e) { return false; }
    }

    // 상품 목록 조회
    // 페이징 적용
    // 지연로딩 어노테이션 추가
    @Transactional(readOnly = true)
    public Page<ProductListResponseDto> getProducts(ProductListResponseDto listResponseDto, int page, int size, String sortBy, boolean isAsc){

        // 페이지 정렬
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);

        // 페이징 처리
        Pageable pageable = PageRequest.of(page, size, sort);

        return productRepository.findAll(pageable).map(ProductListResponseDto::new);
    }

    // 상품 상세 조회
    @Transactional(readOnly = true)
    public ProductResponseDto getProductById(UUID productId) {
        Product product = productRepository.findById(productId)
                .filter(p -> p.getDeleted_at() == null)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 상품을 찾을 수 없거나 이미 삭제된 상태입니다."));
        return ProductResponseDto.toResponseDto(product);
    }
}
