package com.blstream.stalker.model.interfaces;

import java.util.Collection;

/**
 * Created by Patryk Gwiazdowski on 13.04.2016.
 * // Good Job Patryk
 */
public interface IPlaceDataDetails {

    /**
     *
     * @return open hours for all days in week
     */
    IOpenHours[] getAllOpenHours();

    /**
     *
     * @param day day number 0 - monday ... 6 - sunday
     * @return hours when place is opened at day specified
     */
    IOpenHours getOpenHours(int day);

    /**
     *
     * @return place rating
     */
    double getRating();

    /**
     *
     * @return three last place reviews
     */
    Collection<IReviews> getReviews();
}
