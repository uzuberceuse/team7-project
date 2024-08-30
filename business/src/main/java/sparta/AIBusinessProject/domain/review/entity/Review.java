package sparta.AIBusinessProject.domain.review.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import sparta.AIBusinessProject.domain.order.entity.Order;
import sparta.AIBusinessProject.domain.review.dto.ReviewDto;
import sparta.AIBusinessProject.domain.store.entity.Store;
import sparta.AIBusinessProject.domain.user.entity.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name="p_review")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
public class Review {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
    @ColumnDefault("random_uuid()")
    @Column(updatable = false, nullable = false)
    private UUID review_id;

    //REVIEW:USER=N:1관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user_id;

    // REVEIW:ORDER = 1:1관계
    @OneToOne
    @JoinColumn(name="order_id")
    private Order order_id;

    // REVIEW:STORE = 1:1 관계
    @OneToOne
    @JoinColumn(name="store_id")
    private Store store_id;

    // 글자 수 제한걸기
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReviewRatingTypeEnum rating;

    private Timestamp created_at;
    private String created_by;
    private Timestamp deleted_at;
    private String deleted_by;
    private Timestamp updated_at;
    private String updated_by;


    // 리뷰 생성 시 생성 일자를 현재 시간으로
    @PrePersist
    protected void onCreate() {
        created_at = Timestamp.valueOf(LocalDateTime.now());
    }

    @PreUpdate
    protected void onUpdate() { updated_at = Timestamp.valueOf(LocalDateTime.now()); }

    @PreRemove
    protected void onDelete() { deleted_at = Timestamp.valueOf(LocalDateTime.now()); }

    // buildup 패턴으로 review 생성
    public static Review createReview(ReviewDto requestDto, String user_id) {
        return Review.builder()
                .order_id(requestDto.getOrder_id())
                .user_id(requestDto.getUser_id())
                .store_id(requestDto.getStore_id())
                .content(requestDto.getContent())
                .rating(requestDto.getRating())
                .created_by(user_id)
                .build();
        return null;
    }

    // buildup 패턴으로 review 수정
    public void updateReview(String content, ReviewRatingTypeEnum rating, String user_id) {
                this.content = content;
                this.rating = rating;
                this.updated_by = user_id;
    }

    // buildup 패턴으로 review 삭제
    public void deleteReview(String user_id) {
        this.deleted_by=user_id;
    }
}
