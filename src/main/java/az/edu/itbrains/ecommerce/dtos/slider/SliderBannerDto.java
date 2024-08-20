package az.edu.itbrains.ecommerce.dtos.slider;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SliderBannerDto {
    private Long id;
    private String title;
    private String photoUrl;
    private String description;
}
