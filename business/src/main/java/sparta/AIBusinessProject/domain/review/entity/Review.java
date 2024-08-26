package sparta.AIBusinessProject.domain.review.entity;


import jakarta.persistence.*;
import lombok.*;
import sparta.AIBusinessProject.domain.review.dto.ReviewDto;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REVIEW_SEQ_GENERATOR")
    @Column(nullable = false, unique = true)
    private String review_id = UUID.randomUUID().toString();


    private String user_id;
    private String order_id;
    private String store_id;


    // 글자 수 제한걸기
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer rating;

    private LocalDateTime created_at;
    private String created_by;
    private LocalDateTime deleted_at;
    private String deleted_by;
    private LocalDateTime updated_at;
    private String updated_by;


    // 리뷰 생성 시 생성 일자를 현재 시간으로
    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() { updated_at = LocalDateTime.now(); }

    @PreRemove
    protected void onDelete() { deleted_at = LocalDateTime.now(); }

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
    }


    // buildup 패턴으로 review 수정
    public static Review updateReview(ReviewDto requestDto, String user_id) {
        return Review.builder()
                .content(requestDto.getContent())
                .rating(requestDto.getRating())
                .updated_by(user_id)
                .build();
    }


    // buildup 패턴으로 review 삭제
    public static Review deleteReview(ReviewDto requestDto, String user_id) {
        return Review.builder()
                .content(requestDto.getContent())
                .rating(requestDto.getRating())
                .deleted_by(user_id)
                .build();
    }


    public ReviewDto toResponseDto(){
        return new ReviewDto(
                this.user_id,
                this.order_id,
                this.store_id,
                this.content,
                this.rating
        );
    }
}
