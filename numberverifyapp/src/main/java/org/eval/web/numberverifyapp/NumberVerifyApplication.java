package org.eval.web.numberverifyapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * main server application class
 * 
 * @author hgoel@244
 *
 */
@SpringBootApplication
public class NumberVerifyApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(NumberVerifyApplication.class, args);

	}

}
