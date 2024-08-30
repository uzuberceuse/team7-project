package sparta.AIBusinessProject.domain.product.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import sparta.AIBusinessProject.domain.product.dto.ProductListResponseDto;
import sparta.AIBusinessProject.domain.product.dto.ProductResponseDto;
import sparta.AIBusinessProject.domain.product.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//import static  sparta.AIBusinessProject.domain.product.core.QProduct.product;


// QueryDSL 구현할 코드
// QueryDSL은 리스트 보여줄 때
// 좀 더 들어가자면 리스트 조회 시 조건 검색할 때 사용
// QueryDSL없으면 JPA로 검색조건에 따라 모든 기능을 다 설정해야
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public Page<ProductListResponseDto> getProducts(ProductListResponseDto listResponseDto, Pageable pageable) {

        List<OrderSpecifier<?>> orders = getAllOrderSpecifiers(pageable);

        QueryResults<Product> results = queryFactory
                .selectFrom(product)
                // 이름, 설명, 가격범위, 수량범위
                .where(
                        nameContains(listResponseDto.getProductName())
                )
                // 리스트 결과값을 정렬
                .orderBy(orders.toArray(new OrderSpecifier[0]))
                .offset(pageable.getOffset())
                // 반드시 DB 한번에 전체 조회하면 안됨
                // 서버 다운될 수 있음
                .limit(pageable.getPageSize())
                .fetchResults();

        List<ProductResponseDto> content = results.getResults().stream()
                .map(Product::toResponseDto)
                .collect(Collectors.toList());
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);

        private BooleanExpression nameContains(String name) {
            return name != null ? store.name.containsIgnoreCase(name) : null;
        }
    }


    private List<OrderSpecifier<?>> getAllOrderSpecifiers(Pageable pageable) {
        List<OrderSpecifier<?>> orders = new ArrayList<>();

        if(pageable.getSort() != null) {
            for(Sort.Order sortOrder : pageable.getSort()) {
                com.querydsl.core.types.Order direction = sortOrder.isAscending() ? com.querydsl.core.types.Order.ASC : com.querydsl.core.types.Order.DESC;
                switch (sortOrder.getProperty()) {
                    // product는 Q파일 생성시
                    case "created_at":
                        orders.add(new OrderSpecifier<>(direction, product.createdAt));
                        break;
                    case "price":
                        orders.add(new OrderSpecifier<>(direction, product.price));
                        break;
                }
            }
        }
        return  orders;
    }
}
