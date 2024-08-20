package az.edu.itbrains.ecommerce.services.impls;

import az.edu.itbrains.ecommerce.dtos.auth.RegisterDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductBasketDto;
import az.edu.itbrains.ecommerce.dtos.user.UserBasketDto;
import az.edu.itbrains.ecommerce.models.Basket;
import az.edu.itbrains.ecommerce.models.UserEntity;
import az.edu.itbrains.ecommerce.repositories.UserRepository;
import az.edu.itbrains.ecommerce.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.encoder = encoder;
    }


    @Override
    public boolean register(RegisterDto registerDto) {
        UserEntity findUser = userRepository.findByEmail(registerDto.getEmail());
        if(findUser != null){
            return false;
        }
        UserEntity newUser = modelMapper.map(registerDto, UserEntity.class);
        String password = encoder.encode(registerDto.getPassword());
        newUser.setPassword(password);
        userRepository.save(newUser);
        return true;
    }

    @Override
    public UserBasketDto getUserBasket(String userEmail) {
        UserEntity findUser = userRepository.findByEmail(userEmail);
        UserBasketDto basket = new UserBasketDto();
        List<Basket> userBasket = findUser.getBaskets();
        List<ProductBasketDto> productBasketList = new ArrayList<>();
        for (Basket b: userBasket) {
            ProductBasketDto pb = new ProductBasketDto();
            pb.setId(b.getProduct().getId());
            pb.setName(b.getProduct().getName());
            pb.setImage(b.getProduct().getImage());
            pb.setPrice(b.getProduct().getPrice());
            pb.setQuantity(b.getQuantity());
            productBasketList.add(pb);
        }
        double subtotal = productBasketList.stream().mapToDouble(c->c.getPrice() * c.getQuantity()).sum();
        double shipping = subtotal > 0 ? 1.4 : 0;
        double total = shipping + subtotal;

        basket.setShipping(shipping);
        basket.setSubtotal(subtotal);
        basket.setTotal(total);
        basket.setProducts(productBasketList);
        return basket;
    }


}
