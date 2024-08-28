package sparta.AIBusinessProject.domain.payment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.AIBusinessProject.domain.payment.dto.PaymentListResponseDto;
import sparta.AIBusinessProject.domain.payment.dto.PaymentRequestDto;
import sparta.AIBusinessProject.domain.payment.dto.PaymentResponseDto;
import sparta.AIBusinessProject.domain.payment.service.PaymentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // 1. 결제 생성
    @PostMapping
    public ResponseEntity<PaymentResponseDto> createPayment(@RequestBody PaymentRequestDto requestDto) {
        PaymentResponseDto responseDto = paymentService.createPayment(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 2. 결제 취소
    @DeleteMapping("/{payment_id}")
    public ResponseEntity<Void> cancelPayment(@PathVariable UUID payment_id) {
        paymentService.cancelPayment(payment_id);
        return ResponseEntity.noContent().build();
    }

    // 3. 결제 개별 정보 조회
    @GetMapping("/{payment_id}")
    public ResponseEntity<PaymentResponseDto> getPayment(@PathVariable UUID payment_id) {
        PaymentResponseDto responseDto = paymentService.getPayment(payment_id);
        return ResponseEntity.ok(responseDto);
    }

    // 4. 결제 목록 조회
    @GetMapping
    public ResponseEntity<List<PaymentListResponseDto>> getAllPayments() {
        List<PaymentListResponseDto> responseDto = paymentService.getAllPayments();
        return ResponseEntity.ok(responseDto);
    }
}
