package az.edu.itbrains.ecommerce.services.impls;

import az.edu.itbrains.ecommerce.dtos.product.*;
import az.edu.itbrains.ecommerce.models.Category;
import az.edu.itbrains.ecommerce.models.Product;
import az.edu.itbrains.ecommerce.payloads.PaginationPayload;
import az.edu.itbrains.ecommerce.repositories.ProductRepository;
import az.edu.itbrains.ecommerce.services.CategoryService;
import az.edu.itbrains.ecommerce.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Product> getHomeProducts() {
        List<Product> products = productRepository.findByDeletedFalse();
        return products;
    }

    @Override
    public ProductDetailDto getById(Long id) {
        Product product = productRepository.findByIdAndDeletedFalse(id).orElseThrow();
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

    @Override
    public PaginationPayload<ProductDashboardDto> getDashboardProducts(Integer pageNumber) {

        pageNumber = pageNumber == null ? 1 : pageNumber;
        Pageable pageable = PageRequest.of(pageNumber-1,10,Sort.by("id"));
        Page<Product> products = productRepository.findByDeletedFalse(pageable);

        PaginationPayload paginationPayload = new PaginationPayload();
        paginationPayload.setTotalPage(products.getTotalPages());
        paginationPayload.setData(products.getContent());

        return paginationPayload;
    }

    @Override
    public boolean createProduct(ProductCreateDto productCreateDto) {
        try {
            Product product = modelMapper.map(productCreateDto, Product.class);
            Category findCategory = categoryService.getCategoryById(productCreateDto.getCategoryId());
            if (findCategory == null){
                return false;
            }
            product.setCategory(findCategory);
            productRepository.save(product);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public ProductUpdateDto getUpdatedProduct(Long id) {
        Product findProduct = productRepository.findByIdAndDeletedFalse(id).orElseThrow();
        ProductUpdateDto product = modelMapper.map(findProduct, ProductUpdateDto.class);
        return product;
    }

    @Override
    public boolean removeProduct(Long id) {
        Product findProduct = productRepository.findByIdAndDeletedFalse(id).orElseThrow();
        findProduct.setDeleted(true);
        productRepository.save(findProduct);
        return true;
    }
}
