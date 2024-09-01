package sparta.AIBusinessProject.domain.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sparta.AIBusinessProject.domain.product.dto.ProductListResponseDto;

public interface ProductRepositoryCustom {

    // 상품 조회 시 페이징 적용
    Page<ProductListResponseDto> findByProductId(ProductListResponseDto listResponseDto, Pageable pageable);
}


