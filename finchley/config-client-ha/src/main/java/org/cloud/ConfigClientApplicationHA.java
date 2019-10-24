package org.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ConfigClientApplicationHA {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplicationHA.class, args);
	}

//	@Value("${foo}")
//	String foo;
	@Value("${config}")
	String message;
	@RequestMapping(value = "/hi")
	public String hi(){
		return "----"+message;
	}
}