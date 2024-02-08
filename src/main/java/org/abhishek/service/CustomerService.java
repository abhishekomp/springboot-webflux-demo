package org.abhishek.service;

import java.util.List;

import reactor.core.publisher.Flux;

import org.abhishek.dao.CustomerDAO;
import org.abhishek.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	public List<Customer> loadAllCustomers() {
		long start = System.currentTimeMillis();
		List<Customer> customers = customerDAO.getCustomers();
		long end = System.currentTimeMillis();
		System.out.println("Total execution duration: " + (end - start));
		return customers;
	}
	
	public Flux<Customer> loadAllCustomersStream() {
		long start = System.currentTimeMillis();
		Flux<Customer> customers = customerDAO.getCustomersAsFlux();
		long end = System.currentTimeMillis();
		System.out.println("Total execution duration: " + (end - start));
		return customers;
	}

}
