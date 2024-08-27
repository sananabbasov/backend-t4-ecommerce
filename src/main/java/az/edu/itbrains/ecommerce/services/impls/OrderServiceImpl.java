package az.edu.itbrains.ecommerce.services.impls;

import az.edu.itbrains.ecommerce.enums.OrderStatus;
import az.edu.itbrains.ecommerce.enums.PaymentStatus;
import az.edu.itbrains.ecommerce.models.Basket;
import az.edu.itbrains.ecommerce.models.Order;
import az.edu.itbrains.ecommerce.models.OrderItem;
import az.edu.itbrains.ecommerce.models.UserEntity;
import az.edu.itbrains.ecommerce.repositories.BasketRepository;
import az.edu.itbrains.ecommerce.repositories.OrderItemRepository;
import az.edu.itbrains.ecommerce.repositories.OrderRepository;
import az.edu.itbrains.ecommerce.repositories.UserRepository;
import az.edu.itbrains.ecommerce.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final BasketRepository basketRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper;


    public OrderServiceImpl(UserRepository userRepository, BasketRepository basketRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.basketRepository = basketRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean checkout(String userEmail) {
        try {
            UserEntity findUser = userRepository.findByEmail(userEmail);
            List<Basket> userBasket = findUser.getBaskets();
            Order order = new Order();
            order.setUser(findUser);
            order.setOrderDate(new Date());
            order.setOrderStatus(OrderStatus.PENDING);
            order.setPaymentStatus(PaymentStatus.PENDING);
            orderRepository.save(order);

            for (Basket basket: userBasket) {
                OrderItem orderItem = new OrderItem();

                orderItem.setProduct(basket.getProduct());
                orderItem.setQuantity(basket.getQuantity());
                orderItem.setPrice(basket.getProduct().getPrice());
                orderItem.setOrder(order);
                orderItemRepository.save(orderItem);
            }

            return true;
        }catch (Exception e){
            return false;
        }
    }
}
