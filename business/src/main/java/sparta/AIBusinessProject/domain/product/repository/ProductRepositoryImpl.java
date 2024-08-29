//package sparta.AIBusinessProject.domain.product.repository;
//
//import com.querydsl.core.types.OrderSpecifier;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import sparta.AIBusinessProject.domain.product.dto.ProductListResponseDto;
//
//import java.util.List;
//
//
//// QueryDSL 구현할 코드
//// QueryDSL은 리스트 보여줄 때
//// 좀 더 들어가자면 리스트 조회 시 조건 검색할 때 사용
//// QueryDSL없으면 JPA로 검색조건에 따라 모든 기능을 다 설정해야
//@RequiredArgsConstructor
//public class ProductRepositoryImpl implements ProductRepositoryCustom {
//
//    private final JPAQueryFactory queryFactory;
//
//
//    @Override
//    public Page<ProductListResponseDto> getProducts(ProductListResponseDto listResponseDto, Pageable pageable) {
//
//        List<OrderSpecifier<?>> orders = getAllOrderSpecifiers(pageable);
//        return null;
//    }
//}
