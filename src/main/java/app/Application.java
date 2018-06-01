package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	/**
	 * Punto de entrada de la aplicación.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Inicializo Spring.
		SpringApplication.run(Application.class, args);
	}

}
