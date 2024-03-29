package com.trackmanagement.scheduler;

import com.trackmanagement.exception.NoSchedulingException;
import com.trackmanagement.helper.SessionConfiguration;
import com.trackmanagement.model.Talk;
import com.trackmanagement.model.Track;
import com.trackmanagement.util.Logger;

import java.util.ArrayList;
import java.util.List;

public class ConferenceSchedulerImpl implements ConferenceScheduler{

    private static Logger logger = Logger.getLogger();

    private List<Track> trackList = new ArrayList<>();

    public void schedule(List<Talk> talks) throws NoSchedulingException {
        scheduleByFirstFitDecreasing(talks);
        printTracks(trackList);
    }

    protected void scheduleByFirstFitDecreasing(List<Talk> talks) throws NoSchedulingException {

        if(talks.isEmpty())
        {
            logger.fatal("Error: No talk receieved for scheduling.");
            throw new NoSchedulingException("No talk receieved for scheduling.");
        }

        talks.sort((o1, o2) -> o2.getTalkTimeinMins() - o1.getTalkTimeinMins());

        Talk longestTalk = talks.get(0);
        int longestTalkDuration = longestTalk.getTalkTimeinMins();

        Talk shortestTalk = talks.get(talks.size() - 1);
        int shortestTalkDuration = shortestTalk.getTalkTimeinMins();

        if (longestTalkDuration > Math.max(SessionConfiguration.MORNING_SESSION_DURATION, SessionConfiguration.AFTERNOON_SESSION_DURATION)
                || shortestTalkDuration < 0) {
            logger.fatal("Error: Some task are beyond the permissible range.");
            throw new NoSchedulingException("Some task are beyond the permissible range.");
        }

        for (Talk talk : talks) {
            boolean isTalkOccupied = false;
            for (Track track : trackList) {
                if (track.allocateTalk(talk)) {
                    isTalkOccupied = true;
                    break;
                }
            }
            if (!isTalkOccupied) {
                Track track = new Track();
                track.allocateTalk(talk);
                trackList.add(track);
            }
        }
        for (Track track : trackList) {
            track.addNetworkingToTrack();
        }
    }

    private void printTracks(List<Track> tracks) {
        int trackCount = 1;
        for (Track track : tracks) {
            logger.info("Track " + trackCount++ + ":");
            track.printSchedule();
        }
    }
}