package com.juniorrodrigues.gof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Projeto Spring boot gerado via spring initializer
 * Modulos :
 * 	- Sprinfg Data Jpa
 * 	- Spring Web
 * 	 - H2 Database
 * 	 - OpenFeign
 *
 * @author Valdionro Junior
 * */

@EnableFeignClients
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
