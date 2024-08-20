package az.edu.itbrains.ecommerce.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDealDto {
    private Long id;
    private String name;
    private String image;
    private String description;
    private Double discountPercent;
    private Date discountDate;
}
