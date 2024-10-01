package az.edu.itbrains.ecommerce.repositories;

import az.edu.itbrains.ecommerce.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
    Product findByFeaturedTrue();
    Optional<Product> findByIdAndDeletedFalse(Long id);
    List<Product> findByDeletedFalse();
    Page<Product> findByDeletedFalse(Pageable pageable);
}
