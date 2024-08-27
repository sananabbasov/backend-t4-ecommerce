package az.edu.itbrains.ecommerce.services;

import az.edu.itbrains.ecommerce.dtos.order.PlaceOrderDto;

public interface OrderService {
    boolean checkout(String userEmail, PlaceOrderDto placeOrderDto);
}
