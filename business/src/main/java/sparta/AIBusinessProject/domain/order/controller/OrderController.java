package sparta.AIBusinessProject.domain.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.AIBusinessProject.domain.order.dto.OrderRequestDto;
import sparta.AIBusinessProject.domain.order.dto.OrderResponseDto;
import sparta.AIBusinessProject.domain.order.service.OrderService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
// 주문을 생성하고 특정 결제에 대한 주문 목록을 반환하는 역할
public class OrderController {

    private final OrderService orderService;

    // 1. 주문 등록
    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto createdOrder = orderService.createOrder(orderRequestDto); // 생성된 주문 정보를 OrderResponseDto 형태로 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    // 2. 주문 수정
    @PatchMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> updateOrder(
            @PathVariable UUID orderId,
            @RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto updatedOrder = orderService.updateOrder(orderId, orderRequestDto);
        return ResponseEntity.ok(updatedOrder);
    }

    // 3. 주문 취소
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    // 4. 주문 목록 조회
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        List<OrderResponseDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // 5. 주문 상세 조회
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable UUID orderId) {
        OrderResponseDto order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }



//    @GetMapping("/order")
//    public List<OrderResponseDto> getOrdersByPaymentId(@PathVariable UUID paymentId) {
//        // 경로 변수로 전달된 paymentId에 해당하는 모든 주문을 조회
//        // 조회된 주문 목록을 OrderResponseDTO 리스트 형태로 반환
//        return orderService.getOrdersByPaymentId(paymentId);
//    }

}
