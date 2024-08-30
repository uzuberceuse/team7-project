package sparta.AIBusinessProject.domain.store.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sparta.AIBusinessProject.domain.store.entity.Store;

import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<Store, UUID>, StoreRepositoryCustom {
}
