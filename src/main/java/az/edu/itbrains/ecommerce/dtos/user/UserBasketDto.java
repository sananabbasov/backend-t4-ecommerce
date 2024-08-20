package az.edu.itbrains.ecommerce.dtos.user;


import az.edu.itbrains.ecommerce.dtos.product.ProductBasketDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBasketDto {

    private double subtotal;
    private double shipping;
    private double total;
    private List<ProductBasketDto> products;
}
