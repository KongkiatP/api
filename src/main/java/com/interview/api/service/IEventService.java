package com.interview.api.service;

import com.interview.api.entity.EventEntity;
import com.interview.api.model.EventDto;

import java.math.BigDecimal;
import java.util.List;

public interface IEventService {

    EventEntity findById(int id);

    List<EventEntity> findTopByParam(String orderBy);

    BigDecimal findTotalByParam(String type);

    List<EventDto> findReport();
}
