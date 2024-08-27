package sparta.AIBusinessProject.domain.payment.dto;

import java.util.*;
import java.sql.Timestamp;
import lombok.*;
import sparta.AIBusinessProject.domain.order.dto.OrderResponseDto;

@Data
@Builder
public class PaymentDto {

    private UUID id;
    private List<OrderResponseDto> orders;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
    private Timestamp deletedAt;
    private String deletedBy;
}
// 주문을 받아 결제해서 보여주면 되어서 일단 PaymentDto만 만듦
// responseDto라 PaymentDto로 한번에 만듦