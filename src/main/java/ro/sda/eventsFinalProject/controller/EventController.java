package ro.sda.eventsFinalProject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.sda.eventsFinalProject.model.Event;
import ro.sda.eventsFinalProject.service.EventService;

@RestController
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService){
        this.eventService=eventService;
    }
    @PostMapping("/events")
    public ResponseEntity createEvent(@RequestBody Event event){
        try {
            Event savedEvent= eventService.saveEvent(event);
            return new ResponseEntity(savedEvent, HttpStatus.OK);
        }catch (IllegalArgumentException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
