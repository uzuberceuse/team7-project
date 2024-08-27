package sparta.AIBusinessProject.domain.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sparta.AIBusinessProject.domain.store.dto.StoreListResponseDto;

public interface StoreRepositoryCustom {

    // 가게 조회 시 페이징 적용
    Page<StoreListResponseDto> getStores(String storeName, Pageable pageable);
}
