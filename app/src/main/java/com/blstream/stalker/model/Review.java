package com.blstream.stalker.model;

import com.blstream.stalker.model.interfaces.IReviews;

/**
 * Used for Storing Place Reviews
 */
public class Review implements IReviews {
    String author;
    String review;
    int rating;

    public Review(String author, String review, int rating) {
        this.author = author;
        this.review = review;
        this.rating = rating;
    }

    /**
     * @return review's Author
     */
    @Override
    public String getAuthor() {
        return author;
    }

    /**
     * @return review's content
     */
    @Override
    public String getReview() {
        return review;
    }

    /**
     * @return place rating
     */
    @Override
    public int getRating() {
        return rating;
    }
}
