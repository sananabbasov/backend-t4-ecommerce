package az.edu.itbrains.ecommerce.models;


import az.edu.itbrains.ecommerce.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Gender gender;


    @OneToMany(mappedBy = "user")
    private List<Basket> baskets;


    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
