package tfip.csf.movie.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Review {
    
    private String title;
    private String rating;
    private String byline;
    private String headline;
    private String summary;
    private String url;
    private String image;
    private Integer commentCount;
    
    public Review() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public static Review fromJSON(JsonObject jo) {
        Review review = new Review();
        review.setTitle(jo.getString("display_title"));
        review.setRating(jo.getString("mpaa_rating"));
        review.setByline(jo.getString("byline"));
        review.setHeadline(jo.getString("headline"));
        review.setSummary(jo.getString("summary_short"));
        if (!jo.isNull("link")){
            review.setUrl(jo.getJsonObject("link").getString("url"));
        } else {
            review.setUrl("");
        }
        if (!jo.isNull("multimedia")){
            review.setImage(jo.getJsonObject("multimedia").getString("src"));
        } else {
            review.setImage("");
        }
        return review;
    }

    public static List<Review> create(String json) throws IOException{
        List<Review> reviews = new LinkedList<>();
        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader jrd = Json.createReader(is);
            JsonObject jo = jrd.readObject();
            if (!jo.isNull("results")){
                reviews = jo.getJsonArray("results").stream()
                            .map(review -> (JsonObject)review)
                            .map(review -> Review.fromJSON(review))
                            .toList();
            }
        }
        return reviews;
    }

    public static JsonObject toJSON(Review review) {
        return Json.createObjectBuilder()
                    .add("title",review.getTitle())
                    .add("rating",review.getRating())
                    .add("byline",review.getByline())
                    .add("headline",review.getHeadline())
                    .add("summary",review.getSummary())
                    .add("url",review.getUrl())
                    .add("image",review.getImage())
                    .add("commentcount", review.getCommentCount())
                    .build();
    }
    
}
