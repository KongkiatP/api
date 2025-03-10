package com.interview.api.service.imp;

import com.interview.api.entity.EventEntity;
import com.interview.api.model.EventDto;
import com.interview.api.repository.EventRepository;
import com.interview.api.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultEventServiceImpl implements IEventService {
    private final EventRepository eventRepository;

    @Autowired
    public DefaultEventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public EventEntity findById(int id) {
        return null;
    }

    @Override
    public List<EventEntity> findTopByParam(String orderBy) {
        return List.of();
    }

    @Override
    public BigDecimal findTotalByParam(String type) {
        return null;
    }

    @Override
    public List<EventDto> findReport() {
        return List.of();
    }
}
