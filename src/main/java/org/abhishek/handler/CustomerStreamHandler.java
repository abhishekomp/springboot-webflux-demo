package org.abhishek.handler;

import org.abhishek.dao.CustomerDAO;
import org.abhishek.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {
	
	@Autowired
	private CustomerDAO customerDao;
	
public Mono<ServerResponse> loadCustomers(ServerRequest request) {
		
		Flux<Customer> customersForRouter = customerDao.getCustomersAsFlux(); //here we are using the stream method that we defined in the DAO class with delay.		
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(customersForRouter, Customer.class);
		
	}
	
}