package sparta.AIBusinessProject.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import sparta.AIBusinessProject.domain.category.entity.Category;
import sparta.AIBusinessProject.domain.store.dto.StoreRequestDto;
import sparta.AIBusinessProject.domain.store.dto.StoreResponseDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Getter
@Entity
@Table(name="p_store")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
public class Store {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID storeId;

    // STORE:CATEGORY=N:1 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

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

    // buildup 패턴으로 store 생성
    public static Store createStore(StoreRequestDto requestDto, Category category, String userId){
        return Store.builder()
                .storeName(requestDto.getStoreName())
                .location(requestDto.getLocation())
                .phone(requestDto.getPhone())
                .time(requestDto.getTime())
                .details(requestDto.getDetails())
                .created_by(userId)
                .category(category) // 추가
                .build();
    }

    // store 수정
    public void updateStore(String storeName, String location, String phone, String time, String details, String userId){
        this.storeName = storeName;
        this.location = location;
        this.phone = phone;
        this.time = time;
        this.details = details;
        this.updated_by = userId;
    }

    public void deleteStore(String userId){
        this.deleted_by = userId;
        this.deleted_at = Timestamp.valueOf(LocalDateTime.now());
    }
}