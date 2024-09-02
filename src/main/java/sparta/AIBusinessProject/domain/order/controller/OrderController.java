package sparta.AIBusinessProject.domain.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparta.AIBusinessProject.domain.complain.dto.ComplainListResponseDto;
import sparta.AIBusinessProject.domain.order.dto.OrderListResponseDto;
import sparta.AIBusinessProject.domain.order.dto.OrderRequestDto;
import sparta.AIBusinessProject.domain.order.dto.OrderResponseDto;
import sparta.AIBusinessProject.domain.order.entity.Order;
import sparta.AIBusinessProject.domain.order.service.OrderService;
import sparta.AIBusinessProject.global.security.UserDetailsImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
// 주문을 생성하고 특정 결제에 대한 주문 목록을 반환하는 역할
public class OrderController {

    private final OrderService orderService;

    // 1. 주문 등록
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_OWNER') or hasRole('ROLE_CUSTOMER')")
    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        OrderResponseDto createdOrder = orderService.createOrder(orderRequestDto, userDetails.getUser()); // 생성된 주문 정보를 OrderResponseDto 형태로 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    // 2. 주문 수정
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_OWNER') or hasRole('ROLE_CUSTOMER')")
    @PatchMapping("/{order_id}")
    public ResponseEntity<OrderResponseDto> updateOrder(
            @PathVariable("order_id") UUID orderId,
            @RequestBody OrderRequestDto orderRequestDto) {

//        // 로그인한 사용자의 ID와 수정 요청한 ID가 일치하는지 확인
//        if (!userDetails.getUser().getUser_id().equals(userDetails.getId())) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }

        OrderResponseDto updatedOrder = orderService.updateOrder(orderId, orderRequestDto);
        return ResponseEntity.ok(updatedOrder);
    }

    // 3. 주문 취소
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_OWNER') or hasRole('ROLE_CUSTOMER')")
    @DeleteMapping("/{order_id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("order_id") UUID orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    // 4. 주문 목록 조회
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_OWNER') or hasRole('ROLE_CUSTOMER')")
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
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_OWNER') or hasRole('ROLE_CUSTOMER')")
    @GetMapping("/{order_id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable UUID order_id) {
        OrderResponseDto order = orderService.getOrder(order_id);
        return ResponseEntity.ok(order);
    }

}
