package sparta.AIBusinessProject;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import sparta.AIBusinessProject.domain.notice.dto.NoticeRequestDto;
import sparta.AIBusinessProject.domain.notice.dto.NoticeResponseDto;
import sparta.AIBusinessProject.domain.notice.entity.Notice;
import sparta.AIBusinessProject.domain.notice.repository.NoticeRepository;
import sparta.AIBusinessProject.domain.notice.service.NoticeService;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@Nested
@DisplayName("주제 별로 테스트를 그룹지어서 파악하기 좋습니다.")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@Transactional
public class NoticeServiceTest {

    @Autowired
    private NoticeRepository noticeRepository;

    private NoticeService noticeService;

    @BeforeEach
    void setUp() {
        noticeService = new NoticeService(noticeRepository);
    }

    @Test
    void testCreateNotice() {
        NoticeRequestDto requestDto = NoticeRequestDto.builder()
                .noticeTitle("Test Notice")
                .noticeContent("This is a test notice content.")
                .created_by("Test User")
                .build();

        NoticeResponseDto responseDto = noticeService.createNotice(requestDto);

        assertThat(responseDto.getNotice_id()).isNotNull();
        assertThat(responseDto.getNoticeTitle()).isEqualTo("Test Notice");
        assertThat(responseDto.getNoticeContent()).isEqualTo("This is a test notice content.");
        assertThat(responseDto.getCreated_by()).isEqualTo("Test User");
    }

    @Test
    void testUpdateNotice() {
        Notice notice = Notice.builder()
                .noticeTitle("Initial Title")
                .noticeContent("Initial Content")
                .created_by("Test User")
                .build();
        noticeRepository.save(notice);

        NoticeRequestDto requestDto = NoticeRequestDto.builder()
                .noticeTitle("Updated Title")
                .noticeContent("Updated Content")
                .update_by("Updated User")
                .build();

        NoticeResponseDto responseDto = noticeService.updateNotice(notice.getNotice_id(), requestDto);

        assertThat(responseDto.getNoticeTitle()).isEqualTo("Updated Title");
        assertThat(responseDto.getNoticeContent()).isEqualTo("Updated Content");
        assertThat(responseDto.getUpdated_by()).isEqualTo("Updated User");
    }

    @Test
    void testDeleteNotice() {
        Notice notice = Notice.builder()
                .noticeTitle("Title to Delete")
                .noticeContent("Content to Delete")
                .created_by("Test User")
                .build();
        noticeRepository.save(notice);

        noticeService.deleteNotice(notice.getNotice_id(), "Deleted User");

        Optional<Notice> deletedNotice = noticeRepository.findById(notice.getNotice_id());
        assertThat(deletedNotice).isPresent();
        assertThat(deletedNotice.get().getDeleted_by()).isEqualTo("Deleted User");
    }

    @Test
    void testGetNoticeDetail() {
        Notice notice = Notice.builder()
                .noticeTitle("Detail Title")
                .noticeContent("Detail Content")
                .created_by("Test User")
                .build();
        noticeRepository.save(notice);

        NoticeResponseDto responseDto = noticeService.getNoticeDetail(notice.getNotice_id());

        assertThat(responseDto.getNoticeTitle()).isEqualTo("Detail Title");
        assertThat(responseDto.getNoticeContent()).isEqualTo("Detail Content");
        assertThat(responseDto.getCreated_by()).isEqualTo("Test User");
    }

    @Test
    void testGetNoticeById_ThrowsException() {
        UUID nonExistentId = UUID.randomUUID();
        assertThrows(RuntimeException.class, () -> {
            noticeService.getNoticeById(nonExistentId);
        });
    }
}
