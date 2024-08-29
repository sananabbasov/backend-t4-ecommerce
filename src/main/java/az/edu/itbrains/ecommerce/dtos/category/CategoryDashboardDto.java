package az.edu.itbrains.ecommerce.dtos.category;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDashboardDto {
    private Long id;
    private String name;
}
