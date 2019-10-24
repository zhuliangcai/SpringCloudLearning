package org.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplicationHA {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplicationHA.class, args);
	}
}