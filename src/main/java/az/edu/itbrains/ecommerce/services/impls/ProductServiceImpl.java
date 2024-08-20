package az.edu.itbrains.ecommerce.services.impls;

import az.edu.itbrains.ecommerce.dtos.category.CategoryDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductDealDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductDetailDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductRelatedDto;
import az.edu.itbrains.ecommerce.models.Product;
import az.edu.itbrains.ecommerce.repositories.ProductRepository;
import az.edu.itbrains.ecommerce.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Product> getHomeProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public ProductDetailDto getById(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        ProductDetailDto productDetail = modelMapper.map(product,ProductDetailDto.class);
        return productDetail;
    }


    @Override
    public List<ProductRelatedDto> getRelatedProduct(Long categoryId, Long productId) {
        List<Product> products = productRepository.findByCategoryId(categoryId).stream().filter(product->product.getId() != productId).limit(3).collect(Collectors.toList());
        List<ProductRelatedDto> result = products.stream().map(product -> modelMapper.map(product, ProductRelatedDto.class)).collect(Collectors.toList());
        return result;
    }

    @Override
    public ProductDealDto getDealProduct() {
        // 0.5 => 1 - 0.5
        // 0.5 => 4 - 0.5
        //  database => findAll => filter(price - discount_price)
        // price * 10 |   1 * 100  | 0.5 / 1 * 100

        Product product = productRepository.findByFeaturedTrue();
        ProductDealDto dealDto = modelMapper.map(product,ProductDealDto.class);
        double percent = (product.getDiscountPrice() * 100) / product.getPrice();
        dealDto.setDiscountPercent(Math.ceil(percent));
        return dealDto;
    }
}
