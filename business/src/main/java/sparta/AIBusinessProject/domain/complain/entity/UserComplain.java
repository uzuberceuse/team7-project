package sparta.AIBusinessProject.domain.complain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.UUID;

@Entity
@Table(name = "p_user_complain")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserComplain {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "complain_id", nullable = false)
    private UUID complainId;

}
