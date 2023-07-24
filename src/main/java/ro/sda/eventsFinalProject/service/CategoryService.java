package ro.sda.eventsFinalProject.service;

import org.springframework.stereotype.Service;
import ro.sda.eventsFinalProject.model.Category;
import ro.sda.eventsFinalProject.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public Category saveCategory(Category categoryToBeSave){
        if (categoryToBeSave==null){
            throw  new IllegalArgumentException("A category must have a body");
        }
        if (categoryToBeSave.getName()==null){
            throw  new IllegalArgumentException("A category must have a name");
        }
        Category savedCategory=categoryRepository.save(categoryToBeSave);
        return savedCategory;
    }
    public Category readCategory(Integer id){
        if (id == null) {
            throw new IllegalArgumentException("Category id must not be null!");
        }
        Category category =categoryRepository.findById(id).orElse(null);
        if (category==null){
            throw new IllegalArgumentException("There it is no category with id: "+ id);
        }
        return category;
    }
    public List<Category> readAllCategories(){
        return categoryRepository.findAll();
    }
    public Category categoryUpdate(Category updatedCategory){
        if (updatedCategory == null){
            throw new IllegalArgumentException("A category must have body");
        }
        Category categoryToUpdate = readCategory(updatedCategory.getId());
        categoryRepository.save(updatedCategory);
        return categoryToUpdate;
    }
    public void deleteCategory(Integer categoryId){
        Category categoryToDelete = readCategory(categoryId);
        categoryRepository.delete(categoryToDelete);
    }
}
