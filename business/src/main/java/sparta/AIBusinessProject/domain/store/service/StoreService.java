package sparta.AIBusinessProject.domain.store.service;

import org.springframework.stereotype.Service;
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
    public StoreResponseDto createStore(StoreRequestDto storeRequestDto, String user_id) {
        try {
            return ;
        } catch (Exception e) {
            return null;
        }
    }

    // 가게 수정
    public StoreResponseDto updateStore(StoreRequestDto storeRequestDto, String user_id) {
        try {
            return ;
        } catch (Exception e) {
            return null;
        }
    }

    // 가게 삭제
    public boolean deleteStore(StoreRequestDto storeRequestDto, String user_id) {
        try {

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 가게 목록 조회
    public List<StoreListResponseDto> getStores() {
        return storeRepository.findAll().stream()
                .map(Store::toListResponseDto)
                .collect(Collectors.toList());
    }


    // 가게 상세 조회
    public StoreResponseDto getStore(UUID store_id){
        return ;
    }

}
