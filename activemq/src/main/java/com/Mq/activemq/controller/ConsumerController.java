package com.Mq.activemq.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;

import com.Mq.activemq.entites.Weather;
import com.Mq.activemq.entites.WeatherHistory;
import com.Mq.activemq.repository.WeatherHistoryRepository;

@Component
public class ConsumerController {

	@Autowired
	private WeatherHistoryRepository historyRepository;
	
	@JmsListener(destination = "WeatherQueue")
	public void WeatherConsumer(Weather message) throws MessageConversionException, JMSException{
		Logger logger = Logger.getLogger("CONSUMER");
		logger.log(Level.INFO,"Invoking Consumer");
		Weather weather = message;
		WeatherHistory history = new WeatherHistory();
		history.setLocation(weather.getLocation().getName());
		history.setTemp_c(weather.getCurrent().getTemp_c());
		history.setTemp_f(weather.getCurrent().getTemp_f());
		history.setFeelslike_c(weather.getCurrent().getFeelslike_c());
		history.setFeelslike_f(weather.getCurrent().getFeelslike_f());
		historyRepository.save(history);
	}
}
