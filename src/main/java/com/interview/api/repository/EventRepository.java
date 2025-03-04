package com.interview.api.repository;

import com.interview.api.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Integer> {

    @Query("SELECT e.location, COUNT(e.id), SUM(e.cost), SUM(e.duration) FROM EventEntity e GROUP BY e.location")
    List<Object[]> findTotalCostAndTotalDurationGroupByLocation();
}
