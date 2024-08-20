package az.edu.itbrains.ecommerce.repositories;

import az.edu.itbrains.ecommerce.models.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestimonialRepository extends JpaRepository<Testimonial,Long> {
}
