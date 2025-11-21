package com.interview.api.service.imp;

import com.interview.api.entity.EventEntity;
import com.interview.api.exception.BadRequestException;
import com.interview.api.exception.NotFoundException;
import com.interview.api.model.EventDto;
import com.interview.api.repository.EventRepository;
import com.interview.api.service.IEventService;
import java.util.Comparator;
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
        return eventRepository.findById(id).orElseThrow(() -> new NotFoundException("not found"));
    }

    @Override
    public List<EventEntity> findTopByParam(String orderBy) {
        switch (orderBy) {
            case "cost":
                return eventRepository.findTop3ByOrderByCostDesc();
            case "duration":
                return eventRepository.findTop3ByOrderByDurationDesc();
            default:
                throw new BadRequestException("bad request");
        }
    }

    @Override
    public BigDecimal findTotalByParam(String type) {
        switch (type) {
            case "cost":
                return eventRepository.findTotalCost();
            case "duration":
                return eventRepository.findTotalDuration();
            default:
                throw new BadRequestException("bad request");
        }
    }

    @Override
    public List<EventDto> findReport() {
        List<EventDto> data = new ArrayList<>();
        List<Object[]> reports = eventRepository.findReport();

        for (Object[] row : reports) {
            String location = (String) row[0];
            Long totalEvent = (Long) row[1];
            BigDecimal cost = (BigDecimal) row[2];
            BigDecimal duration = (BigDecimal) row[3];

            if (cost == null) {
                cost = BigDecimal.ZERO;
            }

            BigDecimal ratio;
            if (duration == null || duration.compareTo(BigDecimal.ZERO) == 0) {
                ratio = BigDecimal.ZERO;
            } else {
                ratio = cost.divide(duration, 3, RoundingMode.HALF_DOWN);
            }

            EventDto eventDto = EventDto.builder()
                    .location(location)
                    .totalEvent(totalEvent.intValue())
                    .costDurationRatio(ratio)
                    .build();

            data.add(eventDto);
        }
        data.sort(
                Comparator.comparing(
                                EventDto::getTotalEvent,
                                Comparator.reverseOrder() // มาก -> น้อย
//                                Comparator.naturalOrder() // น้อย -> มาก
                        )
                        .thenComparing(
                                EventDto::getCostDurationRatio,
                                Comparator.reverseOrder()
//                                Comparator.naturalOrder()
                        )
        );

        return data;
    }
}
