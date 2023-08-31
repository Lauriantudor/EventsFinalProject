package ro.sda.eventsFinalProject.initializer;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ro.sda.eventsFinalProject.model.Category;
import ro.sda.eventsFinalProject.model.Event;
import ro.sda.eventsFinalProject.service.CategoryService;
import ro.sda.eventsFinalProject.service.EventService;

import java.time.LocalDateTime;

@Component
public class DataBaseInitializer {
   private final EventService eventService;
   private final CategoryService categoryService;

   public DataBaseInitializer(EventService eventService, CategoryService categoryService){
       this.eventService =eventService;
       this.categoryService = categoryService;
   }
    @EventListener(ApplicationReadyEvent.class)
    public void initDataBase(){
        Category c1 =new Category(null,"streatFood",null);
        Category c2 =new Category(null,"stendup",null);
        categoryService.saveCategory(c1);
        categoryService.saveCategory(c2);
       Event e1 = new Event(null,
                "UNTOLD",

                LocalDateTime.of(2023, 8, 11, 20, 0, 0),
                LocalDateTime.of(2023,8,14,23,59,59),
                "Festival de muzică",
                "Cluj-Napoca",

                "https://s.inyourpocket.com/img/text/romania/cluj-napoca/top-10-events-cluj-napoca.jpg",
               c1);

        Event e2 =  new Event(null,
                "Concert Guns N'Roses",
                LocalDateTime.of(2023, 9, 15, 20, 0, 0),
                LocalDateTime.of(2023,9,15,23,59,59),
                "Concert rock",
                "București",
                "https://cdn.pixabay.com/photo/2019/08/13/08/48/bucuresti-4402855_1280.jpg",c1);




        eventService.saveEvent(e1);
        eventService.saveEvent(e2);

    }
}
