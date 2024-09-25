package com.ramzi.workshop.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestControllerTest {

    @Test
    void testFn_shouldReturnMessage() {
        TestController testController = new TestController();

        String result = testController.testFn();

        assertEquals("You are secured", result);
    }
}
