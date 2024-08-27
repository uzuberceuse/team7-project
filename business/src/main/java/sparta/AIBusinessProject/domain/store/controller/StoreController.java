package sparta.AIBusinessProject.domain.store.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.AIBusinessProject.domain.store.dto.StoreListResponseDto;
import sparta.AIBusinessProject.domain.store.dto.StoreRequestDto;
import sparta.AIBusinessProject.domain.store.dto.StoreResponseDto;
import sparta.AIBusinessProject.domain.store.service.StoreService;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }


    /* 가게 등록
       등록 결과 확인
    */
    @PostMapping("/{store_id}")
    public StoreResponseDto createStore(@PathVariable String store_id,
                                        @RequestBody StoreRequestDto storeRequestDto,
                                        @RequestHeader String user_id) {
            return ;
    }

    /* 가게 수정
       수정 결과 확인
    */
    @PatchMapping("/{store_id}")
    public StoreResponseDto updateStore(@PathVariable String store_id,
                                        @RequestBody StoreRequestDto storeRequestDto,
                                        @RequestHeader String user_id) {
        return ;
    }

    /* 가게 삭제
       삭제 결과는 T/F
    */
    @DeleteMapping("/{store_id}")
    public Boolean deleteStore(@PathVariable String store_id) {

        return ;
    }


    // 가게 목록 조회
    @GetMapping
    public ResponseEntity<List<StoreListResponseDto>> getStores() {
        return ;
    }


    // 가게 상세 조회
    @GetMapping("/{store_id}")
    public StoreResponseDto getStore() {
        return ;
    }
}
