package sparta.AIBusinessProject.domain.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.AIBusinessProject.domain.category.entity.Category;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
