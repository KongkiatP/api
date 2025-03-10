package com.interview.api.controller;

import com.interview.api.service.imp.DefaultEventServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {
    private final DefaultEventServiceImpl eventService;

    public EventController(DefaultEventServiceImpl eventService) {
        this.eventService = eventService;
    }
}
