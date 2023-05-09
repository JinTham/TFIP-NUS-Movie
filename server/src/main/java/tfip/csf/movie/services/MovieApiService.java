package tfip.csf.movie.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import tfip.csf.movie.models.Review;

@Service
public class MovieApiService {

    @Value("${nytimes.ApiKey}")
    private String nytimesApiKey;

    private static final String URL = "https://api.nytimes.com/svc/movies/v2/reviews/search.json";
    
    public Optional<List<Review>> getReviews(String movieName) throws IOException{

        String API_URL = UriComponentsBuilder.fromUriString(URL)
                            .queryParam("query", movieName)
                            .queryParam("api-key", nytimesApiKey)
                            .toUriString();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.getForEntity(API_URL,String.class);
        List<Review> reviews = Review.create(resp.getBody());
        if (reviews != null) {
            return Optional.of(reviews);
        }
        return Optional.empty();
    }

}
