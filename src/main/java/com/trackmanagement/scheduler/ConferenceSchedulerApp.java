package com.trackmanagement.scheduler;

import com.trackmanagement.util.Logger;

public class ConferenceSchedulerApp {
    private static Logger logger = Logger.getLogger();

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Error: Invalid number of arguments");
            System.out.println("Usage: <script_name> <path_to_input_file>");
            System.exit(1);
        }
        String fileName = args[0];
        ConferenceScheduler scheduler = new ConferenceScheduler();
        scheduler.schedule(fileName);
    }
}
