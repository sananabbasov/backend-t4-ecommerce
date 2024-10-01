package az.edu.itbrains.ecommerce.controllers.dashboard;


import az.edu.itbrains.ecommerce.dtos.category.CategoryCreateDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryDashboardDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryUpdateDto;
import az.edu.itbrains.ecommerce.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/admin/category")
    public String index(Model model){
        List<CategoryDashboardDto> categories = categoryService.getDashboardCategories();
        model.addAttribute("categories",categories);
        return "/dashboard/category/index";
    }


    @GetMapping("/admin/category/create")
    public String create(){
        return "/dashboard/category/create";
    }

    @PostMapping("/admin/category/create")
    public String create(CategoryCreateDto categoryCreateDto){
        boolean result = categoryService.createCategory(categoryCreateDto);
        if (result){
            return "redirect:/admin/category";
        }
        return "redirect:/dashboard/category/create";
    }

    @GetMapping("/admin/category/update/{id}")
    public String update(@PathVariable Long id, Model model){
        CategoryDto category = categoryService.getCategory(id);
        model.addAttribute("category",category);
        return "/dashboard/category/update";
    }


    @PostMapping("/admin/category/update/{id}")
    public String update(CategoryUpdateDto categoryUpdateDto, @PathVariable Long id){
        boolean result = categoryService.updateCategory(id,categoryUpdateDto);
        if (result){
            return "redirect:/admin/category";
        }
        return "redirect:/dashboard/category/update";
    }


    @GetMapping("/admin/category/delete")
    public String delete(){
        return "/dashboard/category/delete";
    }
}
