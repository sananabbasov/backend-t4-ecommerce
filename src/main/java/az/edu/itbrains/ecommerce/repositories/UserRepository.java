package az.edu.itbrains.ecommerce.repositories;

import az.edu.itbrains.ecommerce.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByEmail(String email);
}
