package az.edu.itbrains.ecommerce.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDto {
    private String name;
    private double price;
    private String image;
    private String description;
    private Double discountPrice;
    private Date discountDate;
    private Boolean featured;
    private Long categoryId;
}
