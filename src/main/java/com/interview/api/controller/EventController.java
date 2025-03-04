package com.interview.api.controller;

import com.interview.api.model.EventDto;
import com.interview.api.service.imp.DefaultEventServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    private final DefaultEventServiceImpl eventService;

    public EventController(DefaultEventServiceImpl eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/scan/report/eventDashboard")
    public ResponseEntity<List<EventDto>> findReport() {
        List<EventDto> result = eventService.findReport();
        return ResponseEntity.ok(result);
    }
}
