package com.trackmanagement.scheduler;

import com.trackmanagement.exception.NoSchedulingException;
import com.trackmanagement.model.Talk;

import java.util.List;

public interface ConferenceScheduler {
    void schedule(List<Talk> talks) throws NoSchedulingException;
}
