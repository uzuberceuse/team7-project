package sparta.AIBusinessProject.domain.address.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.AIBusinessProject.domain.address.dto.AddressResponseDto;
import sparta.AIBusinessProject.domain.address.dto.CreateAddressRequestDto;
import sparta.AIBusinessProject.domain.address.entity.Address;
import sparta.AIBusinessProject.domain.address.repository.AddressRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    // 주소 조회 비즈니스 로직
    public Page<AddressResponseDto> getAddresses(Pageable pageable) {
        return addressRepository.findAll(pageable).map(address -> new AddressResponseDto(
                address.getAddressName(),
                address.getZipcode()
        ));
    }

    // 주소 생성 비즈니스 로직
    public AddressResponseDto createAddress(CreateAddressRequestDto request,String username) {
        Address address=addressRepository.save(Address.create(request.getName(),request.getZipcode(),username));
        return AddressResponseDto.of(address);

    }

    // 주소 삭제 비즈니스 로직
    public void deleteCategory(UUID addressId,String deletedBy) {
        Address address=addressRepository.findById(addressId)
                .orElseThrow(()->new IllegalArgumentException("Category Not Found"));

        address.chanageDeleted(deletedBy);
        addressRepository.save(address);
    }

    // 주소 수정 비즈니스 로직
    @Transactional
    public void updateCategory(
            UUID addressId,
            CreateAddressRequestDto request,
            String updatedBy
            ) {
        // 기존 주소 조회
        Address address=addressRepository.findById(addressId)
                .orElseThrow(()->new IllegalArgumentException("category not found"));
        // 업데이트
        address.setAddressName(request.getName());
        address.setZipcode(request.getZipcode());
        address.changeUpdated(updatedBy);
        // 변경사항 저장
        addressRepository.save(address);
    }




}
