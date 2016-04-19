package com.blstream.stalker.model;

import android.support.annotation.NonNull;

/**
 * Created by Patryk Gwiazdowski on 15.04.2016.
 * // Good Job Patryk
 */
public class OpenHours {

    private String timeOpened;
    private String timeClosed;



    public void setTimeOpened(String timeOpened) {
        this.timeOpened = timeOpened;
    }

    public void setTimeClosed(String timeClosed) {
        this.timeClosed = timeClosed;
    }

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
    public static OpenHours[] fillWithNoInfo(OpenHours[] openHours){
        for(int i = 0; i < openHours.length; i++){
            openHours[i] = new OpenHours("no info", "no info");
        }
        return openHours;
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
