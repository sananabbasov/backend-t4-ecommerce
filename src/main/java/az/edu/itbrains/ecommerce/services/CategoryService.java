package az.edu.itbrains.ecommerce.services;

import az.edu.itbrains.ecommerce.models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getHomeCategories();
}
