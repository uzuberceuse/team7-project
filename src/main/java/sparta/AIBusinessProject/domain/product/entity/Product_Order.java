package sparta.AIBusinessProject.domain.product.entity;

import jakarta.persistence.*;
import lombok.*;
import sparta.AIBusinessProject.domain.order.entity.Order;

import java.util.UUID;

@Entity
@Table(name="p_product_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(access= AccessLevel.PUBLIC)
public class Product_Order {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name = "product_order_id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
