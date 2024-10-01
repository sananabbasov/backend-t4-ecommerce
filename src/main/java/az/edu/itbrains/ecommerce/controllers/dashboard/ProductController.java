package az.edu.itbrains.ecommerce.controllers.dashboard;


import az.edu.itbrains.ecommerce.dtos.category.CategoryDashboardDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductCreateDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductDashboardDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductUpdateDto;
import az.edu.itbrains.ecommerce.payloads.PaginationPayload;
import az.edu.itbrains.ecommerce.services.CategoryService;
import az.edu.itbrains.ecommerce.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }


    @GetMapping("/admin/product")
    public String index(Integer currentPage, Model model){
       PaginationPayload<ProductDashboardDto> result = productService.getDashboardProducts(currentPage);
       model.addAttribute("products",result);
        return "/dashboard/product/index";
    }


    @GetMapping("/admin/product/create")
    public String create(Model model){
        List<CategoryDashboardDto> categories = categoryService.getDashboardCategories();
        model.addAttribute("categories",categories);
        return "/dashboard/product/create";
    }

    @PostMapping("/admin/product/create")
    public String create(ProductCreateDto productCreateDto){
        boolean result = productService.createProduct(productCreateDto);
        if (result){
            return "redirect:/admin/product/index";
        }
        return "redirect:/admin/product/create";
    }


    @GetMapping("/admin/product/update/{id}")
    public String update(@PathVariable Long id, Model model){
        ProductUpdateDto productUpdateDto = productService.getUpdatedProduct(id);
        List<CategoryDashboardDto> categories = categoryService.getDashboardCategories();
        model.addAttribute("product", productUpdateDto);
        model.addAttribute("categories",categories);
        return "/dashboard/product/update";
    }


   @GetMapping("/admin/product/delete/{id}")
    public String delete(@PathVariable Long id){
        productService.removeProduct(id);
        return "redirect:/admin/product";
   }

}
