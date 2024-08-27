package sparta.AIBusinessProject.domain.payment.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
public class PaymentListResponseDto {
    private UUID id;
    private Timestamp createdAt; // 결제일
    private String createdBy;
    private Integer totalAmount; // 예: 전체 주문 금액 합계
}

/// 결제 목록