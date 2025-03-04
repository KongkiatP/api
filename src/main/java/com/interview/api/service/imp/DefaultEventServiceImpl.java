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
        List<Object[]> dataList = eventRepository.findTotalCostAndTotalDurationGroupByLocation();
        List<EventDto> result = new ArrayList<>();

        for (Object[] data : dataList) {
            String location = (String) data[0];
            Long totalEvent = (Long) data[1];
            BigDecimal totalCost = (BigDecimal) data[2];
            BigDecimal totalDuration = (BigDecimal) data[3];
            double costDuration = totalCost.doubleValue() / totalDuration.doubleValue();

            EventDto eventDto = EventDto.builder()
                    .location(location)
                    .totalEvent(totalEvent.intValue())
                    .costDurationRatio(new BigDecimal(costDuration).setScale(3, RoundingMode.HALF_UP))
                    .build();

            result.add(eventDto);
        }
        return result;
    }
}
