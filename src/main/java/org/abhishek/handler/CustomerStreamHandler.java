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
/*
data:{"id":1,"name":"Customer 1"}
data:{"id":2,"name":"Customer 2"}
data:{"id":3,"name":"Customer 3"}
data:{"id":4,"name":"Customer 4"}
data:{"id":5,"name":"Customer 5"}
data:{"id":6,"name":"Customer 6"}
data:{"id":7,"name":"Customer 7"}
data:{"id":8,"name":"Customer 8"}
data:{"id":9,"name":"Customer 9"}
data:{"id":10,"name":"Customer 10"}
*/
	
}
