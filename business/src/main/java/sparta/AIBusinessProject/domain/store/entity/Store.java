package sparta.AIBusinessProject.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import sparta.AIBusinessProject.domain.category.entity.Category;
import sparta.AIBusinessProject.domain.store.dto.StoreListResponseDto;
import sparta.AIBusinessProject.domain.store.dto.StoreRequestDto;
import sparta.AIBusinessProject.domain.store.dto.StoreResponseDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name="p_store")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
public class Store {


    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
    @ColumnDefault("random_uuid()")
    @Column(updatable = false, nullable = false)
    private UUID store_id;

    // STORE:CATEGORY=N:1 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category_id;

    @Column(nullable = false, unique = true)
    private String storeName;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String phone;

    // ERD에 추가하기
    @Column
    private String time;

    @Column(nullable = false)
    private String details;

    private Timestamp created_at;
    private String created_by;
    private Timestamp deleted_at;
    private String deleted_by;
    private Timestamp updated_at;
    private String updated_by;



    // 가게 생성 시 생성 일자를 현재 시간으로
    @PrePersist
    protected void onCreate() {
        created_at = Timestamp.valueOf(LocalDateTime.now());
    }

    @PreUpdate
    protected void onUpdate() { updated_at = Timestamp.valueOf(LocalDateTime.now()); }

    @PreRemove
    protected void onDelete() { deleted_at = Timestamp.valueOf(LocalDateTime.now()); }

    // buildup 패턴으로 store 생성
    public static Store createStore(StoreRequestDto requestDto, String user_id){
        return sparta.AIBusinessProject.domain.store.entity.Store.builder()
                .storeName(requestDto.getStoreName())
                .location(requestDto.getLocation())
                .phone(requestDto.getPhone())
                .time(requestDto.getTime())
                .details(requestDto.getDetails())
                .created_by(user_id)
                .build();
    }

    // store 수정
    public void updateStore(String storeName, String location, String phone, String time, String details, String user_id){

                this.storeName = storeName;
                this.location = location;
                this.phone = phone;
                this.time = time;
                this.details = details;
                this.updated_by = user_id;
    }

    public void deleteStore(String user_id){
        this.deleted_by = user_id;
    }
}
