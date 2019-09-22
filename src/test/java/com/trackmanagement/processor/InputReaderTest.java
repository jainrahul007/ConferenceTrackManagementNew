package com.trackmanagement.processor;

import com.trackmanagement.model.Talk;
import org.junit.jupiter.api.AfterEach;
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
    void getInputListofTalk() {
        List<Talk> inputTalkMap = inputReader.getInputListofTalk("/talk-input.txt");

        assertEquals(785, inputReader.getTotalInputTalktimeinMins());
    }

    @AfterEach
    void tearDown() {
    }

}