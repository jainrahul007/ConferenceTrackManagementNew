package com.trackmanagement.model;

import com.trackmanagement.helper.StringConstants;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static com.trackmanagement.helper.StringConstants.TIME_PATTERN;


public class Talk {

    private String talkTitle;
    private int talkTimeinMins;
    private LocalTime startTime;

    public Talk(String talkTitle, int talkTimeinMins) {

        this.talkTitle = talkTitle;
        this.talkTimeinMins = talkTimeinMins;
    }

    public String getTalkTitle() {
        return talkTitle;
    }

    public int getTalkTimeinMins() {
        return talkTimeinMins;
    }

    public LocalTime getEndTime() {
        return startTime.plusMinutes(talkTimeinMins);
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        StringBuilder scheduleStringBuilder = new StringBuilder();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_PATTERN);
        String startTimeString = formatter.format(startTime);
        scheduleStringBuilder.append(startTimeString);

        scheduleStringBuilder.append(StringConstants.SPACE);
        scheduleStringBuilder.append(talkTitle);

        scheduleStringBuilder.append(StringConstants.SPACE);

        if (talkTimeinMins == 5) {
            scheduleStringBuilder.append(StringConstants.LIGHTNING);
        } else {
            scheduleStringBuilder.append(talkTimeinMins + StringConstants.MINUTE);
        }

        return scheduleStringBuilder.toString();
    }


}
