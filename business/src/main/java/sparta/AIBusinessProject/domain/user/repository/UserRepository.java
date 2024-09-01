package sparta.AIBusinessProject.domain.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sparta.AIBusinessProject.domain.user.entity.User;
import sparta.AIBusinessProject.domain.user.entity.UserRoleEnum;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository <User, UUID>{
    Optional<Object> findByEmail(String email);
    Optional<Object> findByUsername(String username);

    Page<User> findByRole(UserRoleEnum userRoleEnum, Pageable pageable);
}
