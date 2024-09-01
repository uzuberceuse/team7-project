package sparta.AIBusinessProject.domain.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sparta.AIBusinessProject.domain.order.dto.OrderListResponseDto;
import sparta.AIBusinessProject.domain.order.entity.Order;
import sparta.AIBusinessProject.domain.payment.entity.Payment;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public static PaymentResponseDto toResponseDto(Payment payment) {
        return PaymentResponseDto.builder()
                .id(payment.getId())
                .orders(payment.getOrders().stream()
                        .map(PaymentResponseDto::toOrderListResponseDto)
                        .collect(Collectors.toList()))
                .createdAt(payment.getCreated_at())
                .createdBy(payment.getCreated_by())
                .deletedAt(payment.getDeleted_at())
                .deletedBy(payment.getDeleted_by())
                .build();
    }

    public static OrderListResponseDto toOrderListResponseDto(Order order) {
        return OrderListResponseDto.builder()
                .orderId(order.getId())
                .productId(order.getProduct_id())
                .quantity(order.getQuantity())
                .amount(order.getAmount())
                .build();
    }

}

// 개별 결제의 세부 정보
