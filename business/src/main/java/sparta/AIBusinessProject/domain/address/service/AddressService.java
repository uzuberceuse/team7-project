package sparta.AIBusinessProject.domain.address.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.AIBusinessProject.domain.address.dto.AddressResponseDto;
import sparta.AIBusinessProject.domain.address.dto.CreateAddressRequestDto;
import sparta.AIBusinessProject.domain.address.repository.AddressRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    // 주소 조회 비즈니스 로직
    public List<AddressResponseDto> getAddresses() {
        return null;
    }

    // 주소 생성 비즈니스 로직
    public AddressResponseDto createAddress(CreateAddressRequestDto request) {
        return null;
    }

    // 주소 삭제 비즈니스 로직
    public void deleteCategory(UUID addressId) {
    }

    // 주소 수정 비즈니스 로직
    public void updateCategory(UUID addressId) {
    }




}
