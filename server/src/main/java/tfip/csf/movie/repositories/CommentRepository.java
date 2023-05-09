package tfip.csf.movie.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import tfip.csf.movie.models.Comment;

@Repository
public class CommentRepository {

    private static final String MONGODB_COLLECTION = "comments";

    @Autowired
    private MongoTemplate mongoTemplate;

    // db.comments.aggregate([
    //     {$match: {"moviecomment.title":"Tokyo Godfathers"}},
    //     {$count: "count"}
    // ])
    public Integer getCommentCount(String title) {
        Integer count = (int) mongoTemplate.count(Query.query(Criteria.where("moviecomment.title").is(title)),MONGODB_COLLECTION);
        return count;
    }
    
    // db.comments.insert({
    // name: "Fred",
    // rating: 3,
    // comment: "Good",
    // title: "Godfather"
    // })
    public String saveComment(Comment comment) {
        Document toSave = new Document();
        toSave.put("moviecomment",comment);
        this.mongoTemplate.insert(toSave, MONGODB_COLLECTION);
        return "Comment saved";
    }

}
