package sparta.AIBusinessProject.domain.order.service;

import lombok.*;

import org.springframework.stereotype.Service;
import sparta.AIBusinessProject.domain.address.entity.Address;
import sparta.AIBusinessProject.domain.address.repository.AddressRepository;
import sparta.AIBusinessProject.domain.order.dto.OrderRequestDto;
import sparta.AIBusinessProject.domain.order.dto.OrderResponseDto;
import sparta.AIBusinessProject.domain.order.entity.Order;
import sparta.AIBusinessProject.domain.order.repository.OrderRepository;
import sparta.AIBusinessProject.domain.payment.entity.Payment;
import sparta.AIBusinessProject.domain.payment.repository.PaymentRepository;
import sparta.AIBusinessProject.domain.user.entity.User;
import sparta.AIBusinessProject.domain.user.repository.UserRepository;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
// Order 생성, 특정 결제 ID와 연관된 Order 목록을 조회하는 기능을 제공
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    // 1. 주문 생성
    // 이 메서드는 OrderRequestDto를 받아 Order 엔티티를 생성하고 저장
    // User, Address, Payment 엔티티를 각 Repository에서 조회하여 Order 엔티티와 연관시킴
    // 생성된 Order 엔티티를 저장한 후, OrderResponseDto로 변환하여 반환
    public OrderResponseDto createOrder(OrderRequestDto requestDto) {
        // User와 Address, Payment를 조회하여 엔티티 객체로 변환
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Address address = addressRepository.findById(requestDto.getAddressId())
                .orElseThrow(() -> new RuntimeException("Address not found"));

        Payment payment = paymentRepository.findById(requestDto.getPaymentId())
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        // Order 엔티티 생성
        Order order = Order.builder()
                .user(user)
                .address(address)
                .payment(payment)
                .product_id(requestDto.getProductId())
                .quantity(requestDto.getQuantity())
                .amount(requestDto.getAmount())
                .type(requestDto.getType())
                .created_at(new Timestamp(System.currentTimeMillis()))
                .build();

        Order savedOrder = orderRepository.save(order);   // 주문 저장

        return OrderResponseDto.fromEntity(savedOrder);  // 반환할 OrderResponseDto 생성
    }

    // 2. 주문 수정
    public OrderResponseDto updateOrder(UUID orderId, OrderRequestDto orderRequestDto) {
        // 기존 주문 조회
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        // 주문 수정
        User user = userRepository.findById(orderRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Address address = addressRepository.findById(orderRequestDto.getAddressId())
                .orElseThrow(() -> new IllegalArgumentException("Address not found"));
        Payment payment = paymentRepository.findById(orderRequestDto.getPaymentId())
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));

        existingOrder.setUser(user);
        existingOrder.setAddress(address);
        existingOrder.setPayment(payment);
        existingOrder.setProduct_id(orderRequestDto.getProductId());
        existingOrder.setQuantity(orderRequestDto.getQuantity());
        existingOrder.setAmount(orderRequestDto.getAmount());
        existingOrder.setType(orderRequestDto.getType());
        existingOrder.setUpdated_at(new Timestamp(System.currentTimeMillis()));
        existingOrder.setUpdated_by("System"); // 실제 환경에서는 현재 사용자 정보를 사용할 수 있습니다.

        // 수정된 주문 저장
        Order updatedOrder = orderRepository.save(existingOrder);

        // 반환할 OrderResponseDto 생성
        return OrderResponseDto.fromEntity(updatedOrder);
    }

    // 3. 주문 삭제
    public void deleteOrder(UUID orderId) {
        // 주문 존재 여부 확인 후 삭제
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        orderRepository.delete(existingOrder);
    }

    // 4. 주문 목록 조회
    public List<OrderResponseDto> getAllOrders() {
        // 모든 주문 조회
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    // 5. 주문 상세 조회
    public OrderResponseDto getOrderById(UUID orderId) {
        // 주문 조회
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        // 반환할 OrderResponseDto 생성
        return OrderResponseDto.fromEntity(order);
    }
}


//createOrder 메서드:
//
//OrderRequestDto를 받아 Order 엔티티를 생성하고 저장한 후, 저장된 엔티티를 OrderResponseDto로 변환하여 반환합니다.
//        필요한 User, Address, Payment 엔티티를 먼저 조회하여 주문 생성 시 사용합니다.
//updateOrder 메서드:
//
//orderId를 기반으로 기존 주문을 조회한 후, 해당 주문을 업데이트합니다.
//OrderRequestDto에서 새로운 값을 받아와 기존 주문의 필드를 업데이트하고, 이를 저장한 후 OrderResponseDto로 변환하여 반환합니다.
//deleteOrder 메서드:
//
//orderId를 기반으로 주문을 조회하고, 해당 주문을 삭제합니다.
//getAllOrders 메서드:
//
//모든 주문을 조회하여 OrderResponseDto의 리스트로 반환합니다.
//Order 엔티티를 OrderResponseDto로 변환하여 클라이언트에게 전달합니다.
//getOrderById 메서드:
//
//orderId로 특정 주문을 조회한 후, 이를 OrderResponseDto로 변환하여 반환합니다.