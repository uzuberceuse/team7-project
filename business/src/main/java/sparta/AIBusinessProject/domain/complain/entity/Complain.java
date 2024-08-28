package sparta.AIBusinessProject.domain.complain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @ColumnDefault("random_uuid()")
    @Column(name = "complain_id", updatable = false, nullable = false)
    private UUID id; // 신고id

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // 외래 키 컬럼
    private User user; // 신고자 id

    private String content; // 신고내용
    private String answer; // 관리자가 신고를 답변
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
    private Timestamp deletedAt;
    private String deletedBy;



}
