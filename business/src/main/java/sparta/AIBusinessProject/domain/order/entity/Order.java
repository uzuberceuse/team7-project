package sparta.AIBusinessProject.domain.order.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import sparta.AIBusinessProject.domain.address.entity.Address;
import sparta.AIBusinessProject.domain.order.entity.OrderTypeEnum;
import sparta.AIBusinessProject.domain.payment.entity.Payment;
import sparta.AIBusinessProject.domain.user.entity.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="p_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(access= AccessLevel.PUBLIC)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // 외래 키 컬럼
    private User user;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false) // 외래 키 컬럼
    private Address address;

    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = false) // 외래 키 컬럼
    private Payment payment;
    // Order는 단일 Payment와 다대일 관계

    @Column(nullable = false)
    private UUID product_id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    @Enumerated(value= EnumType.STRING)
    private OrderTypeEnum type;

    private Timestamp created_at;
    private String created_by;
    private Timestamp updated_at;
    private String updated_by;
    private Timestamp deleted_at;
    private String deleted_by;
}
