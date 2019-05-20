package rocks.monsees.statusboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StatusboardApplication {

	private static final Logger logger = LoggerFactory.getLogger(StatusboardApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StatusboardApplication.class, args);
	}

	
	
	@Bean
	CommandLineRunner runner() {
		return args -> {
//			logger.debug("Loading data");
			//
		};
	}

}
