package sparta.AIBusinessProject.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;
import sparta.AIBusinessProject.domain.store.dto.StoreListResponseDto;
import sparta.AIBusinessProject.domain.store.dto.StoreRequestDto;
import sparta.AIBusinessProject.domain.store.dto.StoreResponseDto;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STORE_SEQ_GENERATOR")
    @Column(nullable = false, unique = true)
    private String store_id = UUID.randomUUID().toString();

//  질문사항
//  @JoinColumn(name="category_id")
//  private Category category;
    private String category_id;


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

    private LocalDateTime created_at;
    private String created_by;
    private LocalDateTime deleted_at;
    private String deleted_by;
    private LocalDateTime updated_at;
    private String updated_by;



    // 상품 생성 시 생성 일자를 현재 시간으로
    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() { updated_at = LocalDateTime.now(); }

    @PreRemove
    protected void onDelete() { deleted_at = LocalDateTime.now(); }

    // buildup 패턴으로 store 생성
    public static Store createStore(StoreRequestDto requestDto, String user_id){
        return Store.builder()
                .storeName(requestDto.getStoreName())
                .location(requestDto.getLocation())
                .phone(requestDto.getPhone())
                .time(requestDto.getTime())
                .details(requestDto.getDetails())
                .created_by(user_id)
                .build();
    }

    // buildup 패턴으로 store 수정
    public static Store updateStore(StoreRequestDto requestDto, String user_id){
        return Store.builder()
                .storeName(requestDto.getStoreName())
                .location(requestDto.getLocation())
                .phone(requestDto.getPhone())
                .time(requestDto.getTime())
                .details(requestDto.getDetails())
                .updated_by(user_id)
                .build();
    }

    // buildup 패턴으로 store 삭제
    public static Store deleteStore(StoreRequestDto requestDto, String user_id){
        return Store.builder()
                .storeName(requestDto.getStoreName())
                .deleted_by(user_id)
                .build();
    }


    public StoreResponseDto toResponseDto() {
        return new StoreResponseDto(
                this.category_id,
                this.storeName,
                this.time,
                this.location,
                this.phone,
                this.details,
                this.created_at,
                this.created_by,
                this.updated_at,
                this.updated_by,
                this.deleted_at,
                this.deleted_by
        );
    }

    public StoreListResponseDto toListResponseDto() {
        return new StoreListResponseDto(
                this.category_id,
                this.storeName,
                this.location
        );
    }
}
