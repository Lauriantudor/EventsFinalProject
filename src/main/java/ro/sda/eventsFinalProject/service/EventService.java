package ro.sda.eventsFinalProject.service;

import org.springframework.stereotype.Service;
import ro.sda.eventsFinalProject.model.Event;
import ro.sda.eventsFinalProject.repository.EventRepository;

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
}
