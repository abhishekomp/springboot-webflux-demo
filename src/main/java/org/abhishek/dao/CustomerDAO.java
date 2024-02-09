package org.abhishek.dao;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

import java.time.Duration;

import org.abhishek.dto.Customer;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

@Component
public class CustomerDAO {
	
	private static void sleepExecution(int i) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public List<Customer> getCustomers(){
		return IntStream.rangeClosed(1, 10)
				.peek(CustomerDAO::sleepExecution)
				.peek(i -> System.out.println("Processing count: " + i))
				.mapToObj(i -> new Customer(i, "Customer " + i))
				.collect(toList());
	}
	
	public Flux<Customer> getCustomersAsFlux(){
		return Flux.range(1, 10)
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(i -> System.out.println("Processing count(Flux): " + i))
				.map(i -> new Customer(i, "Customer " + i));
	}
	
	// Using this method for Functional end-point implementation
	public Flux<Customer> getCustomersForRouter(){
		return Flux.range(1, 10)
				.doOnNext(i -> System.out.println("Processing count(Flux Router): " + i))
				.map(i -> new Customer(i, "Customer " + i));
	}
}
