package com.blstream.stalker.model;

/**
 * Used for store open and closed Time of Place
 */
public interface IOpenHours {

    /**
     * Sets hours to specified
     * @param timeOpened time open to be set
     * @param timeClosed time closed to be set
     */
    void setHours(long timeOpened, long timeClosed);

    /**
     *
     * @return time when open
     */
    long getOpenTime();

    /**
     *
     * @return time when closed
     */
    long getCloseTime();
}
