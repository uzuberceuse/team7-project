package sparta.AIBusinessProject.domain.address.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.AIBusinessProject.domain.address.dto.AddressResponseDto;
import sparta.AIBusinessProject.domain.address.dto.CreateAddressRequestDto;
import sparta.AIBusinessProject.domain.address.service.AddressService;
import sparta.AIBusinessProject.domain.complain.dto.ComplainListResponseDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class  AddressController {

    private final AddressService addressService;

    // 배송지 등록
    @PostMapping
    public ResponseEntity<AddressResponseDto> createAddress(
            final @RequestBody CreateAddressRequestDto request,
            @RequestParam("username") String username
    ){
        AddressResponseDto address=addressService.createAddress(request,username);
        return ResponseEntity.ok(address);
    }
    // 배송지 목록 조회
    @GetMapping
    public ResponseEntity<Page<AddressResponseDto>>getAddresses(
            @RequestParam(defaultValue = "0") int page,         // 기본 페이지 번호 0
            @RequestParam(defaultValue = "10") int size,        // 기본 페이지 크기 10
            @RequestParam(defaultValue = "createdAt") String sortBy, // 기본 정렬 필드 createdAt
            @RequestParam(defaultValue = "desc") String direction    // 기본 정렬 방향 desc
    ){
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<AddressResponseDto> responseDtos = addressService.getAddresses(pageable);
        return ResponseEntity.ok(responseDtos);
    }


    // 배송지 수정
    @PutMapping("/{address_id}")
    public ResponseEntity<Void> updateAddress(
            @PathVariable UUID address_id,
            @RequestBody CreateAddressRequestDto request,
            @RequestParam String username
    ) {
        addressService.updateCategory(address_id,request,username);
        return null;
    }

    // 배송지 삭제
    @DeleteMapping("/{address_id}/delete")
    public ResponseEntity<String> deleteAddress(@PathVariable UUID address_id,@RequestParam String username){
        addressService.deleteCategory(address_id,username);
        return ResponseEntity.ok("Category deleted successfully.");
    }
}
