package se.coredev.web.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class JpaWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaWebApplication.class, args);
	}
}
