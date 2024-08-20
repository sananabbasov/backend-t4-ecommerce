package az.edu.itbrains.ecommerce.dtos.auth;

import az.edu.itbrains.ecommerce.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    private String name;
    private String surname;
    private String email;
    private String password;
    private int gender;
}
