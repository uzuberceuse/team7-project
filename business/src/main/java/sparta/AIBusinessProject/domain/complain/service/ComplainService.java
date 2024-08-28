package sparta.AIBusinessProject.domain.complain.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sparta.AIBusinessProject.domain.complain.dto.ComplainListResponseDto;
import sparta.AIBusinessProject.domain.complain.dto.ComplainRequestDto;
import sparta.AIBusinessProject.domain.complain.dto.ComplainResponseDto;
import sparta.AIBusinessProject.domain.complain.entity.Complain;
import sparta.AIBusinessProject.domain.complain.repository.ComplainRepository;
import sparta.AIBusinessProject.domain.user.entity.User;
import sparta.AIBusinessProject.domain.user.repository.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComplainService {

    private final ComplainRepository complainRepository;
    private final UserRepository userRepository;

    // 신고접수
    @Transactional
    public ComplainResponseDto createComplain(UUID userId, ComplainRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 user를 찾을 수 없습니다."));

        // 새로운 Complain 객체를 빌더 패턴으로 생성
        Complain complain = Complain.builder()
                .user(user)
                .content(requestDto.getContent())
                .createdBy(requestDto.getCreatedBy())
                .build();

        // 신고를 저장
        complainRepository.save(complain);

        // 저장된 Complain 객체반환
        return new ComplainResponseDto(complain);
    }
    
    // 신고삭제
    @Transactional
    public void deleteComplain(UUID userId) {

        // userId의 사용자의 신고를 조회
        Complain complain = complainRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저의 신고가 없습니다."));

        complainRepository.delete(complain);
    }

    // 목록조회
    @Transactional
    public Page<ComplainListResponseDto> getComplainList(Pageable pageable) {
        return complainRepository.findAll(pageable).map(complain -> new ComplainListResponseDto(
                complain.getId(),
                complain.getUser().getUser_id(),
                complain.getCreatedAt(),
                complain.getCreatedBy()
        ));
    }

    // 상세조회
    @Transactional
    public ComplainResponseDto getComplainDetail(UUID userId) {
        Complain complain = complainRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저의 신고가 없습니다."));

        return new ComplainResponseDto(complain);
    }


}
