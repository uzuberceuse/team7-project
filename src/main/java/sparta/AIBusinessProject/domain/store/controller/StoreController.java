package sparta.AIBusinessProject.domain.store.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sparta.AIBusinessProject.domain.store.dto.StoreListResponseDto;
import sparta.AIBusinessProject.domain.store.dto.StoreRequestDto;
import sparta.AIBusinessProject.domain.store.dto.StoreResponseDto;
import sparta.AIBusinessProject.domain.store.service.StoreService;
import sparta.AIBusinessProject.global.security.UserDetailsImpl;

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
    @PostMapping
    public StoreResponseDto createStore(@RequestBody StoreRequestDto requestDto,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails){

        if("ROLE_CUSTOMER".equals(userDetails.getUser().getRole())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "접근이 허용되지 않습니다.");
        }

            return storeService.createStore(requestDto, String.valueOf(userDetails.getId()));
    }

    /* 가게 수정
       수정 결과 확인
    */
    @PatchMapping("/{store_id}")
    public StoreResponseDto updateStore(@RequestBody StoreRequestDto requestDto,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails,
                                        @PathVariable UUID store_id) {

        if("ROLE_CUSTOMER".equals(userDetails.getUser().getRole())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "접근이 허용되지 않습니다.");
        }

        return storeService.updateStore(requestDto, store_id, String.valueOf(userDetails.getId()));
    }

    /* 가게 삭제
       삭제 결과는 T/F
    */
    @DeleteMapping("/{store_id}")
    public Boolean deleteStore(@AuthenticationPrincipal UserDetailsImpl userDetails,
                               @PathVariable UUID store_id) {

        if("ROLE_CUSTOMER".equals(userDetails.getUser().getRole())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "접근이 허용되지 않습니다.");
        }

        return storeService.deleteStore(store_id, String.valueOf(userDetails.getId()));
    }


    // 가게 목록 조회
    // 가게 목록 조회
    @GetMapping
    public Page<StoreListResponseDto> getStores(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc,
            StoreListResponseDto listResponseDto) {

        return storeService.getStore(listResponseDto, page, size, sortBy, isAsc);
    }


    // 가게 상세 조회
    @GetMapping("/{store_id}")
    public StoreResponseDto getStore(@PathVariable UUID store_id) {

        return storeService.getStoreById(store_id);
    }
}
