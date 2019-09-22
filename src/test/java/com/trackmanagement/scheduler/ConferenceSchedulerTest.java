package com.trackmanagement.scheduler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConferenceSchedulerTest {

    private ConferenceScheduler conferenceScheduler;

    @BeforeEach
    void setUp() {
        conferenceScheduler = new ConferenceScheduler();
    }

    @Test
    void scheduleTalk() {
        conferenceScheduler.schedule("/talk-input.txt");
    }
}