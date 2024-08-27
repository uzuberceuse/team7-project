package sparta.AIBusinessProject.domain.product.service;


import org.springframework.stereotype.Service;
import sparta.AIBusinessProject.domain.product.dto.ProductListResponseDto;
import sparta.AIBusinessProject.domain.product.dto.ProductRequestDto;
import sparta.AIBusinessProject.domain.product.dto.ProductResponseDto;
import sparta.AIBusinessProject.domain.product.entity.Product;
import sparta.AIBusinessProject.domain.product.repository.ProductRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    // 상품 등록
    public ProductResponseDto createProduct(ProductRequestDto requestDto, String user_id){
        try {
            return //productRepository.save(Product.createProduct(requestDto, user_id));
        } catch (Exception e) {
            return null;
        }
    }

    // 상품 수정
    public ProductResponseDto updateProduct(ProductRequestDto requestDto, String user_id){
        try {
            return //productRepository.save(Product.updateProduct(requestDto, user_id));

        } catch (Exception e) {
            return null;
        }
    }

    // 상품 삭제
    public boolean deleteProduct(ProductRequestDto requestDto, String user_id){
        try {
            productRepository.delete(Product.deleteProduct(requestDto, user_id));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    // 상품 목록 조회
    public List<ProductListResponseDto> getProducts(){
        return productRepository.findAll().stream()
                .map(Product::toListResponseDto)
                .collect(Collectors.toList());
    }

    // 상품 상세 조회
    public ProductResponseDto getProduct(UUID product_id) {
        return ;
    }

}
