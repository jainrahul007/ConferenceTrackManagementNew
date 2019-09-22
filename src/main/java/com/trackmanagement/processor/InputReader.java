package com.trackmanagement.processor;

import com.trackmanagement.model.Talk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class InputReader {

    private List<Talk> talkList;
    private int totalInputTalktimeinMins;

    public List<Talk> getTalkList() {
        return talkList;
    }

    public int getTotalInputTalktimeinMins() {
        return totalInputTalktimeinMins;
    }

    public List<Talk> getInputListofTalk(String fileName) {

        InputStream in = this.getClass().getResourceAsStream(fileName);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

            talkList = new ArrayList<>();
            String line;
            int numTime;
            while ((line = reader.readLine()) != null) {
                int lastBlank = line.lastIndexOf(" ");
                String title = line.substring(0, lastBlank);
                String time = line.substring(lastBlank + 1);
                if (time.equals("lightning")) {
                    numTime = 5;
                } else {
                    // Remove "min" suffix
                    numTime = Integer.parseInt(time.substring(0, time.length() - 3));
                }
                Talk talk = new Talk(title, Integer.valueOf(numTime));

                totalInputTalktimeinMins = totalInputTalktimeinMins + numTime;
                talkList.add(talk);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return talkList;
    }
}

