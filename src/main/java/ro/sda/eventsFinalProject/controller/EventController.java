package ro.sda.eventsFinalProject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sda.eventsFinalProject.model.Event;
import ro.sda.eventsFinalProject.service.EventService;

import java.util.List;

@RestController
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService){
        this.eventService=eventService;
    }
    @PostMapping("/events")
    public ResponseEntity createEvent(@RequestBody Event event){
       if (event.getId() != null){
           return new ResponseEntity("Id must be empty",HttpStatus.BAD_REQUEST);
       }
        try {
            Event savedEvent= eventService.saveEvent(event);
            return new ResponseEntity(savedEvent, HttpStatus.OK);
        }catch (IllegalArgumentException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/events/{id}")
    public ResponseEntity readEvent(@PathVariable(name = "id") Integer eventId) {
        try {
            Event readEvent = eventService.readEvent(eventId);
            return new ResponseEntity(readEvent, HttpStatus.OK);
        } catch (IllegalArgumentException iAE) {
            return new ResponseEntity(iAE.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/events")
    public ResponseEntity readAllEvents() {
        List<Event> allEvents = eventService.readAllEvents();
        return new ResponseEntity(allEvents, HttpStatus.OK);
    }

    @PutMapping("/events/{id}")
    public ResponseEntity update(@PathVariable(name = "id") Integer pathId, @RequestBody Event updatedEvent) {
        if (!pathId.equals(updatedEvent.getId())) {
            return new ResponseEntity("Inconsistent ID", HttpStatus.BAD_REQUEST);
        }
        try {
            Event updated = eventService.eventUpdate(updatedEvent);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (IllegalArgumentException iAE){
            return new ResponseEntity<>(iAE.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        try {
            eventService.deleteEvent(id);
            return new ResponseEntity("Event deleted sucesfuly ", HttpStatus.OK);
        }catch (IllegalArgumentException ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
