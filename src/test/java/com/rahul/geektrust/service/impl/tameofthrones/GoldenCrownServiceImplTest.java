package com.rahul.geektrust.service.impl.tameofthrones;

import com.rahul.geektrust.constants.GoldenCrownConstants;
import com.rahul.geektrust.exceptions.InvalidInputException;
import com.rahul.geektrust.services.tameofthrones.GoldenCrownServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GoldenCrownServiceImplTest {

    static final String lessThan3Allies = "src/test/resources/input_proper.txt";

    static final String greaterOrEqualTo3Allies = "src/test/resources/input_duplicate_input_lines.txt";

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private final PrintStream originalOut = System.out;

    private GoldenCrownServiceImpl tameOfThronesService  = new GoldenCrownServiceImpl();;

    @BeforeEach
    public void setupStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void playTameOfThronesExpectedOutputOnNotEnoughAllies() throws InvalidInputException {
        tameOfThronesService.playTameOfThrones(lessThan3Allies);
        assertEquals("NONE", outContent.toString());
    }

    @Test
    void playTameOfThronesExpectedOutputOnEnoughAllies() throws InvalidInputException {
        tameOfThronesService.playTameOfThrones(greaterOrEqualTo3Allies);
        String expectedOutput = GoldenCrownConstants.PROSPECTIVE_RULER_KINGDOM_NAME + " AIR LAND ICE";
        assertEquals(expectedOutput, outContent.toString());
    }

}
