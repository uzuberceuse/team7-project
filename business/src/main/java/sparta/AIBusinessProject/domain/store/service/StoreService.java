package sparta.AIBusinessProject.domain.store.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sparta.AIBusinessProject.domain.store.dto.StoreListResponseDto;
import sparta.AIBusinessProject.domain.store.dto.StoreRequestDto;
import sparta.AIBusinessProject.domain.store.dto.StoreResponseDto;
import sparta.AIBusinessProject.domain.store.entity.Store;
import sparta.AIBusinessProject.domain.store.repository.StoreRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    // 가게 등록
    @Transactional
    public StoreResponseDto createStore(StoreRequestDto requestDto, String user_id) {
        Store store= Store.createStore(requestDto, user_id);
        Store createdStore = storeRepository.save(store);

        return toResponseDto(createdStore);
    }

    // 가게 수정
    @Transactional
    public StoreResponseDto updateStore(StoreRequestDto requestDto, UUID store_id, String user_id) {
        Store store = storeRepository.findById(store_id)
                .filter(p -> p.getDeleted_at() == null)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 가게를 찾을 수 없습니다."));

        store.updateStore(requestDto.getStoreName(), requestDto.getLocation(), requestDto.getPhone(), requestDto.getTime(), requestDto.getDetails(), user_id);
        Store updatedStore = storeRepository.save(store);

        return toResponseDto(updatedStore);
    }

    // 가게 삭제
    // 실제로 DB에서 삭제하는 것이 아닌 삭제 필드에 데이터가 들어가면 삭제라고 판단
    // 조회 시 시간값이 있다면 삭제된 것으로 판단하겠음
    @Transactional
    public boolean deleteStore(UUID store_id, String user_id) {
        try {
            Store store = storeRepository.findById(store_id)
                    .filter(p -> p.getDeleted_at() == null)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 가게를 찾을 수 없습니다."));

            store.deleteStore(user_id);
            storeRepository.save(store);
            return true;
        } catch (Exception e) { return false; }
    }

    // 가게 목록 조회
    // 페이징 적용
    public Page<StoreListResponseDto> getStores(StoreListResponseDto listResponseDto, Pageable pageable) {
        return storeRepository.getStores(listResponseDto, pageable);
    }

    // 가게 상세 조회
    @Transactional(readOnly = true)
    public StoreResponseDto getStoreById(UUID store_id){
        Store store = storeRepository.findById(store_id)
                .filter(p -> p.getDeleted_at() == null)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 가게를 찾을 수 없습니다."));
        return toResponseDto(store);
    }

    public StoreResponseDto toResponseDto(Store store) {
        return new StoreResponseDto(
                store.getCategory_id(),
                store.getStoreName(),
                store.getTime(),
                store.getLocation(),
                store.getPhone(),
                store.getDetails(),
                store.getCreated_at(),
                store.getCreated_by(),
                store.getUpdated_at(),
                store.getUpdated_by(),
                store.getDeleted_at(),
                store.getDeleted_by()
        );
    }
}
