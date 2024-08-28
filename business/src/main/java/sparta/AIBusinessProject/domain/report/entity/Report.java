package sparta.AIBusinessProject.domain.report.entity;

import jakarta.persistence.*;
import lombok.*;
import sparta.AIBusinessProject.domain.report.dto.ReportRequestDto;
import sparta.AIBusinessProject.domain.review.entity.Review;
import sparta.AIBusinessProject.domain.user.entity.User;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "p_report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report {
    public Report(ReportRequestDto requestDto, UUID userId, ReportRequestDto requestDto1) {
    }

    @Id
    @GeneratedValue
    @Column(name="report_id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // 외래 키 컬럼
    private User user;

    @ManyToOne
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    private UUID reviewId;
    private UUID userId;

    private String createBy;
    private String updatedBy;
    private String deletedBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;
    private String title;
    private String content;

}
