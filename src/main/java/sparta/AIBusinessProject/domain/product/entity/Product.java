package sparta.AIBusinessProject.domain.product.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import sparta.AIBusinessProject.domain.product.dto.ProductRequestDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name="p_product")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="product_id",updatable = false, nullable = false)
    private UUID productId;

    @OneToMany(mappedBy = "product")
    @Builder.Default
    private List<Product_Order> product_orders = new ArrayList<>();

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String details;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private boolean status;

    private Timestamp createdAt;
    private String createdBy;
    private Timestamp deletedAt;
    private String deletedBy;
    private Timestamp updatedAt;
    private String updatedBy;



    // 상품 생성 시 생성 일자를 현재 시간으로
    @PrePersist
    protected void onCreate() {
        createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

    @PreUpdate
    protected void onUpdate() { updatedAt = Timestamp.valueOf(LocalDateTime.now());}


    // buildup 패턴으로 product 생성
    public static Product createProduct(ProductRequestDto requestDto, String user_id) {
        return Product.builder()
                .productName(requestDto.getProductName())
                .price(requestDto.getPrice())
                .details(requestDto.getDetails())
                .status(requestDto.isStatus())
                .createdBy(user_id)
                .build();
    }

    // product 업데이트
    public void updateProduct(String productName, String details, Integer price, boolean status, String userId ){
            this.productName = productName;
            this.details = details;
            this.price = price;
            this.status = status;
            this.updatedBy = userId;
    }

    public void deleteProduct(String userId) {
        this.deletedBy = userId;
        deletedAt = Timestamp.valueOf(LocalDateTime.now());
    }
}