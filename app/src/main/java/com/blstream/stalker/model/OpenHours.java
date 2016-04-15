package com.blstream.stalker.model;

import com.blstream.stalker.model.interfaces.IOpenHours;

/**
 * Created by Patryk Gwiazdowski on 15.04.2016.
 * // Good Job Patryk
 */
public class OpenHours implements IOpenHours {

    private String timeOpened;
    private String timeClosed;

    public OpenHours(String timeClosed, String timeOpened) {
        this.timeClosed = timeClosed;
        this.timeOpened = timeOpened;
    }

    /**
     * Sets hours to specified
     *
     * @param timeOpened time open to be set
     * @param timeClosed time closed to be set
     */
    @Override
    public void setHours(String timeOpened, String timeClosed) {
        this.timeClosed = timeClosed;
        this.timeOpened = timeOpened;
    }

    /**
     * @return time when open
     */
    @Override
    public String getOpenTime() {
        return timeOpened;
    }

    /**
     * @return time when closed
     */
    @Override
    public String getCloseTime() {
        return timeClosed;
    }
}
