package org.ticket.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "org.ticket.restapi.controller", "org.ticket.restapi.service" })
@EntityScan(basePackages = { "org.ticket.restapi.entity", "org.ticket.restapi.domain" })
@EnableJpaRepositories(basePackages = { "org.ticket.restapi.repository" })
public class RestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
	}

}
