package ro.sda.eventsFinalProject.service;

import org.springframework.stereotype.Service;
import ro.sda.eventsFinalProject.model.Category;
import ro.sda.eventsFinalProject.model.Event;
import ro.sda.eventsFinalProject.repository.EventRepository;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final CategoryService categoryService;

    public EventService(EventRepository eventRepository, CategoryService categoryService) {
        this.eventRepository = eventRepository;
        this.categoryService = categoryService;
    }
    public Event saveEvent(Event eventToBeSaved){
        if (eventToBeSaved == null){
            throw new IllegalArgumentException("An event must have body");
        }
        if(eventToBeSaved.getName() == null){
            // Avoids the generation of NullPointerException for null event names!
            throw new IllegalArgumentException("An event must have a name");
        }

        if (eventToBeSaved.getStartDate() == null || eventToBeSaved.getEndDate() == null ||
                eventToBeSaved.getStartDate().isAfter(eventToBeSaved.getEndDate())){
            throw new IllegalArgumentException("Start date is after end date. Please be careful");
        }


        Event savedEvent = eventRepository.save(eventToBeSaved);
        return savedEvent;
    }
    public Event readEvent(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Events id must not be null!");
        }
        Event event = eventRepository.findById(id).orElse(null);
        if (event == null) {
            throw new IllegalArgumentException("There it is no event with " + id);
        }
        return event;
    }

    public List<Event> readAllEvents(){
        return eventRepository.findAll();
    }

    public Event eventUpdate(Event updatedEvent){
        if (updatedEvent == null){
            throw new IllegalArgumentException("An event must have body");
        }
        Event eventToUpdate = readEvent(updatedEvent.getId());
        eventRepository.save(updatedEvent);
        return eventToUpdate;
    }
    public void deleteEvent(Integer eventID){
        Event eventToDelete = readEvent(eventID);
        eventToDelete.setCategory(null);
        eventRepository.delete(eventToDelete);
    }

//    public Event addToCategory(Integer eventId, Category category) {
//        Category categoryToAdd = categoryService.readCategory(category.getId());
//        Event event = readEvent(eventId);
//        event.setCategory(categoryToAdd);
//        Event eventWith = saveEvent(event);
//        return eventWith;
//    }
//    public Event removeFromCategory(Integer id, Category category){
//        Event event = readEvent(id);
//        event.setCategory(null);
//        Event eventWithOut = saveEvent(event);
//
//        return eventWithOut;
//    }

}
