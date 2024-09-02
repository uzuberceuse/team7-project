package sparta.AIBusinessProject.domain.review.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import sparta.AIBusinessProject.domain.order.entity.Order;
import sparta.AIBusinessProject.domain.review.dto.ReviewRequestDto;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="review_id", updatable = false, nullable = false)
    private UUID reviewId;

    //REVIEW:USER=N:1관계
    // JPA ManyToOne USER타입 객체로 받아오는 부분이 이해를 온전히 못함.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // REVEIW:ORDER = 1:1관계
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    // REVIEW:STORE = N:1 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

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
    protected void onUpdate() {
        updated_at = Timestamp.valueOf(LocalDateTime.now());
    }


    // buildup 패턴으로 review 생성
    public static Review createReview(ReviewRequestDto requestDto, User user, Order order, Store store) {
        return Review.builder()
                .user(user)
                .order(order)
                .store(store)
                .content(requestDto.getContent())
                .rating(requestDto.getRating())
                .created_by(String.valueOf(user.getUser_id()))
                .build();
    }

    // review 수정
    public void updateReview(String content, ReviewRatingTypeEnum rating, String userId) {
        this.content = content;
        this.rating = rating;
        this.updated_by = userId;
    }

    // review 삭제
    public void deleteReview(String userId) {
        this.deleted_by = userId;
        deleted_at = Timestamp.valueOf(LocalDateTime.now());
    }
}
