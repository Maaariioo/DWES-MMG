package goya.daw2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JokeClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(JokeClientApplication.class, args);
	}

	@Bean
    CommandLineRunner run(JokeService jokeService) {
        return args -> {
            Joke joke = jokeService.obtenerJoke();
            System.out.println("Joke: " + joke.setup() + " - " + joke.punchline());
        };
    }
}
