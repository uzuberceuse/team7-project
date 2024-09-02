package sparta.AIBusinessProject.domain.complain.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sparta.AIBusinessProject.domain.complain.dto.ComplainListResponseDto;
import sparta.AIBusinessProject.domain.complain.dto.ComplainRequestDto;
import sparta.AIBusinessProject.domain.complain.dto.ComplainResponseDto;
import sparta.AIBusinessProject.domain.complain.entity.Complain;
import sparta.AIBusinessProject.domain.complain.repository.ComplainRepository;
import sparta.AIBusinessProject.domain.user.entity.User;
import sparta.AIBusinessProject.domain.user.repository.UserRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComplainService {

    private final ComplainRepository complainRepository;
    private final UserRepository userRepository;

    // 신고접수
    @Transactional
    public ComplainResponseDto createComplain(UUID user_id, ComplainRequestDto requestDto, String createBy) {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 user를 찾을 수 없습니다."));

        // 새로운 Complain 객체를 빌더 패턴으로 생성
        Complain complain = Complain.builder()
                .user(user)
                .complainContent(requestDto.getComplainContent())
                .created_by(createBy)
                .build();

        // 신고를 저장
        complainRepository.save(complain);

        // 저장된 ComplainResponseDto 로반환
        return new ComplainResponseDto(complain);
    }
    
    // 신고삭제
    @Transactional
    public ResponseEntity<Void> deleteComplain(UUID complain_id, String deletedBy) {

        // 글이 있는지 조회
        Complain complain = complainRepository.findById(complain_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다."));

        complain.setDeleted_by(deletedBy);
        complain.setDeleted_at(new Timestamp(System.currentTimeMillis()));
        complainRepository.save(complain);

        return ResponseEntity.noContent().build();


    }

    // 목록조회
    @Transactional
    public Page<ComplainListResponseDto> getComplainList(Pageable pageable) {
        return complainRepository.findAll(pageable).map(complain -> new ComplainListResponseDto(
                complain.getComplain_id(),
                complain.getUser().getUser_id(),
                complain.getCreatedAt(),
                complain.getCreated_by()
        ));
    }

    // 상세조회
    @Transactional
    public ComplainResponseDto getComplainDetail(UUID complain_id, UUID user_id) {

        userRepository.findById(user_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저의 신고가 없습니다."));

        Complain complain =complainRepository.findById(complain_id).orElseThrow(()-> new IllegalArgumentException("해당 신고는 존재하지않습니다."));

        return new ComplainResponseDto(complain);
    }

    // 답변달기
    public ComplainResponseDto answerComplain(UUID complainId, ComplainRequestDto requestDto, String updatedBy) {
        
//        // 고객센터 신고 id로 유무확인
//        Complain complain = complainRepository.findById(complainId)
//                .orElseThrow(()-> new IllegalArgumentException("해당 신고글은 존재하지 않습니다."));

//        if(StringUtils.hasText(requestDto.getComplainContent())){
//            complain.setComplainContent(requestDto.getComplainContent());
//        }
//
//        complain.setUpdated_at(new Timestamp(System.currentTimeMillis()));
//        complain.setUpdated_by(updatedBy);

        Optional<Complain> optionalComplain = complainRepository.findById(complainId);

        if (optionalComplain.isPresent()) {
            Complain complain = optionalComplain.get();
            complain.setAnswer(requestDto.getAnswer());
            complain.setUpdated_at(new Timestamp(System.currentTimeMillis()));
            complain.setUpdated_by(updatedBy);
            Complain updatedComplain = complainRepository.save(complain);

            return new ComplainResponseDto(updatedComplain);
        } else {
            throw new RuntimeException("Complain not found with id: " + complainId);
        }
    }

}
