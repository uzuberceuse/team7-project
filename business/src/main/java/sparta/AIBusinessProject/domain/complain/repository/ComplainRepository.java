package sparta.AIBusinessProject.domain.complain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.AIBusinessProject.domain.complain.entity.Complain;

import java.util.UUID;

public interface ComplainRepository extends JpaRepository<Complain, UUID> {
}
