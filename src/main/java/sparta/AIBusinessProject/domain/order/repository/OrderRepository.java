package sparta.AIBusinessProject.domain.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.AIBusinessProject.domain.complain.entity.Complain;
import sparta.AIBusinessProject.domain.order.entity.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
