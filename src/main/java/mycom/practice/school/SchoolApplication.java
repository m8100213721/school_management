package mycom.practice.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
		System.out.println("http://localhost:8080/swagger-ui/" + "\n"+
				"http://localhost:8080/h2-console" + "\n" +
				"http://localhost:8080/v2/api-docs");
		}

}
