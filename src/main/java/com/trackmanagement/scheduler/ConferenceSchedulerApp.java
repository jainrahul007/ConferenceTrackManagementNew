package com.trackmanagement.scheduler;

import com.trackmanagement.exception.NoSchedulingException;
import com.trackmanagement.model.Talk;
import com.trackmanagement.processor.InputReader;
import com.trackmanagement.util.Logger;

import java.util.List;

public class ConferenceSchedulerApp {
    private static Logger logger = Logger.getLogger();

    public static void main(String[] args) {
        if (args.length != 1) {
            logger.fatal("Error: Invalid number of arguments");
            logger.fatal("Usage: <path_to_input_file>");
            System.exit(1);
        }
        String fileName = args[0];
        InputReader inputReader = new InputReader();
        List<Talk> talks = inputReader.getInputListofTalk(fileName);
        ConferenceScheduler scheduler = new ConferenceSchedulerImpl();
        try {
            scheduler.schedule(talks);
        } catch (NoSchedulingException e) {
            logger.error("No Scheduling Done");
        }
    }
}
