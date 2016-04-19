package com.blstream.stalker.model;

import android.support.annotation.NonNull;

/**
 * Used for strogin open hours of each place
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
    public OpenHours(@NonNull String timeOpened, @NonNull String timeClosed) {
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
