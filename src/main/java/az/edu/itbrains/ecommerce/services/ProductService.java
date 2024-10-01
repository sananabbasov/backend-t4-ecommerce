package az.edu.itbrains.ecommerce.services;

import az.edu.itbrains.ecommerce.dtos.product.*;
import az.edu.itbrains.ecommerce.models.Product;
import az.edu.itbrains.ecommerce.payloads.PaginationPayload;

import java.util.List;

public interface ProductService {

    List<Product> getHomeProducts();
    ProductDetailDto getById(Long id);
    List<ProductRelatedDto> getRelatedProduct(Long categoryId, Long productId);
    ProductDealDto getDealProduct();
    PaginationPayload<ProductDashboardDto> getDashboardProducts(Integer pageNumber);
    boolean createProduct(ProductCreateDto productCreateDto);
    ProductUpdateDto getUpdatedProduct(Long id);
    boolean removeProduct(Long id);
}
