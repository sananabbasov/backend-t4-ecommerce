package az.edu.itbrains.ecommerce.services;

import az.edu.itbrains.ecommerce.dtos.basket.BasketAddDto;

public interface BasketService {

    boolean addToBasket(BasketAddDto basketAddDto, String userEmail);
    boolean removeFromBasket(Long productId, String userEmail);
}
