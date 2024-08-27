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
import sparta.AIBusinessProject.domain.notice.dto.NoticeListResponseDto;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComplainService {

    private final ComplainRepository complainRepository;

    public Complain createComplain(UUID userId, ComplainRequestDto requestDto) {
        return null;
    }

    public void deleteReport(UUID userId, String deleteBy) {

    }

    public ComplainResponseDto getComplainDetail(UUID userId) {
        return null;
    }

    @Transactional
    public Page<ComplainListResponseDto> getComplainList(Pageable pageable) {
        return complainRepository.findAll(pageable).map(complain -> new ComplainListResponseDto(
                complain.getId(),
                complain.getUser().getUser_id(),
                complain.getCreatedAt(),
                complain.getCreatedBy()
        ));
    }
}
