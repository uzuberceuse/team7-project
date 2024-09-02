package sparta.AIBusinessProject.domain.store.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sparta.AIBusinessProject.domain.category.entity.Category;
import sparta.AIBusinessProject.domain.category.repository.CategoryRepository;
import sparta.AIBusinessProject.domain.store.dto.StoreListResponseDto;
import sparta.AIBusinessProject.domain.store.dto.StoreRequestDto;
import sparta.AIBusinessProject.domain.store.dto.StoreResponseDto;
import sparta.AIBusinessProject.domain.store.entity.Store;
import sparta.AIBusinessProject.domain.store.repository.StoreRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StoreService {


    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;

    public StoreService(StoreRepository storeRepository, CategoryRepository categoryRepository) {
        this.storeRepository = storeRepository;
        this.categoryRepository = categoryRepository;
    }


    // 가게 등록
    @Transactional
    public StoreResponseDto createStore(StoreRequestDto requestDto, String userId) {
        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        Store store = Store.createStore(requestDto, category, userId);

        return StoreResponseDto.toResponseDto(storeRepository.save(store));
    }

    // 가게 수정
    @Transactional
    public StoreResponseDto updateStore(StoreRequestDto requestDto, UUID storeId, String  userId) {
        Store store = storeRepository.findById(storeId)
                .filter(p -> p.getDeleted_at() == null)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 가게를 찾을 수 없습니다."));

        store.updateStore(requestDto.getStoreName(), requestDto.getLocation(), requestDto.getPhone(), requestDto.getTime(), requestDto.getDetails(), userId);

        return StoreResponseDto.toResponseDto(store);
    }

    // 가게 삭제
    // 실제로 DB에서 삭제하는 것이 아닌 삭제 필드에 데이터가 들어가면 삭제라고 판단
    // 조회 시 시간값이 있다면 삭제된 것으로 판단하겠음
    @Transactional
    public boolean deleteStore(UUID storeId, String userId) {
        try {
            Store store = storeRepository.findById(storeId)
                    .filter(p -> p.getDeleted_at() == null)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 가게를 찾을 수 없습니다."));

            store.deleteStore(userId);
            return true;
        } catch (Exception e) { return false; }
    }

    // 가게 목록 조회
    // 페이징 적용
    public Page<StoreListResponseDto> getStore(StoreListResponseDto listResponseDto, int page, int size, String sortBy, boolean isAsc) {

        // 페이지 정렬
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);

        // 페이징 처리
        Pageable pageable = PageRequest.of(page, size, sort);

        return storeRepository.findAll(pageable).map(StoreListResponseDto::new);
    }

    // 가게 상세 조회
    @Transactional(readOnly = true)
    public StoreResponseDto getStoreById(UUID storeId){
        Store store = storeRepository.findById(storeId)
                .filter(p -> p.getDeleted_at() == null)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 가게를 찾을 수 없습니다."));
        return StoreResponseDto.toResponseDto(store);
    }
}
