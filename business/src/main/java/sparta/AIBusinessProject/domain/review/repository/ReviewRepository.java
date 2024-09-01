package sparta.AIBusinessProject.domain.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sparta.AIBusinessProject.domain.review.dto.ReviewRequestDto;
import sparta.AIBusinessProject.domain.review.dto.ReviewResponseDto;
import sparta.AIBusinessProject.domain.review.entity.Review;
import sparta.AIBusinessProject.domain.store.entity.Store;
import sparta.AIBusinessProject.domain.user.entity.User;

import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {

    // 리뷰 조회 시 페이징 적용
    Page<Review> findAllByStore_StoreId(UUID storeId, Pageable pageable);
    Page<Review> findAllByUser(User user, Pageable pageable);

}
