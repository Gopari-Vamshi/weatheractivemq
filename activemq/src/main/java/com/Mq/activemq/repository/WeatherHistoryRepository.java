package com.Mq.activemq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Mq.activemq.entites.WeatherHistory;

@Repository
public interface WeatherHistoryRepository extends JpaRepository<WeatherHistory, Integer>{

}
