package az.edu.itbrains.ecommerce.controllers.dashboard;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {

    @GetMapping("/admin/category")
    public String index(){
        return "/dashboard/category/index";
    }


    @GetMapping("/admin/category/create")
    public String create(){
        return "/dashboard/category/create";
    }

    @GetMapping("/admin/category/update")
    public String update(){
        return "/dashboard/category/update";
    }


    @GetMapping("/admin/category/delete")
    public String delete(){
        return "/dashboard/category/delete";
    }
}
