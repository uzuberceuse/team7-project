package sparta.AIBusinessProject.domain.review.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import sparta.AIBusinessProject.domain.review.dto.ReviewRequestDto;
import sparta.AIBusinessProject.domain.review.entity.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//import static  sparta.AIBusinessProject.domain.review.core.QProduct.review;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ReviewRequestDto> getReviews(ReviewRequestDto reviewDto, Pageable pageable) {

        List<OrderSpecifier<?>> orders = getAllOrderSpecifiers(pageable);

        QueryResults<Review> results = queryFactory
                .selectFrom(review)
                // 생성 순, 평점 순
                .where(
                        contentsContains(reviewDto.getContent())
                )
                //리스트로 결과값 정렬
                .orderBy(orders.toArray(new OrderSpecifier[0]))
                .offset(pageable.getOffset())
                // 반드시 DB 한번에 전체 조회하면 안됨
                // 서버 다운될 수 있음
                .limit(pageable.getPageSize())
                .fetchResults();

        List<ReviewRequestDto> content = results.getResults().stream()
                .map(Review::toResponseDto)
                .collect(Collectors.toList());
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    // reviewDto에서 내용을 검색하지 않으면 따로 NULL로 반환해 무시하고 검색조건에서 빼버린다.
    private BooleanExpression contentsContains(String contents) {
        return contents != null ? review.description.containsIgnoreCase(contents) : null;
    }


    private List<OrderSpecifier<?>> getAllOrderSpecifiers(Pageable pageable) {
        List<OrderSpecifier<?>> orders = new ArrayList<>();

        if (pageable.getSort() != null) {
            for (Sort.Order sortOrder : pageable.getSort()) {
                com.querydsl.core.types.Order direction = sortOrder.isAscending() ? com.querydsl.core.types.Order.ASC : com.querydsl.core.types.Order.DESC;
                switch (sortOrder.getProperty()) {
                    case "created_at":
                        orders.add(new OrderSpecifier<>(direction, review.created_at));
                        break;
                    case "rating":
                        orders.add(new OrderSpecifier<>(direction, review.rating));
                        break;
                    default:
                        break;
                }
            }
        }

        return orders;
    }
}
