package tfip.csf.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tfip.csf.movie.services.CommentService;
import tfip.csf.movie.services.MovieApiService;

@SpringBootApplication
public class MovieApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}

	@Autowired
	private MovieApiService movieApiSvc;

	@Autowired
	private CommentService commentSvc;

	@Override
	public void run(String... args) throws Exception {
		//movieApiSvc.getReviews("big");
		//System.out.println(commentSvc.getCommentCount("Tokyo Godfathers"));
	}

}
