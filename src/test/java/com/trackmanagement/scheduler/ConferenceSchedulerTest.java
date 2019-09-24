package com.trackmanagement.scheduler;

import com.trackmanagement.exception.NoSchedulingException;
import com.trackmanagement.model.Talk;
import com.trackmanagement.processor.InputReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConferenceSchedulerTest {

    private ConferenceScheduler conferenceScheduler;
    private InputReader inputReader;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        conferenceScheduler = new ConferenceSchedulerImpl();
        inputReader = new InputReader();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testScheduleTalk() throws Exception {
        List<Talk> inputTalkList = inputReader.getInputListofTalk("/talk-input.txt");
        conferenceScheduler.schedule(inputTalkList);
        assertEquals(readFile("/talk-output.txt"), outContent.toString());
    }

    @Test
    void testAdditionalScheduleTalk() throws Exception {
        List<Talk> inputTalkList = inputReader.getInputListofTalk("/more-talk-input.txt");
        conferenceScheduler.schedule(inputTalkList);
        assertEquals(33, inputTalkList.size());
        assertEquals(readFile("/more-talk-output.txt"), outContent.toString());

    }

    @Test
    void testEmptyScheduleTalk() {
        List<Talk> inputTalkList = inputReader.getInputListofTalk("/talk-input-empty.txt");
        assertThrows(NoSchedulingException.class,() -> conferenceScheduler.schedule(inputTalkList));
    }

    @Test
    void testBeyondRangeScheduleTalk() {
        List<Talk> inputTalkList = inputReader.getInputListofTalk("/talk-input-additionaltime.txt");
        assertThrows(NoSchedulingException.class,() -> conferenceScheduler.schedule(inputTalkList));
    }



    private String readFile(String fileName) throws IOException {
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        InputStream in = this.getClass().getResourceAsStream(fileName);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

}