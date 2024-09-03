package sparta.AIBusinessProject.domain.payment.entity;

import jakarta.persistence.*;
import lombok.*;
import sparta.AIBusinessProject.domain.order.entity.Order;

import java.sql.Timestamp;
import java.util.*;
import java.util.UUID;

@Entity
@Table(name="p_payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(access= AccessLevel.PUBLIC)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "payment_id", updatable = false, nullable = false)
    private UUID id;

    @OneToMany(mappedBy = "payment")
    @Builder.Default
    private List<Order> orders = new ArrayList<>();
    // 1번의 결제에 여러번 주문이 가능한 상황
    // Order:Payment = 1 : N (일대다 관계)

    private Integer totalAmount;

    @Column(name="pg_id", nullable = false)
    private String pgId;//private UUID pg_id; // pg사 결제연동 id

    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
    private Timestamp deletedAt;
    private String deletedBy;
}
