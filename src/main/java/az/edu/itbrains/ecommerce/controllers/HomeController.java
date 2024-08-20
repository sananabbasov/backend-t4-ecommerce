package az.edu.itbrains.ecommerce.controllers;


import az.edu.itbrains.ecommerce.dtos.product.ProductDealDto;
import az.edu.itbrains.ecommerce.dtos.slider.SliderBannerDto;
import az.edu.itbrains.ecommerce.dtos.testimonial.TestimonialDto;
import az.edu.itbrains.ecommerce.models.Category;
import az.edu.itbrains.ecommerce.models.Product;
import az.edu.itbrains.ecommerce.services.CategoryService;
import az.edu.itbrains.ecommerce.services.ProductService;
import az.edu.itbrains.ecommerce.services.SliderService;
import az.edu.itbrains.ecommerce.services.TestimonialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final SliderService sliderService;
    private final TestimonialService testimonialService;

    public HomeController(CategoryService categoryService, ProductService productService, SliderService sliderService, TestimonialService testimonialService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.sliderService = sliderService;
        this.testimonialService = testimonialService;
    }


    @GetMapping("/")
    public String index(Model model){

        List<Product> products = productService.getHomeProducts();
        List<Category> categories = categoryService.getHomeCategories();
        List<Product> homeProducts = products.stream().limit(3).collect(Collectors.toList());
        List<SliderBannerDto> slider = sliderService.getSlider();
        ProductDealDto deal = productService.getDealProduct();
        List<TestimonialDto> testimonialDtos = testimonialService.getTestimonials();
        model.addAttribute("products",homeProducts);
        model.addAttribute("categories", categories);
        model.addAttribute("sliders", slider);
        model.addAttribute("deal", deal);
        model.addAttribute("testimonials", testimonialDtos);
        return "home";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
