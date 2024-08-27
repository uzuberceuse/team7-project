package sparta.AIBusinessProject.domain.notice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sparta.AIBusinessProject.domain.notice.dto.NoticeListResponseDto;
import sparta.AIBusinessProject.domain.notice.dto.NoticeRequestDto;
import sparta.AIBusinessProject.domain.notice.dto.NoticeResponseDto;
import sparta.AIBusinessProject.domain.notice.entity.Notice;
import sparta.AIBusinessProject.domain.notice.repository.NoticeRepository;

import java.sql.Timestamp;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    // 공지사항 등록 (C)
    public NoticeResponseDto createNotice(NoticeRequestDto requestDto) {
        Notice notice = noticeRepository.save(new Notice(requestDto));
        notice.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return new NoticeResponseDto(notice);
    }

    // Notice_id로 조회 (R)
    public Notice getNoticeById(UUID id){
        return noticeRepository.findById(id).orElseThrow(()-> new NullPointerException("해당 공지사항은 존재하지 않습니다."));
    }

    // 수정 공지사항 (U)
    @Transactional
    public NoticeResponseDto updateNotice(UUID id, NoticeRequestDto requestDto){

        // 공지사항id로 공지사항 유무 확인
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("해당 공지사항은 존재하지 않습니다."));

        if(StringUtils.hasText(requestDto.getNoticeTitle())){
            notice.setNoticeTitle(requestDto.getNoticeTitle());
        }

        if(StringUtils.hasText(requestDto.getNoticeContent())){
            notice.setNoticeContent(requestDto.getNoticeContent());
        }

        if(StringUtils.hasText(requestDto.getUpdateBy())){
            notice.setUpdatedBy(requestDto.getUpdateBy());
        }

        // 수정시간 변경
         notice.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        // 수정된 공지사항 정보 dto 반환
        return new NoticeResponseDto(notice);


    }

    // 공지사항 삭제
    public ResponseEntity<Void> deleteNotice(UUID id, String deleteBy) {

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("해당 공지사항은 존재하지 않습니다."));

        notice.setDeletedBy(deleteBy);
        notice.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        noticeRepository.save(notice);

        return ResponseEntity.noContent().build();

    }

    // 공지사항 상세조회
    public NoticeResponseDto getNoticeDetail(UUID id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("해당 공지사항은 존재하지 않습니다."));

        return new NoticeResponseDto(notice);
    }

    // 공지사항 목록조회
    @Transactional
    public Page<NoticeListResponseDto> getNoticeList(Pageable pageable) {
        return noticeRepository.findAll(pageable).map(notice -> new NoticeListResponseDto(
                notice.getId(),
                notice.getNoticeTitle(),
                notice.getCreatedAt(),
                notice.getCreatedBy()
        ));
    }

}
