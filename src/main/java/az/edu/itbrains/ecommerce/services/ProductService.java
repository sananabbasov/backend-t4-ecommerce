package az.edu.itbrains.ecommerce.services;

import az.edu.itbrains.ecommerce.dtos.product.ProductDealDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductDetailDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductRelatedDto;
import az.edu.itbrains.ecommerce.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> getHomeProducts();
    ProductDetailDto getById(Long id);
    List<ProductRelatedDto> getRelatedProduct(Long categoryId, Long productId);
    ProductDealDto getDealProduct();

}
