package ro.sda.eventsFinalProject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sda.eventsFinalProject.model.Category;
import ro.sda.eventsFinalProject.service.CategoryService;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    public ResponseEntity createCategory(@RequestBody Category category) {
        if (category.getId() != null) {
            return new ResponseEntity("Id must be empty", HttpStatus.BAD_REQUEST);
        }
        try {
            Category savedCategory = categoryService.saveCategory(category);
            return new ResponseEntity(savedCategory, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity readCategory(@PathVariable Integer id) {
        try {
            Category readCategory = categoryService.readCategory(id);
            return new ResponseEntity(readCategory, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/categories")
    public ResponseEntity readAllCategories() {
        List<Category> allCategories = categoryService.readAllCategories();
        return new ResponseEntity(allCategories, HttpStatus.OK);
    }
    @PutMapping("/categories/{id}")
    public ResponseEntity updateCategories(@PathVariable Integer id, @RequestBody Category updatedCategory) {
        if (!id.equals(updatedCategory.getId())) {
            return new ResponseEntity("Inconsistent ID", HttpStatus.BAD_REQUEST);
        }
        try {
            Category updated = categoryService.categoryUpdate(updatedCategory);
            return new ResponseEntity(updated,HttpStatus.OK);
        }catch (IllegalArgumentException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/categories/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        try {
            categoryService.deleteCategory(id);
            return new ResponseEntity("Category deleted sucesfuly",HttpStatus.OK);
        }catch (IllegalArgumentException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
