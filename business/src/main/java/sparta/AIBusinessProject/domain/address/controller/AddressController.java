package sparta.AIBusinessProject.domain.address.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.AIBusinessProject.domain.address.dto.AddressResponseDto;
import sparta.AIBusinessProject.domain.address.dto.CreateAddressRequestDto;
import sparta.AIBusinessProject.domain.address.service.AddressService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    // 배송지 등록
    @PostMapping
    public ResponseEntity<AddressResponseDto> createAddress(
            final @RequestBody CreateAddressRequestDto request
    ){
        AddressResponseDto address=addressService.createAddress(request);
        return ResponseEntity.ok(address);
    }
    // 배송지 목록 조회
    @GetMapping
    public ResponseEntity<List<AddressResponseDto>>getAddresses(){
        List<AddressResponseDto> addresses=addressService.getAddresses();
        return ResponseEntity.ok(addresses);
    }

    // 배송지 수정
    @PutMapping("/{addressId}")
    public ResponseEntity<Void> updateAddress(@PathVariable UUID addressId) {
        addressService.updateCategory(addressId);
        return null;
    }

    // 배송지 삭제
    @PutMapping("/{addressId}/delete")
    public ResponseEntity<String> deleteAddress(@PathVariable UUID addressId){
        addressService.deleteCategory(addressId);
        return ResponseEntity.ok("Category deleted successfully.");
    }
}
