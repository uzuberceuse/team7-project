package sparta.AIBusinessProject.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.AIBusinessProject.domain.user.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository <User, UUID>{
    
    Optional<Object> findByUserEmail(String email);

    Optional<Object> findByUsername(String username);

}
