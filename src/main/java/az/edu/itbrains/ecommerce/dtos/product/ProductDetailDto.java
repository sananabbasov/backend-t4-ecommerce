package az.edu.itbrains.ecommerce.dtos.product;

import az.edu.itbrains.ecommerce.dtos.category.CategoryDto;
import lombok.Data;

@Data
public class ProductDetailDto {
    private Long id;
    private String name;
    private double price;
    private String image;
    private String description;
    private CategoryDto category;
}
