package ca.bluespurs.bluespursapitest.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class created for returning commonly used beans.
 *
 * @author Rodrigo da Silva
 * @version 1.0.0
 */
@Configuration
public class BlueSpursApiTestConfig {
	/**
	 * Creates a new {@link RestTemplate} bean to be used by the application.
	 *
	 * @return The created bean
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
