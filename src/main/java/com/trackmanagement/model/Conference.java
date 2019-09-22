package com.trackmanagement.model;


import java.util.List;

import static com.trackmanagement.helper.SessionConfiguration.*;

public class Conference {

    private int confId;
    private List<Track> trackList;
    private int totalConferenceTimeinMins;

    public Conference(int confId, List<Track> trackList) {
        this.confId = confId;
        this.trackList = trackList;
        this.totalConferenceTimeinMins = trackList.size() * (SESSION_END_TIME - SESSION_START_TIME - 1) * MINS_PER_HOUR;
    }

    public int getConfId() {
        return confId;
    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public int getTotalConferenceTimeinMins() {
        return totalConferenceTimeinMins;
    }

}
