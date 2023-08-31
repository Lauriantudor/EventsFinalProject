package ro.sda.eventsFinalProject.service;

import org.springframework.stereotype.Service;
import ro.sda.eventsFinalProject.model.Category;
import ro.sda.eventsFinalProject.model.Event;
import ro.sda.eventsFinalProject.repository.CategoryRepository;
import ro.sda.eventsFinalProject.repository.EventRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final EventRepository eventRepository;


    public CategoryService(CategoryRepository categoryRepository,
                           EventRepository eventRepository) {
         this.categoryRepository = categoryRepository;

        this.eventRepository = eventRepository;
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
    public List<Event> getEventsOfCategory(Integer categoryId){
        Category category  = readCategory(categoryId);
        if (category.getEvents().isEmpty()){
            throw new IllegalArgumentException("The category has no events");
        }
        List<Event> eventsOf = category.getEvents();
        return eventsOf;
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
        List<Event> eventsOf = categoryToDelete.getEvents();
        for (Event e : eventsOf){
            e.setCategory(null);
            eventRepository.save(e);
        }
        categoryRepository.delete(categoryToDelete);

    }



}
