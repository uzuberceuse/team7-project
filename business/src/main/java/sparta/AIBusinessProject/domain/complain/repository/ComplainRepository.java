package sparta.AIBusinessProject.domain.complain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.AIBusinessProject.domain.complain.entity.Complain;

import java.util.Optional;
import java.util.UUID;

public interface ComplainRepository extends JpaRepository<Complain, UUID> {
    // 사용자 ID로 신고 조회
    Optional<Complain> findByUserId(UUID userId);
}
