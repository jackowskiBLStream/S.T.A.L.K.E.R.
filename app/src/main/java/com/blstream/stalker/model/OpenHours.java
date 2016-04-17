package com.blstream.stalker.model;

import android.support.annotation.NonNull;

/**
 * Created by Patryk Gwiazdowski on 15.04.2016.
 * // Good Job Patryk
 */
public class OpenHours {

    private String timeOpened;
    private String timeClosed;

    /**
     * Constructor that sets hours to specified
     *
     * @param timeOpened time open to be set
     * @param timeClosed time closed to be set
     */
    public OpenHours(@NonNull String timeClosed,@NonNull String timeOpened) {
        this.timeClosed = timeClosed;
        this.timeOpened = timeOpened;
    }

    /**
     * @return time when open
     */
    public String getOpenTime() {
        return timeOpened;
    }

    /**
     * @return time when closed
     */
    public String getCloseTime() {
        return timeClosed;
    }

    @Override
    public String toString() {
        return "OpenHours{" +
                "timeOpened='" + timeOpened + '\'' +
                ", timeClosed='" + timeClosed + '\'' +
                '}';
    }
}
