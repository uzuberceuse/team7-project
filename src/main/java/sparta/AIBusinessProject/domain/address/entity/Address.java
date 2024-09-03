package sparta.AIBusinessProject.domain.address.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import sparta.AIBusinessProject.domain.category.entity.Category;
import sparta.AIBusinessProject.domain.user.entity.User;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="p_address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access= AccessLevel.PRIVATE)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    private String addressName;
    private String zipcode;

    // user:address=1:N
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder.Default
    private Boolean isPublic=true;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
    private Timestamp deletedAt;
    private String deletedBy;

    public static Address create(String addressName,String zipcode,String username){
        return Address.builder()
                .addressName(addressName)
                .zipcode(zipcode)
                .createdAt(Timestamp.from(Instant.now()))
                .createdBy(username)
                .build();
    }

    // 수정을 한 후 0 -> updated값을 수정하는 메서드
    public void changeUpdated(String updatedBy){
        this.deletedAt=new Timestamp(System.currentTimeMillis()); // 현재 시간으로 설정
        this.deletedBy=updatedBy;
        this.isPublic=false;
    }

    // 삭제 한 후 -> deleted 값을 수정하는 메서드
    public void chanageDeleted(String deletedBy){
        this.deletedAt=new Timestamp(System.currentTimeMillis()); // 현재 시간으로 설정
        this.deletedBy=deletedBy;
        this.isPublic=false;
    }
}
