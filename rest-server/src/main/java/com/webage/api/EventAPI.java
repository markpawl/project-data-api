package com.webage.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webage.domain.Event;
import com.webage.repository.EventRepository;

@RestController
@RequestMapping("/events")
public class EventAPI {
	@Autowired
	EventRepository repo;

	@GetMapping
	public Iterable<Event> getAll() {
		return repo.findAll();
	}

	@GetMapping("/{eventId}")
	public Event getCustomerById(@PathVariable("eventId") long id) {
		return repo.findOne(id);
	}
}
