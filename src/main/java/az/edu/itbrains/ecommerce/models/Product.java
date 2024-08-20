package az.edu.itbrains.ecommerce.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String image;
    private String description;
    private Double discountPrice;
    private Date discountDate;
    private Boolean featured;
    @ManyToOne
    private Category category;
}
