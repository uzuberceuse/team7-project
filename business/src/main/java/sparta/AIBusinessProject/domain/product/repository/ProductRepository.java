package sparta.AIBusinessProject.domain.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sparta.AIBusinessProject.domain.product.dto.ProductListResponseDto;
import sparta.AIBusinessProject.domain.product.entity.Product;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    // 상품 조회 시 페이징 적용
    Page<Product> findAllBy(UUID productId, Pageable pageable);
}
