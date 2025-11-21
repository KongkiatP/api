package com.interview.api.controller;

import com.interview.api.entity.EventEntity;
import com.interview.api.exception.BadRequestException;
import com.interview.api.exception.NotFoundException;
import com.interview.api.model.EventDto;
import com.interview.api.service.imp.DefaultEventServiceImpl;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {

    private final DefaultEventServiceImpl eventService;

    public EventController(DefaultEventServiceImpl eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventEntity> findById(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok(eventService.findById(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/top3")
    public ResponseEntity<List<EventEntity>> findTopByParam(@RequestParam("by") String orderBy) {
        try {
            return ResponseEntity.ok(eventService.findTopByParam(orderBy));
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/total")
    public ResponseEntity<BigDecimal> findTotalByParam(@RequestParam("type") String type) {
        try {
            return ResponseEntity.ok(eventService.findTotalByParam(type));
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/report")
    public ResponseEntity<List<EventDto>> findReport() {
        return ResponseEntity.ok(eventService.findReport());
    }
}
