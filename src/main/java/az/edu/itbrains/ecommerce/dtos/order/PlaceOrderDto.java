package az.edu.itbrains.ecommerce.dtos.order;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderDto {
    private String address;
    private String phoneNumber;
    private String message;
}
