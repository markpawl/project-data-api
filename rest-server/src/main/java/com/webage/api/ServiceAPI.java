package com.webage.api;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class ServiceAPI {

	@GetMapping
	public String healthCheck() {
		Date date = new Date();
		String dateformat = SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.SHORT, SimpleDateFormat.SHORT).format(date);
		return "The Event Registration service is up and running " + dateformat ;
	}
}
