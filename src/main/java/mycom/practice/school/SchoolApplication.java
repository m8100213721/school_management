package mycom.practice.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
		System.out.println("http://localhost:8080/swagger-ui/" + "\n"+
				"http://localhost:8080/h2-console");
		}

}
