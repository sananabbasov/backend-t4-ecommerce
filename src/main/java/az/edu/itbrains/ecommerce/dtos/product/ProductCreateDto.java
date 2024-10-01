package az.edu.itbrains.ecommerce.dtos.product;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDto {
    private String name;
    private Double price;
    private String image;
    private String description;
    private Double discountPrice;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date discountDate;
    private Boolean featured;
    private Long categoryId;
}
