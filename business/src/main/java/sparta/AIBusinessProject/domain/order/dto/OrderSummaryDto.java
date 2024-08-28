package sparta.AIBusinessProject.domain.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// PaymentResponseDto에서 주문의 요약 정보를 제공하기 위해 사용하는 Dto
public class OrderSummaryDto {
    private UUID orderId;
    private UUID productId;      // 상품 ID
    private Integer amount;
    private Integer quantity;
}

// OrderResponseDto 대신 간단한 정보만 포함
