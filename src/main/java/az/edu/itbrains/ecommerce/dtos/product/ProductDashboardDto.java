package az.edu.itbrains.ecommerce.dtos.product;


import az.edu.itbrains.ecommerce.dtos.category.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDashboardDto {
    private Long id;
    private String name;
    private double price;
    private String image;
    private CategoryDto category;
}
