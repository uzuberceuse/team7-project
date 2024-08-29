package sparta.AIBusinessProject.domain.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sparta.AIBusinessProject.domain.report.entity.Report;

import java.util.List;
import java.util.UUID;

public interface ReportRepository extends JpaRepository<Report, UUID> {

    List<Report> findByReviewIdAndUserId(@Param("review_id") UUID review_id, @Param("user_id") UUID user_id);
}
