package com.flow.enginehouse;

import org.flowable.spring.boot.EndpointAutoConfiguration;
import org.flowable.spring.boot.FlowableServlet;
import org.flowable.spring.boot.rest.BaseRestApiConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

public class RestApiCfg extends BaseRestApiConfiguration {

	@Bean
	public ServletRegistrationBean processEngine() {
		FlowableServlet servlet = new FlowableServlet("/rest-api", "Custom Rest Api");
		return registerServlet(servlet, EndpointAutoConfiguration.class);
	}
}
