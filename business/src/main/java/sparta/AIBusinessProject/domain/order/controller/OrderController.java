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
    @PatchMapping("/{order_id}")
    public ResponseEntity<OrderResponseDto> updateOrder(
            @PathVariable UUID order_id,
            @RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto updatedOrder = orderService.updateOrder(order_id, orderRequestDto);
        return ResponseEntity.ok(updatedOrder);
    }

    // 3. 주문 취소
    @DeleteMapping("/{order_id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID order_id) {
        orderService.deleteOrder(order_id);
        return ResponseEntity.noContent().build();
    }

    // 4. 주문 목록 조회
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        List<OrderResponseDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // 5. 주문 상세 조회
    @GetMapping("/{order_id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable UUID order_id) {
        OrderResponseDto order = orderService.getOrderById(order_id);
        return ResponseEntity.ok(order);
    }



//    @GetMapping("/order")
//    public List<OrderResponseDto> getOrdersByPaymentId(@PathVariable UUID payment_id) {
//        // 경로 변수로 전달된 paymentId에 해당하는 모든 주문을 조회
//        // 조회된 주문 목록을 OrderResponseDTO 리스트 형태로 반환
//        return orderService.getOrdersByPaymentId(payment_id);
//    }

}
