package tfip.csf.movie.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import tfip.csf.movie.models.Comment;
import tfip.csf.movie.models.Review;
import tfip.csf.movie.services.CommentService;
import tfip.csf.movie.services.MovieApiService;

@RestController
@RequestMapping
public class MovieController {

    @Autowired
    private MovieApiService movieApiSvc;

    @Autowired
    private CommentService commentSvc;

    @GetMapping(path="api/search", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getReviews(@RequestParam(required=true) String query) throws IOException {
        Optional<List<Review>> opt = movieApiSvc.getReviews(query);
        List<Review> reviews = opt.get();
        this.commentSvc.setCommentCount(reviews);
        
        JsonArrayBuilder jarrBuilder = Json.createArrayBuilder();
        for (Review review : reviews) {
            jarrBuilder.add(Review.toJSON(review));
        }
        JsonArray results = jarrBuilder.build();
        //System.out.println(results.toString());
        return ResponseEntity.status(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(results.toString());
    }

    @PostMapping(path="api/comment", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveComment(@RequestBody Comment comment) {
        String resp = this.commentSvc.saveComment(comment);
        return ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resp);
    }
    
}
