package com.trackmanagement.processor;

import com.trackmanagement.model.Talk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputReaderTest {

    private InputReader inputReader;

    @BeforeEach
    void setUp() {
        inputReader = new InputReader();
    }

    @Test
    void testGetInputListofTalk() {
        List<Talk> inputTalkList = inputReader.getInputListofTalk("/talk-input.txt");

        assertEquals(785, inputReader.getTotalInputTalktimeinMins());
        assertEquals(19, inputTalkList.size());
    }

    @Test
    void testGetEmptyInputListofTalk() {
        List<Talk> inputTalkList = inputReader.getInputListofTalk("/talk-input-empty.txt");
        assertEquals(0, inputReader.getTotalInputTalktimeinMins());
    }

    @Test
    void testGetAdditionalInputListofTalk() {
        List<Talk> inputTalkList = inputReader.getInputListofTalk("/talk-input-additionaltime.txt");
        assertEquals(1085, inputReader.getTotalInputTalktimeinMins());
    }
}