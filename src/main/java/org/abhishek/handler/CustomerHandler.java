package org.abhishek.handler;

import org.abhishek.dao.CustomerDAO;
import org.abhishek.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	public Mono<ServerResponse> loadCustomers(ServerRequest request) {		
		Flux<Customer> customersForRouter = customerDAO.getCustomersForRouter();
		return ServerResponse.ok().body(customersForRouter, Customer.class);		
	}
	
	public Mono<ServerResponse> findCustomer(ServerRequest request){
		int customerId = Integer.valueOf(request.pathVariable("input"));
//		customerDAO.getCustomersForRouter()
//			.filter(c -> c.getId() == customerId)
//			.take(1)
//			.single();
		Mono<Customer> singleCustomer = customerDAO.getCustomersForRouter()
				.filter(c -> c.getId() == customerId)
				.next();
		return ServerResponse.ok().body(singleCustomer, Customer.class);
	}
	
	public Mono<ServerResponse> saveCustomer(ServerRequest request){
		Mono<Customer> customerMono = request.bodyToMono(Customer.class);
		Mono<String> stringMono = customerMono.map(dto -> dto.getId() + " " + dto.getName());
		return ServerResponse.ok().body(stringMono, String.class);
	}
}
