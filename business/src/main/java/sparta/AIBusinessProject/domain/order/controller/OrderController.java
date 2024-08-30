package sparta.AIBusinessProject.domain.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import sparta.AIBusinessProject.domain.complain.dto.ComplainListResponseDto;
import sparta.AIBusinessProject.domain.order.dto.OrderListResponseDto;
import sparta.AIBusinessProject.domain.order.dto.OrderRequestDto;
import sparta.AIBusinessProject.domain.order.dto.OrderResponseDto;
import sparta.AIBusinessProject.domain.order.entity.Order;
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
    @Secured({"ROLE_MANAGER", "ROLE_OWNER", "ROLE_CUSTOMER"})
    @PostMapping("/{user_id}")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto,
                                                        @PathVariable("user_id") UUID userId) {
        OrderResponseDto createdOrder = orderService.createOrder(orderRequestDto, userId); // 생성된 주문 정보를 OrderResponseDto 형태로 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    // 2. 주문 수정
    @Secured({"ROLE_MANAGER", "ROLE_OWNER", "ROLE_CUSTOMER"})
    @PatchMapping("/{order_id}")
    public ResponseEntity<OrderResponseDto> updateOrder(
            @PathVariable UUID order_id,
            @RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto updatedOrder = orderService.updateOrder(order_id, orderRequestDto);
        return ResponseEntity.ok(updatedOrder);
    }

    // 3. 주문 취소
    @Secured({"ROLE_MANAGER", "ROLE_OWNER", "ROLE_CUSTOMER"})
    @DeleteMapping("/{order_id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID order_id) {
        orderService.deleteOrder(order_id);
        return ResponseEntity.noContent().build();
    }

    // 4. 주문 목록 조회
    @Secured({"ROLE_MANAGER", "ROLE_OWNER", "ROLE_CUSTOMER"})
    @GetMapping
    public ResponseEntity<Page<OrderListResponseDto>> getOrderList(
            @RequestParam(defaultValue = "0") int page,         // 기본 페이지 번호 0
            @RequestParam(defaultValue = "10") int size,        // 기본 페이지 크기 10
            @RequestParam(defaultValue = "createdAt") String sortBy, // 기본 정렬 필드 createdAt
            @RequestParam(defaultValue = "desc") String direction    // 기본 정렬 방향 desc
            ) {

        // 페이지 요청 생성
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<OrderListResponseDto> responseDto = orderService.getOrderList(pageable);
        return ResponseEntity.ok(responseDto);
    }

    // 5. 주문 상세 조회
    @Secured({"ROLE_MANAGER", "ROLE_OWNER", "ROLE_CUSTOMER"})
    @GetMapping("/{order_id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable UUID order_id) {
        OrderResponseDto order = orderService.getOrder(order_id);
        return ResponseEntity.ok(order);
    }

}
