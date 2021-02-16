package com.Mq.activemq.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.Mq.activemq.entites.Weather;
import com.Mq.activemq.service.ProducerService;

@RestController
public class ProducerController {
	
	@Autowired
	public ProducerService producerService;
	
	@GetMapping("currentWeather/{place}")
	public Weather currentWearther(@PathVariable String place) throws IOException{
		return producerService.currentWeather(place);
	}

	
}
