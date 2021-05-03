package com.example.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.example.entitybean.Greeting;


@Controller
public class CommunicationByRestController {

	
	@Autowired
    private RestTemplate restTemplate;
	
	@GetMapping("/greeting/communication")
	public Greeting greeting() {
		String url = "http://localhost:8080/greeting";
		Greeting greeting = restTemplate.getForObject(url, Greeting.class);
		return greeting;
	}
}
