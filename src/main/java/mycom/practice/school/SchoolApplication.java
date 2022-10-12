package mycom.practice.school;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchoolApplication {
	private static final Logger logger = LoggerFactory.getLogger(SchoolApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
		logger.info("http://localhost:8080/swagger-ui/" + "\n"+
				"http://localhost:8080/h2-console" + "\n" +
				"http://localhost:8080/v2/api-docs");
		}

}
