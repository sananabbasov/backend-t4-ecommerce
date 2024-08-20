package az.edu.itbrains.ecommerce.dtos.testimonial;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestimonialDto {
    private Long id;
    private String position;
    private String image;
    private String name;
    private String surname;
    private String description;
}
