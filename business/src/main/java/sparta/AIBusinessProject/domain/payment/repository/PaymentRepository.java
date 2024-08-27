package sparta.AIBusinessProject.domain.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.AIBusinessProject.domain.payment.entity.Payment;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository <Payment, UUID>{
}
