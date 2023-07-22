package ro.sda.eventsFinalProject.service;

import org.springframework.stereotype.Service;
import ro.sda.eventsFinalProject.model.Event;
import ro.sda.eventsFinalProject.repository.EventRepository;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    public Event saveEvent(Event eventToBeSaved){
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
        // orElse (object the OtherValue)
        // if (this.value != null
        //     return this.value
        // else
        //     return theOtherValue
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
        Event eventToUpdate = readEvent(updatedEvent.getId());
        eventRepository.save(updatedEvent);
        return eventToUpdate;
    }
}
