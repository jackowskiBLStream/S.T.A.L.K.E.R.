package com.blstream.stalker.model.interfaces;

/**
 * Used for store Reviews for Places
 */
public interface IReviews {

    /**
     *
     * @return review's Author
     */
    String getAuthor();

    /**
     *
     * @return review's content
     */
    String getReview();

    /**
     *
     * @return place rating
     */
    int getRating();
}
