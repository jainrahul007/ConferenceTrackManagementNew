package com.trackmanagement.helper;

public class SessionConfiguration {

    public static final int SESSION_START_TIME = 9; // 24 hr format.
    public static final int SESSION_END_TIME = 17;
    public static final int LUNCH_TIME = 12;
    public static final int MINS_PER_HOUR = 60;

    public static final int MORNING_SESSION_DURATION = 180;
    public static final int AFTERNOON_SESSION_DURATION = 240;

    public enum SessionType {
        MORNING,
        AFTERNOON
    }
}
