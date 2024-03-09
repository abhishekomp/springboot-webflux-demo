package org.abhishek.controller;

import java.util.List;

import org.abhishek.dto.Customer;
import org.abhishek.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	//In Chrome: http://localhost:9191/customers/
	
	@Autowired
	private CustomerService customerService;

	
	@GetMapping("/")
	public List<Customer> getCustomers() {
		return customerService.loadAllCustomers();	//traditional rest end-point
	}
	
	//http://localhost:9191/customers/stream
	@GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	//@GetMapping(value = "/stream")
	public Flux<Customer> getCustomersUsingFlux() {
		return customerService.loadAllCustomersStream();
	}
	
}
