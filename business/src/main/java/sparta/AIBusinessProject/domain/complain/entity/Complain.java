package sparta.AIBusinessProject.domain.complain.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @GeneratedValue
    @Column(name = "complain_id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String userName;

    private UUID reviewId;

    private String content;
    private String answer;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
    private Timestamp deletedAt;
    private String deletedBy;



}
