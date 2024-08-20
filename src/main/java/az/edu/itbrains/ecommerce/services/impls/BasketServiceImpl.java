package az.edu.itbrains.ecommerce.services.impls;

import az.edu.itbrains.ecommerce.dtos.basket.BasketAddDto;
import az.edu.itbrains.ecommerce.models.Basket;
import az.edu.itbrains.ecommerce.models.Product;
import az.edu.itbrains.ecommerce.models.UserEntity;
import az.edu.itbrains.ecommerce.repositories.BasketRepository;
import az.edu.itbrains.ecommerce.repositories.ProductRepository;
import az.edu.itbrains.ecommerce.repositories.UserRepository;
import az.edu.itbrains.ecommerce.services.BasketService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public BasketServiceImpl(BasketRepository basketRepository, UserRepository userRepository, ModelMapper modelMapper, ProductRepository productRepository) {
        this.basketRepository = basketRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public boolean addToBasket(BasketAddDto basketAddDto, String userEmail) {
        UserEntity findUser = userRepository.findByEmail(userEmail);
        Product findProduct = productRepository.findById(basketAddDto.getProductId()).orElseThrow();
        Basket filterBasket = basketRepository.findByUserIdAndProductId(findUser.getId(), findProduct.getId());

        if (filterBasket == null){
            Basket basket = new Basket();
            basket.setProduct(findProduct);
            basket.setQuantity(basketAddDto.getQuantity());
            basket.setUser(findUser);
            basketRepository.save(basket);
        }else{
            filterBasket.setQuantity(filterBasket.getQuantity()+ basketAddDto.getQuantity());
            basketRepository.save(filterBasket);
        }
        return true;
    }

    @Override
    public boolean removeFromBasket(Long productId, String userEmail) {
        UserEntity findUser = userRepository.findByEmail(userEmail);
        Basket filterBasket = basketRepository.findByUserIdAndProductId(findUser.getId(), productId);
        basketRepository.delete(filterBasket);
        return true;
    }
}
