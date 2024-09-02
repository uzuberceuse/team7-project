package sparta.AIBusinessProject.domain.order.dto;

import sparta.AIBusinessProject.domain.order.entity.Order;
import sparta.AIBusinessProject.domain.order.entity.OrderTypeEnum;

import lombok.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {
    private UUID orderId;        // 주문 ID
    private UUID userId;         // 유저 ID
    private UUID addressId;      // 주소 ID
    private UUID paymentId;      // 결제 ID
    private UUID productId;      // 상품 ID
    private Integer quantity;    // 수량
    private Integer amount;      // 총 금액
    private OrderTypeEnum type;  // 주문 타입 (예: 온라인, 오프라인 등)
    private Timestamp createdAt; // 생성 시간
    private String createdBy;    // 생성자
    private Timestamp updatedAt; // 수정 시간
    private String updatedBy;    // 수정자
    private Timestamp deletedAt;
    private String deletedBy;

    // 페이징 관련 필드 추가
    private int page;           // 현재 페이지 번호
    private int size;           // 페이지 크기
    private int totalPages;      // 전체 페이지 수
    private long totalElements;  // 전체 요소 수

    public static OrderResponseDto toResponseDto(Order order) {
        return OrderResponseDto.builder()
                .orderId(order.getId())
                .userId(order.getUser().getUser_id())
                .addressId(order.getAddress().getId())
                .paymentId(order.getPayment().getId())
                .productId(order.getProduct_id())
                .quantity(order.getQuantity())
                .amount(order.getAmount())
                .type(order.getType())
                .createdAt(order.getCreated_at())
                .createdBy(order.getCreated_by())
                .updatedAt(order.getUpdated_at())
                .updatedBy(order.getUpdated_by())
                .build();
    }

    public static OrderResponseDto preResponseDto(Order order) {
        return OrderResponseDto.builder()
                .orderId(order.getId())
                .userId(order.getUser().getUser_id())
                .addressId(order.getAddress().getId())
                .productId(order.getProduct_id())
                .quantity(order.getQuantity())
                .amount(order.getAmount())
                .type(order.getType())
                .createdAt(order.getCreated_at())
                .createdBy(order.getCreated_by())
                .updatedAt(order.getUpdated_at())
                .updatedBy(order.getUpdated_by())
                .build();
    }
}

// 주문 생성, 수정, 조회 시 클라이언트에게 반환할 정보를 담고 있다.
// Order 엔티티를 기반으로 OrderResponseDto를 생성하는 fromEntity 메서드를 제공하여,
// 엔티티 객체를 쉽게 DTO로 변환할 수 있다.

// Order 엔티티의 ID뿐만 아니라 관련된 User, Address, Payment 엔티티의 ID도 포함하여,
// 클라이언트가 필요한 모든 정보를 전달할 수 있도록 한다.