package pt.isel.ps.li61n;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RsbWebserviceApplication {

	/**
	 * Logger
	 */
	public static final Logger logger = org.slf4j.LoggerFactory.getLogger(RsbWebserviceApplication.class);


	public static void main(String[] args) {
		logger.debug("Iniciar Aplicação...");
		SpringApplication.run(RsbWebserviceApplication.class, args);
	}
}
