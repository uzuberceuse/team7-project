package sparta.AIBusinessProject.domain.notice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sparta.AIBusinessProject.domain.notice.entity.Notice;
import sparta.AIBusinessProject.domain.notice.service.NoticeService;

@RestController
@RequestMapping("/api/vi/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    // 공지사항 등록
//    @PostMapping("")
//    public ResponseEntity<Notice> save(@RequestBody Notice notice) {
//
//    }



}
