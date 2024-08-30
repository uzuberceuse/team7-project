package sparta.AIBusinessProject.domain.store.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import sparta.AIBusinessProject.domain.product.dto.ProductResponseDto;
import sparta.AIBusinessProject.domain.product.entity.Product;
import sparta.AIBusinessProject.domain.store.dto.StoreListResponseDto;
import sparta.AIBusinessProject.domain.store.entity.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//import static  sparta.AIBusinessProject.domain.store.core.QProduct.store;

public class StoreRepositoryImpl implements StoreRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<StoreListResponseDto> getStores(StoreListResponseDto listResponseDto, Pageable pageable) {

        List<OrderSpecifier<?>> orders = getAllOrderSpecifiers(pageable);

        QueryResults<Store> results = queryFactory
                .selectFrom(store)
                // 이름, 설명, 가격범위, 수량범위
                .where(
                        nameContains(listResponseDto.getStoreName()),
                        locationContains(listResponseDto.getLocation())
                )
                // 리스트 결과값을 정렬
                .orderBy(orders.toArray(new OrderSpecifier[0]))
                .offset(pageable.getOffset())
                // 반드시 DB 한번에 전체 조회하면 안됨
                // 서버 다운될 수 있음
                .limit(pageable.getPageSize())
                .fetchResults();

        List<StoreListResponseDto> content = results.getResults().stream()
                .map(Store::toResponseDto)
                .collect(Collectors.toList());
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    // searchDto에서 이름을 검색하지 않으면 따로 NULL로 반환해 무시하고 검색조건에서 빼버린다.
    private BooleanExpression nameContains(String name) {
        return name != null ? store.name.containsIgnoreCase(name) : null;
    }

    private BooleanExpression locationContains(String description) {
        return description != null ? store.description.containsIgnoreCase(description) : null;
    }

    private List<OrderSpecifier<?>> getAllOrderSpecifiers(Pageable pageable) {
        List<OrderSpecifier<?>> orders = new ArrayList<>();

        if (pageable.getSort() != null) {
            for (Sort.Order sortOrder : pageable.getSort()) {
                com.querydsl.core.types.Order direction = sortOrder.isAscending() ? com.querydsl.core.types.Order.ASC : com.querydsl.core.types.Order.DESC;
                switch (sortOrder.getProperty()) {
                    case "storeName":
                        orders.add(new OrderSpecifier<>(direction, store.storeName));
                        break;
                    case "location":
                        orders.add(new OrderSpecifier<>(direction, store.location));
                        break;
                    default:
                        break;
                }
            }
        }

        return orders;
    }
}