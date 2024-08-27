package sparta.AIBusinessProject.domain.product.entity;


import jakarta.persistence.*;
import lombok.*;
import sparta.AIBusinessProject.domain.product.dto.ProductListResponseDto;
import sparta.AIBusinessProject.domain.product.dto.ProductRequestDto;
import sparta.AIBusinessProject.domain.product.dto.ProductResponseDto;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name="p_product")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SEQ_GENERATOR")
    @Column(nullable = false, unique = true)
    private String product_id = UUID.randomUUID().toString();

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String details;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private boolean status;

    private LocalDateTime created_at;
    private String created_by;
    private LocalDateTime deleted_at;
    private String deleted_by;
    private LocalDateTime updated_at;
    private String updated_by;



    // 상품 생성 시 생성 일자를 현재 시간으로
    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() { updated_at = LocalDateTime.now(); }

    @PreRemove
    protected void onDelete() { deleted_at = LocalDateTime.now(); }

    // buildup 패턴으로 product 생성
    public static Product createProduct(ProductRequestDto requestDto, String user_id) {
        return Product.builder()
                .productName(requestDto.getProductName())
                .price(requestDto.getPrice())
                .details(requestDto.getDetails())
                .status(requestDto.isStatus())
                .created_by(user_id)
                .build();
    }

    // buildup 패턴으로 product 수정
    public static Product updateProduct(ProductRequestDto requestDto, String user_id) {
        return Product.builder()
                .productName(requestDto.getProductName())
                .price(requestDto.getPrice())
                .details(requestDto.getDetails())
                .status(requestDto.isStatus())
                .updated_by(user_id)
                .build();
    }

    // buildup 패턴으로 product 삭제
    // ??????
    public static Product deleteProduct(ProductRequestDto requestDto, String user_id) {
        return Product.builder()
                .productName(requestDto.getProductName())
                .status(requestDto.isStatus())
                .deleted_by(user_id)
                .build();
    }

    // ProductResponseDTO 변환 메서드
    public ProductResponseDto toResponseDto() {
        return new ProductResponseDto(
                this.product_id,
                this.productName,
                this.details,
                this.price,
                this.status,
                this.created_at,
                this.created_by,
                this.updated_at,
                this.updated_by,
                this.deleted_at,
                this.deleted_by
        );
    }

    // ProductListResponseDTO 변환 메서드
    public ProductListResponseDto toListResponseDto() {
        return new ProductListResponseDto(
                this.product_id,
                this.productName,
                this.status
        );
    }
}