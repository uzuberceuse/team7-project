package sparta.AIBusinessProject.domain.address.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import sparta.AIBusinessProject.domain.category.entity.Category;
import sparta.AIBusinessProject.domain.user.entity.User;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name="p_address")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access= AccessLevel.PRIVATE)
public class Address {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @ColumnDefault("random_uuid()")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String addressName;
    private String zipcode;

    // user:address=1:N
    @ManyToOne
    @JoinColumn(name="user_id")
    private User uer;

    private Timestamp created_at;
    private String created_by;
    private Timestamp updated_at;
    private String updated_by;
    private Timestamp deleted_at;
    private String deleted_by;
}
