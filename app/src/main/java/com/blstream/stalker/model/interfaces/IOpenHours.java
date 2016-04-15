package com.blstream.stalker.model.interfaces;

/**
 * Used for store open and closed Time of Place
 */
public interface IOpenHours {

    /**
     * Sets hours to specified
     * @param timeOpened time open to be set
     * @param timeClosed time closed to be set
     */
    void setHours(String timeOpened, String timeClosed);

    /**
     *
     * @return time when open
     */
    String getOpenTime();

    /**
     *
     * @return time when closed
     */
    String getCloseTime();
}
