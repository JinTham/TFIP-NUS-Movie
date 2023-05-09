package tfip.csf.movie.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tfip.csf.movie.models.Comment;
import tfip.csf.movie.models.Review;
import tfip.csf.movie.repositories.CommentRepository;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepo;

    public Integer getCommentCount(String title) {
        return this.commentRepo.getCommentCount(title);
    }

    public String saveComment(Comment comment) {
        return this.commentRepo.saveComment(comment);
    }

    public void setCommentCount(List<Review> reviews) {
        for (Review review:reviews) {
            review.setCommentCount(this.getCommentCount(review.getTitle()));
        }
    }

}
