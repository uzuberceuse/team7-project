package sparta.AIBusinessProject.domain.category.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="p_category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access= AccessLevel.PRIVATE)
public class Category {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @ColumnDefault("random_uuid()")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(unique=true,nullable=false)
    private String categoryName;

    private Boolean isPublic=true;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp created_at;
    private String created_by;

    @UpdateTimestamp
    private Timestamp updated_at;
    private String updated_by;
    private Timestamp deleted_at;
    private String deleted_by;

    public static Category create(String categoryName,String username){
        return Category.builder()
                .categoryName(categoryName)
                .created_at(Timestamp.from(Instant.now()))
                .created_by(username)
                .build();
    }

    // 수정을 한 후 0 -> updated값을 수정하는 메서드
    public void changeUpdated(String updatedBy){
        this.deleted_at=new Timestamp(System.currentTimeMillis()); // 현재 시간으로 설정
        this.deleted_by=updatedBy;
        this.isPublic=false;
    }

    // 삭제 한 후 -> deleted 값을 수정하는 메서드
    public void chanageDeleted(String deletedBy){
        this.deleted_at=new Timestamp(System.currentTimeMillis()); // 현재 시간으로 설정
        this.deleted_by=deletedBy;
        this.isPublic=false;
    }
}
