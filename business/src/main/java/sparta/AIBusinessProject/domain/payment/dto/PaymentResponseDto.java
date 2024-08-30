package sparta.AIBusinessProject.domain.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sparta.AIBusinessProject.domain.order.dto.OrderListResponseDto;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponseDto {
    private UUID id;
    private List<OrderListResponseDto> orders; // 주문을 요약한 Dto 리스트

    private Integer totalAmount; // 예: 전체 주문 금액 합계

    private Timestamp createdAt;
    private String createdBy;
    private Timestamp deletedAt;
    private String deletedBy;

}

// 개별 결제의 세부 정보
