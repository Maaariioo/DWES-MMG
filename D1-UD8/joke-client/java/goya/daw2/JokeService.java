package goya.daw2;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JokeService {
    private final String API_URL = "http://localhost:8080/api/jokes/random";

    public Joke obtenerJoke() {
    	
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(API_URL, Joke.class);
    }
}
