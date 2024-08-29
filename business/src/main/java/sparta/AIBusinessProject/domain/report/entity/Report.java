package sparta.AIBusinessProject.domain.report.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @ColumnDefault("random_uuid()")
    @Column(name="report_id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // 외래 키 컬럼
    private User user;

    @ManyToOne
    @JoinColumn(name = "review_id", nullable = false) // 외래 키 컬럼
    private Review review;


    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "deleted_by")
    private String deletedBy;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    private String title;
    private String content;


    public static Report createReport(ReportRequestDto requestDto, User user, Review review) {
        return Report.builder()
                .user(user)
                .review(review)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public static Report from(ReportRequestDto requestDto, User user, Review review) {
        return Report.builder()
                .user(user)
                .review(review)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
