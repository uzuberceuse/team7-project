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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "category_id", updatable = false, nullable = false)
    private UUID categoryId;

    @Column(unique=true,nullable=false)
    private String categoryName;

    @Builder.Default
    private Boolean isPublic=true;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;
    private String createdBy;

    @UpdateTimestamp
    private Timestamp updatedAt;
    private String updatedBy;
    private Timestamp deletedAt;
    private String deletedBy;

    public static Category create(String categoryName,String username){
        return Category.builder()
                .categoryName(categoryName)
                .createdAt(Timestamp.from(Instant.now()))
                .createdBy(username)
                .build();
    }

    // 수정을 한 후 -> updated값을 수정하는 메서드
    public void changeUpdated(String updatedBy){
        this.updatedAt=new Timestamp(System.currentTimeMillis()); // 현재 시간으로 설정
        this.updatedBy=updatedBy;
        this.isPublic=false;
    }

    // 삭제 한 후 -> deleted 값을 수정하는 메서드
    public void chanageDeleted(String deletedBy){
        this.deletedAt=new Timestamp(System.currentTimeMillis()); // 현재 시간으로 설정
        this.deletedBy=deletedBy;
        this.isPublic=false;
    }
}
