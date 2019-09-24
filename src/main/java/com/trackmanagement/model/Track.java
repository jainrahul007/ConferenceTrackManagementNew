package com.trackmanagement.model;

import com.trackmanagement.helper.StringConstants;
import com.trackmanagement.util.Logger;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.trackmanagement.helper.SessionConfiguration.SessionType;

public class Track {
    private static Logger logger = Logger.getLogger();

    private Session morningSession;
    private Session afterNoonSession;

    public Track()
    {
        morningSession = new Session(SessionType.MORNING);
        afterNoonSession = new Session(SessionType.AFTERNOON);
    }

    public boolean allocateTalk(Talk talk) {
        if (getFreeTimeInSession(afterNoonSession) >= talk.getTalkTimeinMins()) {
            if (afterNoonSession.getTalkList().isEmpty()) {
                LocalTime startTime = LocalTime.of(13, 0);
                talk.setStartTime(startTime);
            } else {
                Talk lastTalk = afterNoonSession.getTalkList().get(afterNoonSession.getTalkList().size() - 1);
                LocalTime startTime = lastTalk.getEndTime();
                talk.setStartTime(startTime);
            }
            afterNoonSession.getTalkList().add(talk);
            return true;
        } else if (getFreeTimeInSession(morningSession) >= talk.getTalkTimeinMins()) {
            if (morningSession.getTalkList().isEmpty()) {
                LocalTime startTime = LocalTime.of(9, 0);
                talk.setStartTime(startTime);
            } else {
                Talk lastTalk = morningSession.getTalkList().get(morningSession.getTalkList().size() - 1);
                LocalTime startTime = lastTalk.getEndTime();
                talk.setStartTime(startTime);
            }
            morningSession.getTalkList().add(talk);
            return true;
        } else {
            return false;
        }
    }

    public void addNetworkingToTrack() {
        Talk networking = new Talk(StringConstants.NETWORKING_EVENT, 60);
        Talk lastTalk = afterNoonSession.getTalkList().get(afterNoonSession.getTalkList().size() - 1);
        LocalTime startTime =
                lastTalk.getEndTime().isBefore(LocalTime.of(16, 0)) ? LocalTime.of(16, 0) : lastTalk.getEndTime();

        networking.setStartTime(startTime);
        afterNoonSession.getTalkList().add(networking);
    }

    public void printSchedule() {
        List<Talk> talkList = new ArrayList<>(morningSession.getTalkList());
        Talk lunch = new Talk(StringConstants.LUNCH, 60);
        LocalTime lunchStartTime = LocalTime.of(12, 0);
        lunch.setStartTime(lunchStartTime);
        talkList.add(lunch);
        talkList.addAll(afterNoonSession.getTalkList());

        for (Talk talk : talkList) {
            logger.info(talk);
        }
    }

    private int getScheduledTime(List<Talk> talks) {
        int scheduledTime = 0;
        if (talks == null) {
            return scheduledTime;
        }
        for (Talk talk : talks) {
            scheduledTime += talk.getTalkTimeinMins();
        }
        return scheduledTime;
    }

    private int getFreeTimeInSession(Session session) {
        return session.getTotalSessionTimeinMins() - getScheduledTime(session.getTalkList());
    }
}
