package com.trackmanagement.model;

import com.trackmanagement.helper.SessionConfiguration;

import java.util.ArrayList;
import java.util.List;

import static com.trackmanagement.helper.SessionConfiguration.*;

public class Session {

    private List<Talk> talkList;
    private SessionConfiguration.SessionType sessionType;
    private int totalSessionTimeinMins;

    public Session(SessionType sessionType) {


        this.talkList = new ArrayList<>();
        this.sessionType = sessionType;

        if (this.sessionType == SessionType.MORNING) {
            this.totalSessionTimeinMins = (LUNCH_TIME - SESSION_START_TIME) * MINS_PER_HOUR;
        } else {
            this.totalSessionTimeinMins = (SESSION_END_TIME - LUNCH_TIME - 1) * MINS_PER_HOUR;
        }
    }


    public List<Talk> getTalkList() {
        return talkList;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public int getTotalSessionTimeinMins() {
        return totalSessionTimeinMins;
    }

}


