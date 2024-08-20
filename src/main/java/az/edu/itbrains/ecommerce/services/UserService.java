package az.edu.itbrains.ecommerce.services;

import az.edu.itbrains.ecommerce.dtos.auth.RegisterDto;
import az.edu.itbrains.ecommerce.dtos.user.UserBasketDto;

import java.util.List;

public interface UserService {

    boolean register(RegisterDto registerDto);
    UserBasketDto getUserBasket(String userEmail);
}
