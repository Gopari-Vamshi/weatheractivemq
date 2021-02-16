package com.Mq.activemq.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import com.Mq.activemq.entites.Weather;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProducerService {
	
	@Autowired
	public JmsMessagingTemplate jmsMessagingTemplate;
	
	@Autowired
	public Queue queue;

	@Value("${weather.api}")
	private String weatherapi;
	
	@Value("${weather.apikey}")
	private String weatherapikey;
	
	public Weather currentWeather(String place) throws IOException{
		Logger logger = Logger.getLogger("PRODUCER");
		logger.log(Level.INFO, "Invoking Producer");
		URL url = new URL(weatherapi+"key="+weatherapikey+"&q=" + place);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("accept", "application/json");
		InputStream responseInputStream = connection.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		Weather weather = mapper.readValue(responseInputStream, Weather.class);
		this.jmsMessagingTemplate.convertAndSend(this.queue, weather);
		return weather;	
	}
}
