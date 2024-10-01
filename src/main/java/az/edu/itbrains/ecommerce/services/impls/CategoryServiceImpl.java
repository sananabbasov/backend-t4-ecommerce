package az.edu.itbrains.ecommerce.services.impls;

import az.edu.itbrains.ecommerce.dtos.category.CategoryCreateDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryDashboardDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryDto;
import az.edu.itbrains.ecommerce.dtos.category.CategoryUpdateDto;
import az.edu.itbrains.ecommerce.models.Category;
import az.edu.itbrains.ecommerce.repositories.CategoryRepository;
import az.edu.itbrains.ecommerce.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Category> getHomeCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    @Override
    public List<CategoryDashboardDto> getDashboardCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDashboardDto> result = categories.stream().map(cat -> modelMapper.map(cat, CategoryDashboardDto.class)).collect(Collectors.toList());
        return result;
    }

    @Override
    public boolean createCategory(CategoryCreateDto categoryCreateDto) {
       try {
           Category category = modelMapper.map(categoryCreateDto, Category.class);
           categoryRepository.save(category);
           return true;
       }catch (Exception e){
           System.out.println(e.getMessage());
           return false;
       }
    }

    @Override
    public boolean updateCategory(Long id, CategoryUpdateDto categoryUpdateDto) {
         try {
             Category findCategory = categoryRepository.findById(id).orElseThrow();
             findCategory.setName(categoryUpdateDto.getName());
             categoryRepository.save(findCategory);
             return true;
         }catch (Exception e){
             System.out.println(e.getMessage());
             return false;
         }
    }

    @Override
    public CategoryDto getCategory(Long id) {
        Category findCategory = categoryRepository.findById(id).orElseThrow();
        CategoryDto result = modelMapper.map(findCategory, CategoryDto.class);
        return result;
    }

    @Override
    public Category getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        return category;
    }



    public int test(){
        int res = 3;
        return  res;
    }
}

