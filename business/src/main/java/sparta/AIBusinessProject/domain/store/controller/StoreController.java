package sparta.AIBusinessProject.domain.store.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sparta.AIBusinessProject.domain.store.dto.StoreListResponseDto;
import sparta.AIBusinessProject.domain.store.dto.StoreRequestDto;
import sparta.AIBusinessProject.domain.store.dto.StoreResponseDto;
import sparta.AIBusinessProject.domain.store.service.StoreService;

import java.util.List;
import java.util.UUID;

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
    /* 아직 gateway를 확인하지 않아 임시로 코드를 짬
           기본적으로 로그인한 상태에서 user_id와 role 체크
    */
    @PostMapping
    public StoreResponseDto createStore(@RequestBody StoreRequestDto requestDto,
                                        @RequestHeader(value = "X-User-Id") String user_id,
                                        @RequestHeader(value = "X-Role") String role){
        // MANAGER, STORE 권한을 가져야만 create 가능
        if(!"MANGER".equals(role) && !"STORE".equals(role)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "접근이 허용되지 않습니다.");
        }
            return storeService.createStore(requestDto, user_id);
    }

    /* 가게 수정
       수정 결과 확인
    */
    @PatchMapping("/{store_id}")
    public StoreResponseDto updateStore(@RequestBody StoreRequestDto requestDto,
                                        @RequestHeader(value = "X-User-Id") String user_id,
                                        @RequestHeader(value = "X-Role") String role,
                                        @PathVariable UUID store_id) {

        // MANAGER, STORE 권한을 가져야만 update 가능
        if(!"MANGER".equals(role) && !"STORE".equals(role)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "접근이 허용되지 않습니다.");
        }

        return storeService.updateStore(requestDto, store_id, user_id);
    }

    /* 가게 삭제
       삭제 결과는 T/F
    */
    @DeleteMapping("/{store_id}")
    public Boolean deleteStore(@RequestHeader(value = "X-User-Id") String user_id,
                               @RequestHeader(value = "X-Role") String role,
                               @PathVariable UUID store_id) {

        // MANAGER, STORE 권한을 가져야만 delete 가능
        if(!"MANGER".equals(role) && !"STORE".equals(role)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "접근이 허용되지 않습니다.");
        }

        return storeService.deleteStore(store_id, user_id);
    }


    // 가게 목록 조회
    @GetMapping
    public Page<StoreListResponseDto> getStores(StoreListResponseDto listResponseDto, Pageable pageable) {

        return storeService.getStores(listResponseDto, pageable);
    }


    // 가게 상세 조회
    @GetMapping("/{store_id}")
    public StoreResponseDto getStore(@PathVariable UUID store_id) {

        return storeService.getStoreById(store_id);
    }
}
