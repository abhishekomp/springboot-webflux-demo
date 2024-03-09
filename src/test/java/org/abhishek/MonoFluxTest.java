package org.abhishek;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {
	
	@Test
	void should_test_Flux() {
		Flux<Integer> myFlux = Flux.just("foo", "bar")
			.map(String::length);
		System.out.println(myFlux);	//FluxMapFuseable
	}
	
	@Test
	void should_test_Flux_2() {
		Flux<String> myFlux = Flux.just("foo", "bar").log();
		myFlux.subscribe(System.out::println);
	}
	
	@Test
	void should_test_Flux_3() {
		Flux<String> myFlux = Flux.just("foo", "bar")
				.concatWithValues("bubbu")
				.log();
		myFlux.subscribe(System.out::println);
	}
	
	@Test
	public void test_mono() {
		Mono<String> monoString = Mono.just("ReactiveProgramming").log();
		monoString.subscribe(System.out::println);
	}
	
	@Test
	public void test_mono_error() {
		Mono<?> monoString = Mono.just("ReactiveProgramming")
				.then(Mono.error(new RuntimeException("An Exception occurred")))
				.log();
		monoString.subscribe(System.out::println, (e) -> System.out.println("Received error from Mono:" + e.getMessage()));
	}

}
