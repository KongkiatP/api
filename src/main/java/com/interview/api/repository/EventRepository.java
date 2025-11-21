package com.interview.api.repository;

import com.interview.api.entity.EventEntity;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<EventEntity, Integer> {

    List<EventEntity> findTop3ByOrderByCostDesc();

    List<EventEntity> findTop3ByOrderByDurationDesc();

    @Query("SELECT SUM(e.cost) FROM EventEntity e ")
    BigDecimal findTotalCost();

    @Query("SELECT SUM(e.duration) FROM EventEntity e")
    BigDecimal findTotalDuration();

    @Query("SELECT e.location, COUNT(e.id) AS totalEvent, SUM(e.cost), SUM(e.duration) " +
            "FROM EventEntity e " +
            "GROUP BY e.location " +
            "ORDER BY totalEvent DESC")
    List<Object[]> findReport();
}
