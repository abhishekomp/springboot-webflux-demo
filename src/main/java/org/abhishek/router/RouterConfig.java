package org.abhishek.router;

import org.abhishek.handler.CustomerHandler;
import org.abhishek.handler.CustomerStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {
	
	@Autowired
	private CustomerHandler customerHandler;
	
	@Autowired
	private CustomerStreamHandler streamHandler;
	
	
	//http://localhost:9191/router/customers/stream
	//http://localhost:9191/router/customer/save
	//http://localhost:9191/router/customers
	@Bean
	public RouterFunction<ServerResponse> routerFunction(){
		return RouterFunctions.route()
				.GET("/router/customers", customerHandler::loadCustomers)	//this shows all records immediately as a single object containing the list of customers
				.GET("/router/customers/stream", streamHandler::loadCustomers)	//this is building one after another
				.GET("/router/customer/{input}", customerHandler::findCustomer)
				.POST("/router/customer/save", customerHandler::saveCustomer)
				.build();
	}
	
}
