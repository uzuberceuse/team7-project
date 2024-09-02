package sparta.AIBusinessProject.domain.report.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
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

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "report_id", updatable = false, nullable = false)
    private UUID reportId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // 외래 키 컬럼
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false) // 외래 키 컬럼
    private Review review;


    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp created_at;
    private String created_by;
    private Timestamp updated_at;
    private String updated_by;
    private Timestamp deleted_at;
    private String deleted_by;

    private String reportTitle;
    private String reportContent;


    public static Report createReport(ReportRequestDto requestDto, User user, Review review) {
        return Report.builder()
                .user(user)
                .review(review)
                .reportTitle(requestDto.getTitle())
                .reportContent(requestDto.getContent())
                .created_at(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public static Report from(ReportRequestDto requestDto, User user, Review review) {
        return Report.builder()
                .user(user)
                .review(review)
                .reportTitle(requestDto.getTitle())
                .reportContent(requestDto.getContent())
                .created_at(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
