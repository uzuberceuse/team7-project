package sparta.AIBusinessProject.domain.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.AIBusinessProject.domain.report.entity.Report;

import java.util.List;
import java.util.UUID;

public interface ReportRepository extends JpaRepository<Report, UUID> {

    List<Report> findByReviewAndUser(UUID review, UUID user); // 연관관계를 맺었기 때문에 Entity 를 통한 조회가 가능합니다.
}
