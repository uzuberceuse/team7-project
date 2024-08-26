package sparta.AIBusinessProject.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.AIBusinessProject.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{
}
