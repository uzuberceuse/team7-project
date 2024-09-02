package sparta.AIBusinessProject.domain.payment.service;


import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.AIBusinessProject.domain.order.dto.OrderListResponseDto;
import sparta.AIBusinessProject.domain.order.repository.OrderRepository;
import sparta.AIBusinessProject.domain.payment.dto.PaymentRequestDto;
import sparta.AIBusinessProject.domain.payment.dto.PaymentResponseDto;
import sparta.AIBusinessProject.domain.payment.entity.Payment;
import sparta.AIBusinessProject.domain.order.entity.Order;
import sparta.AIBusinessProject.domain.payment.repository.PaymentRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Builder
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    // 1. 결제 생성
    public PaymentResponseDto createPayment(PaymentRequestDto requestDto, UUID userId) {

        List<Order> orderList = orderRepository.findAllById(requestDto.getOrderIds());

        Payment payment = Payment.builder()
                .orders(orderList)
                .pgId(requestDto.getPgId())
                .created_by(String.valueOf(userId))
                .created_at(new Timestamp(System.currentTimeMillis()))
                .build();

        payment = paymentRepository.save(payment);

        // 주문을 결제에 연결
        List<Order> orders = orderRepository.findAllById(requestDto.getOrderIds());
        orders.forEach(order -> order.setPayment(Payment.builder().build()));

        return PaymentResponseDto.toResponseDto(payment);
    }

    // 2. 결제 취소
    public void cancelPayment(UUID paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));

        // 관련된 주문들의 결제 정보 제거
        List<Order> orders = payment.getOrders();
        orders.forEach(order -> order.setPayment(null));

        paymentRepository.delete(payment);
    }

    // 3. 결제 개별 정보 조회
    @Transactional(readOnly = true)
    public PaymentResponseDto getPayment(UUID paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));

        return PaymentResponseDto.toResponseDto(payment);
    }

    // 4. 결제 목록 조회
    @Transactional(readOnly = true)
    public Page<PaymentResponseDto> getPaymentList(Pageable pageable) {

        return paymentRepository.findAll(pageable).map(payment -> new PaymentResponseDto(
                payment.getId(),
                payment.getOrders().stream().map(PaymentResponseDto::toOrderListResponseDto).collect(Collectors.toList()),
                payment.getTotalAmount(),
                payment.getCreated_at(),
                payment.getCreated_by(),
                payment.getDeleted_at(),
                payment.getDeleted_by()
        ));
    }

    // DTO 변환 메서드들
    //  application 의 private 메서드보다 Dto 에서 static method 로 처리하는 것이
    //  어떠한 ResponseDto로 치환되는지 확인할 수 있기 때문에 직관성이 좋다.
//    private PaymentResponseDto toResponseDto(Payment payment) {
//        return PaymentResponseDto.builder()
//                .id(payment.getId())
//                .orders(payment.getOrders().stream()
//                        .map(this::toOrderListResponseDto)
//                        .collect(Collectors.toList()))
//                .createdAt(payment.getCreated_at())
//                .createdBy(payment.getCreated_by())
//                .deletedAt(payment.getDeleted_at())
//                .deletedBy(payment.getDeleted_by())
//                .build();
//    }

//    private PaymentListResponseDto toListResponseDto(Payment payment) {
//        return PaymentListResponseDto.builder()
//                .id(payment.getId())
//                .createdAt(payment.getCreated_at())
//                .createdBy(payment.getCreated_by())
//                .totalAmount(payment.getOrders().stream()
//                        .mapToInt(Order::getAmount)
//                        .sum())
//                .build();
//    }

//    private OrderListResponseDto toOrderListResponseDto(Order order) {
//        return OrderListResponseDto.builder()
//                .orderId(order.getId())
//                .productId(order.getProduct_id())
//                .quantity(order.getQuantity())
//                .amount(order.getAmount())
//                .build();
//    }
}
