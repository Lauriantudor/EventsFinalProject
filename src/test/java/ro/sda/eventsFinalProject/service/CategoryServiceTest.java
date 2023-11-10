package ro.sda.eventsFinalProject.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.sda.eventsFinalProject.repository.CategoryRepository;
import ro.sda.eventsFinalProject.repository.EventRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private EventRepository eventRepository;
    private CategoryService underTest;
    @BeforeEach
    void setUp(){
        underTest = new CategoryService(categoryRepository, eventRepository);
    }
    @Test
    void saveCategoryTest() {
    }

    @Test
    void readCategory() {
    }

    @Test
    void getEventsOfCategory() {
    }

    @Test
    void readAllCategoriesTest() {
        underTest.readAllCategories();

        verify(categoryRepository).findAll();
    }

    @Test
    void categoryUpdate() {
    }

    @Test
    void deleteCategory() {
    }
}