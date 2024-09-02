package sparta.AIBusinessProject.domain.order.service;

import lombok.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.AIBusinessProject.domain.address.entity.Address;
import sparta.AIBusinessProject.domain.address.repository.AddressRepository;
import sparta.AIBusinessProject.domain.order.dto.OrderListResponseDto;
import sparta.AIBusinessProject.domain.order.dto.OrderRequestDto;
import sparta.AIBusinessProject.domain.order.dto.OrderResponseDto;
import sparta.AIBusinessProject.domain.order.entity.Order;
import sparta.AIBusinessProject.domain.order.repository.OrderRepository;
import sparta.AIBusinessProject.domain.payment.entity.Payment;
import sparta.AIBusinessProject.domain.payment.repository.PaymentRepository;
import sparta.AIBusinessProject.domain.store.entity.Store;
import sparta.AIBusinessProject.domain.store.repository.StoreRepository;
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
    private final StoreRepository storeRepository;

    // 1. 주문 생성
    // 이 메서드는 OrderRequestDto를 받아 Order 엔티티를 생성하고 저장
    // User, Address, Payment 엔티티를 각 Repository에서 조회하여 Order 엔티티와 연관시킴
    // 생성된 Order 엔티티를 저장한 후, OrderResponseDto로 변환하여 반환
    public OrderResponseDto createOrder(OrderRequestDto requestDto,User user) {
        // User와 Address, Payment를 조회하여 엔티티 객체로 변환
        User orderUser = userRepository.findById(user.getUser_id())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Address address = addressRepository.findById(requestDto.getAddressId())
                .orElseThrow(() -> new RuntimeException("Address not found"));

        Store store = storeRepository.findById(requestDto.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        // Order 엔티티 생성
        Order order = Order.builder()
                .user(orderUser)
                .address(address)
                .store(store)
                .product_id(requestDto.getProductId())
                .quantity(requestDto.getQuantity())
                .amount(requestDto.getAmount())
                .type(requestDto.getType())
                .created_at(new Timestamp(System.currentTimeMillis()))
                .created_by(String.valueOf(user.getUser_id()))
                .build();
        Payment payment = order.getPayment();
        Order savedOrder=null;
        if(payment != null) {
        savedOrder = orderRepository.save(order);   // 주문 저장
       }
        return OrderResponseDto.preResponseDto(savedOrder);  // 반환할 OrderResponseDto 생성
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
        Store store = storeRepository.findById(orderRequestDto.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("Store not found"));

        existingOrder.setUser(user);
        existingOrder.setAddress(address);
        existingOrder.setPayment(payment);
        existingOrder.setStore(store);
        existingOrder.setProduct_id(orderRequestDto.getProductId());
        existingOrder.setQuantity(orderRequestDto.getQuantity());
        existingOrder.setAmount(orderRequestDto.getAmount());
        existingOrder.setType(orderRequestDto.getType());
        existingOrder.setUpdated_at(new Timestamp(System.currentTimeMillis()));
        existingOrder.setUpdated_by(orderRequestDto.getUpdatedBy());

        // 수정된 주문 저장
        Order updatedOrder = orderRepository.save(existingOrder);

        // 반환할 OrderResponseDto 생성
        return OrderResponseDto.toResponseDto(updatedOrder);
    }

    // 3. 주문 취소
    public void deleteOrder(UUID orderId) {
        // 주문 존재 여부 확인 후 취소
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        // 주문이 생성된 후 5분 이내인지 확인
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        long timeDifference = currentTime.getTime() - order.getCreated_at().getTime();

        // 5분(300,000 밀리초) 이내에만 취소 가능
        // 5분 초과하면 취소 제한
        if (timeDifference > 300000) {
            throw new IllegalStateException("Order can only be canceled within 5 minutes of creation.");
        }

        order.setDeleted_at(currentTime);

        orderRepository.delete(order);
    }

    // 4. 주문 목록 조회
    @Transactional(readOnly = true)
    public Page<OrderListResponseDto> getOrderList(Pageable pageable) {

        return orderRepository.findAll(pageable).map(order -> new OrderListResponseDto(
                order.getId(),
                order.getProduct_id(),
                order.getAmount(),
                order.getQuantity(),
                order.getUser().getUser_id(),
                order.getCreated_at(),
                order.getCreated_by()
        ));
    }

    // 5. 주문 상세 조회
    @Transactional(readOnly = true)
    public OrderResponseDto getOrder(UUID orderId) {
        // 주문 조회
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        // 반환할 OrderResponseDto 생성
        return OrderResponseDto.toResponseDto(order);
    }

    // Response DTO 변환 메서드
    //  application 의 private 메서드보다 Dto 에서 static method 로 처리하는 것이
    //  어떠한 ResponseDto로 치환되는지 확인할 수 있기 때문에 직관성이 좋다.
//    private OrderResponseDto toResponseDto(Order order) {
//        return OrderResponseDto.builder()
//                .orderId(order.getId())
//                .userId(order.getUser().getUser_id())
//                .addressId(order.getAddress().getId())
//                .paymentId(order.getPayment().getId())
//                .productId(order.getProduct_id())
//                .quantity(order.getQuantity())
//                .amount(order.getAmount())
//                .type(order.getType())
//                .createdAt(order.getCreated_at())
//                .createdBy(order.getCreated_by())
//                .updatedAt(order.getUpdated_at())
//                .updatedBy(order.getUpdated_by())
//                .build();
//    }
}


// createOrder 메서드
// OrderRequestDto를 받아 Order 엔티티를 생성하고 저장한 후, 저장된 엔티티를 OrderResponseDto로 변환하여 반환
// 필요한 User, Address, Payment 엔티티를 먼저 조회하여 주문 생성 시 사용

// updateOrder 메서드
// orderId를 기반으로 기존 주문을 조회한 후, 해당 주문을 업데이트
// OrderRequestDto에서 새로운 값을 받아와 기존 주문의 필드를 업데이트하고, 이를 저장한 후 OrderResponseDto로 변환하여 반환합니다.

// deleteOrder 메서드
// orderId를 기반으로 주문을 조회하고, 해당 주문을 삭제

// getAllOrders 메서드
// 모든 주문을 조회하여 OrderResponseDto의 리스트로 반환
// Order 엔티티를 OrderResponseDto로 변환하여 클라이언트에게 전달

//getOrderById 메서드:
//orderId로 특정 주문을 조회한 후, 이를 OrderResponseDto로 변환하여 반환