package sparta.AIBusinessProject.domain.payment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparta.AIBusinessProject.domain.payment.dto.PaymentRequestDto;
import sparta.AIBusinessProject.domain.payment.dto.PaymentResponseDto;
import sparta.AIBusinessProject.domain.payment.service.PaymentService;
import sparta.AIBusinessProject.global.security.UserDetailsImpl;

import java.util.UUID;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // 1. 결제 생성
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_CUSTOMER')")
    @PostMapping
    public ResponseEntity<PaymentResponseDto> createPayment(@RequestBody PaymentRequestDto requestDto,
                                                            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        PaymentResponseDto responseDto = paymentService.createPayment(requestDto, userDetails.getId());
        return ResponseEntity.ok(responseDto);
    }

    // 2. 결제 취소
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_CUSTOMER')")
    @DeleteMapping("/{payment_id}")
    public ResponseEntity<Void> cancelPayment(@PathVariable UUID payment_id) {
        paymentService.cancelPayment(payment_id);
        return ResponseEntity.noContent().build();
    }

    // 3. 결제 개별 정보 조회
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_CUSTOMER')")
    @GetMapping("/{payment_id}")
    public ResponseEntity<PaymentResponseDto> getPayment(@PathVariable UUID payment_id) {
        PaymentResponseDto responseDto = paymentService.getPayment(payment_id);
        return ResponseEntity.ok(responseDto);
    }

    // 4. 결제 목록 조회
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_CUSTOMER')")
    @GetMapping
    public ResponseEntity<Page<PaymentResponseDto>> getPaymentList(
            @RequestParam(defaultValue = "0") int page,         // 기본 페이지 번호 0
            @RequestParam(defaultValue = "10") int size,        // 기본 페이지 크기 10
            @RequestParam(defaultValue = "createdAt") String sortBy, // 기본 정렬 필드 createdAt
            @RequestParam(defaultValue = "desc") String direction    // 기본 정렬 방향 desc
            ) {

        // 페이지 요청 생성
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<PaymentResponseDto> responseDto = paymentService.getPaymentList(pageable);
        return ResponseEntity.ok(responseDto);
    }
}
