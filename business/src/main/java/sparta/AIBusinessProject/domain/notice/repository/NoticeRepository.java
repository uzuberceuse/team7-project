package sparta.AIBusinessProject.domain.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.AIBusinessProject.domain.notice.entity.Notice;

import java.util.UUID;

public interface NoticeRepository extends JpaRepository<Notice, UUID> {




}
