package sparta.AIBusinessProject.domain.product.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import sparta.AIBusinessProject.domain.product.dto.ProductRequestDto;

import java.sql.Timestamp;
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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
    @ColumnDefault("random_uuid()")
    @Column(updatable = false, nullable = false)
    private UUID product_id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String details;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private boolean status;

    private Timestamp created_at;
    private String created_by;
    private Timestamp deleted_at;
    private String deleted_by;
    private Timestamp updated_at;
    private String updated_by;



    // 상품 생성 시 생성 일자를 현재 시간으로
    @PrePersist
    protected void onCreate() {
        created_at = Timestamp.valueOf(LocalDateTime.now());
        status = true;
    }

    @PreUpdate
    protected void onUpdate() { updated_at = Timestamp.valueOf(LocalDateTime.now());}

    // ?????실제로 삭제하는 것이 아니라 어노테이션 사용이 헷갈림
    @PreRemove
    protected void onDelete() {
        deleted_at = Timestamp.valueOf(LocalDateTime.now());
        status = false;
    }

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

    // product 업데이트
    public void updateProduct(String productName, String details, Integer price, boolean status, String user_id ){
            this.productName = productName;
            this.details = details;
            this.price = price;
            this.status = status;
            this.updated_by = user_id;
    }

    public void deleteProduct(String user_id){
            this.deleted_by = user_id;
    }
}