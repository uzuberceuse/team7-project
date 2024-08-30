package sparta.AIBusinessProject.domain.order.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import sparta.AIBusinessProject.domain.order.entity.OrderTypeEnum;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDto {
    private UUID id;
    private UUID userId;        // 유저 ID
    private UUID addressId;     // 주소 ID
    private UUID productId;     // 상품 ID
    private UUID storeId;       // 가게 ID
    private Integer quantity;   // 수량
    private Integer amount;     // 총금액
    private OrderTypeEnum type; // 주문 타입 (온라인/오프라인)
    private UUID paymentId;     // 결제 ID

    private String createdBy;
    private String updatedBy;
    private String deletedBy;
}


// 클라이언트에서 주문 생성이나 수정 요청을 할 때 필요한 정보를 담고 있다
// userId, addressId, paymentId와 같은 필드들은 Order 엔티티를 생성할 때 필요한 외래 키 정보
// 주문 수량, 총 금액, 주문 타입 등의 정보를 포함하고 있다