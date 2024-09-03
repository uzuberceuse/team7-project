package sparta.AIBusinessProject.domain.notice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sparta.AIBusinessProject.domain.notice.dto.NoticeListResponseDto;
import sparta.AIBusinessProject.domain.notice.dto.NoticeRequestDto;
import sparta.AIBusinessProject.domain.notice.dto.NoticeResponseDto;
import sparta.AIBusinessProject.domain.notice.entity.Notice;
import sparta.AIBusinessProject.domain.notice.repository.NoticeRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    // 공지사항 등록 (C)
    @Transactional
    public NoticeResponseDto createNotice(NoticeRequestDto requestDto, String createdBy) {
        Notice notice = Notice.from(requestDto);
        notice.setCreated_by(createdBy);
        notice.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        notice = noticeRepository.save(notice);
        return new NoticeResponseDto(notice);
    }

    // Notice_id로 조회 (R)
    public Notice getNoticeById(UUID notice_id){
        return noticeRepository.findById(notice_id)
                .orElseThrow(()-> new NullPointerException("해당 공지사항은 존재하지 않습니다."));
    }

    // 수정 공지사항 (U)
    @Transactional
    public NoticeResponseDto updateNotice(UUID notice_id, NoticeRequestDto requestDto, String updatedBy){

        // 공지사항id로 공지사항 유무 확인
        Notice notice = noticeRepository.findById(notice_id)
                .orElseThrow(()-> new RuntimeException("해당 공지사항은 존재하지 않습니다."));

        // StringUtils.hasText -> 문자열 유효성 검증 메소드
        // 문자열이 유효 할 경우에만 수행 => 공백을 제외하고 길이가 1이상인 경우.
        if(StringUtils.hasText(requestDto.getNoticeTitle())){
            notice.setNoticeTitle(requestDto.getNoticeTitle());
        }

        if(StringUtils.hasText(requestDto.getNoticeContent())){
            notice.setNoticeContent(requestDto.getNoticeContent());
        }

        if(StringUtils.hasText(requestDto.getUpdate_by())){
            notice.setUpdated_by(requestDto.getUpdate_by());
        }

        // 수정시간 변경
         notice.setUpdated_at(new Timestamp(System.currentTimeMillis()));

        notice.setUpdated_by(updatedBy);
        notice.setCreated_by(updatedBy);

        // 수정된 공지사항 정보 dto 반환
        return new NoticeResponseDto(notice);


    }

    // 공지사항 삭제
    public ResponseEntity<Void> deleteNotice(UUID notice_id, String deleteBy) {

        Notice notice = noticeRepository.findById(notice_id)
                .orElseThrow(()-> new RuntimeException("해당 공지사항은 존재하지 않습니다."));

        // 삭제한 시간, 삭제한 사람 확인
        notice.setDeleted_by(deleteBy);
        notice.setDeleted_at(new Timestamp(System.currentTimeMillis()));
        noticeRepository.save(notice);

        return ResponseEntity.noContent().build();
    }

    // 공지사항 상세조회
    public NoticeResponseDto getNoticeDetail(UUID notice_id) {
        Notice notice = noticeRepository.findById(notice_id)
                .orElseThrow(()-> new RuntimeException("해당 공지사항은 존재하지 않습니다."));
        return new NoticeResponseDto(notice);
    }

    // 공지사항 목록조회
    @Transactional
    public Page<NoticeListResponseDto> getNoticeList(Pageable pageable) {
        return noticeRepository.findAll(pageable)
                .map(notice -> new NoticeListResponseDto(notice));
    }
}
