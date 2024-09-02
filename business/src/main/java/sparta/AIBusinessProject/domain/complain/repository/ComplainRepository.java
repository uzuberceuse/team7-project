package sparta.AIBusinessProject.domain.complain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.AIBusinessProject.domain.complain.entity.Complain;
import sparta.AIBusinessProject.domain.user.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface ComplainRepository extends JpaRepository<Complain, UUID> {
    //Optional<Complain> findByUserId(UUID userId);
    Optional<Complain> findByUser(User user);

}
