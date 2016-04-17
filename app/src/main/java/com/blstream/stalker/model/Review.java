package com.blstream.stalker.model;

/**
 * Used for Storing Place Reviews
 */
public class Review {
    String author;
    String review;
    double rating;

    /**
     * Constructor that Creates new Review and fills it with Data given in parameter
     * @param author Reviev author
     * @param review Reviev contents
     * @param rating Reviev rating from 0 to 5
     */
    public Review(String author, String review, double rating) {
        this.author = author;
        this.review = review;
        this.rating = rating;
    }

    /**
     * @return review's Author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return review's content
     */
    public String getReview() {
        return review;
    }

    /**
     * @return place rating
     */
    public double getRating() {
        return rating;
    }
}
