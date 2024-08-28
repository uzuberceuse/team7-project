package sparta.AIBusinessProject.domain.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.AIBusinessProject.domain.report.entity.Report;

import java.util.UUID;

public interface ReportRepository extends JpaRepository<Report, UUID> {
}
