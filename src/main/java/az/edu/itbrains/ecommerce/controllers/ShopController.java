package az.edu.itbrains.ecommerce.controllers;

import az.edu.itbrains.ecommerce.dtos.basket.BasketAddDto;
import az.edu.itbrains.ecommerce.dtos.order.PlaceOrderDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductDetailDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductRelatedDto;
import az.edu.itbrains.ecommerce.dtos.user.UserBasketDto;
import az.edu.itbrains.ecommerce.services.BasketService;
import az.edu.itbrains.ecommerce.services.OrderService;
import az.edu.itbrains.ecommerce.services.ProductService;
import az.edu.itbrains.ecommerce.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ShopController {

    private final ProductService productService;
    private final BasketService basketService;
    private final UserService userService;
    private final OrderService orderService;

    public ShopController(ProductService productService, BasketService basketService, UserService userService, OrderService orderService) {
        this.productService = productService;
        this.basketService = basketService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/shop")
    public String index(){
        return "/shop/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        ProductDetailDto product = productService.getById(id);
        List<ProductRelatedDto> relatedProducts = productService.getRelatedProduct(product.getCategory().getId(),product.getId());
        model.addAttribute("product", product);
        model.addAttribute("relatedProducts", relatedProducts);
        return "/shop/detail";
    }


    @GetMapping("/basket")
    public String basket(Model model, Principal principal){
        UserBasketDto userBasket = userService.getUserBasket(principal.getName());
        model.addAttribute("basket", userBasket);
        boolean active = true;
        if (userBasket.getTotal() == 0){
            active = false;
        }
        model.addAttribute("active",active);
        return "/shop/basket";

    }


    @PostMapping("/basket")
    public String basket(BasketAddDto basketAddDto, Principal principal){
        basketService.addToBasket(basketAddDto,principal.getName());


        return "redirect:/basket";
    }


    @GetMapping("/basket/{id}")
    public String removeBasket(@PathVariable Long id, Principal principal){
        basketService.removeFromBasket(id, principal.getName());
        return "redirect:/basket";
    }


    @GetMapping("/checkout")
    public String checkout(Principal principal, Model model){
        UserBasketDto userBasket = userService.getUserBasket(principal.getName());
        if (userBasket.getTotal() == 0){
            return "redirect:/basket";
        }
        model.addAttribute("basket",userBasket);
        return "/shop/checkout";
    }


    @PostMapping("/checkout")
    public String checkout(PlaceOrderDto placeOrderDto, Principal principal){
        UserBasketDto userBasket = userService.getUserBasket(principal.getName());
        if (userBasket.getTotal() == 0){
            return "redirect:/basket";
        }
        orderService.checkout(principal.getName(), placeOrderDto);
        return "redirect:/";
    }
}
