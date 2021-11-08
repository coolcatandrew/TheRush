package com.liprogramming.therush.dao;

import com.liprogramming.therush.model.RushingStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RushingStatisticDao extends JpaRepository<RushingStatistic, Long> {

    @Query("SELECT DISTINCT playerId FROM RushingStatistic ORDER BY created")
    List<RushingStatistic> getDistinctPlayerIdOrderByCreated();

}
