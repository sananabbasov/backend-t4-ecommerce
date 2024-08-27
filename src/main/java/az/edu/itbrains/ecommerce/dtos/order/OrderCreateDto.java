package az.edu.itbrains.ecommerce.dtos.order;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDto {
    private Long id;
    private Long quantity;
}
