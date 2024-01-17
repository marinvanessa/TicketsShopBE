package com.example.tickets.controller;

import com.example.tickets.dto.EventDto;
import com.example.tickets.model.Event;
import com.example.tickets.service.impl.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class EventController {

    @Autowired
    private EventService eventService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createEvent")
    public ResponseEntity<Event> addEvent(@RequestBody EventDto eventDto) {
        Event createdEvent = eventService.addEvent(eventDto);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @GetMapping("/getAllEvents")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/getEvent")
    public ResponseEntity<Event> getEvent(@RequestParam UUID eventId) {
        Event event = eventService.getEvent(eventId);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteEvent")
    public ResponseEntity<String> deleteEvent(@RequestParam UUID eventId) {
        eventService.deleteEvent(eventId);
        return new ResponseEntity<>("Event deleted successfully!", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteAllEvents")
    public ResponseEntity<String> deleteAllEvents() {
        eventService.deleteAllEvents();
        return new ResponseEntity<>("All events deleted successfully!", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateEvent")
    public ResponseEntity<Event> updateEvent(@RequestBody EventDto eventDto, @RequestParam UUID eventId) {
        Event event =  eventService.updateEvent(eventDto, eventId);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }
}
