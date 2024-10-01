package az.edu.itbrains.ecommerce.payloads;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationPayload<TEntity> {

    private Integer totalPage;
    private List<TEntity> data;
}
