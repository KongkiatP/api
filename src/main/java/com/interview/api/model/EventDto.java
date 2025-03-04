package com.interview.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private String location;
    private int totalEvent;
    private BigDecimal costDurationRatio;
}
