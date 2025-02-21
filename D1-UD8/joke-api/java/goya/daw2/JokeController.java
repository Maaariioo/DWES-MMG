package goya.daw2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/jokes")
public class JokeController {

    private final String EXTERNAL_API = "http://official-joke-api.appspot.com/random_joke";

    @GetMapping("/random")
    public Joke getJokeFromExternalAPI() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(EXTERNAL_API, Joke.class);
    }
}
