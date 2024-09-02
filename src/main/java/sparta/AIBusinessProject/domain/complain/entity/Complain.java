package sparta.AIBusinessProject.domain.complain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import sparta.AIBusinessProject.domain.user.entity.User;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "p_complain")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Complain {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="complain_id", updatable = false, nullable = false)
    private UUID complain_id; // 신고id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // 외래 키 컬럼
    private User user; // 신고자 id

    private String complainContent; // 신고내용
    private String answer; // 관리자가 신고를 답변

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;
    private String created_by;
    private Timestamp updated_at;
    private String updated_by;
    private Timestamp deleted_at;
    private String deleted_by;

}
