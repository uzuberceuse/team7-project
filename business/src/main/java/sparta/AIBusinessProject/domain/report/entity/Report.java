package sparta.AIBusinessProject.domain.report.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @GeneratedValue
    @Column(name="report_id", updatable = false, nullable = false)
    private UUID id;

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
