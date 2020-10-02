package com.rahul.geektrust.service.impl.output;

import com.rahul.geektrust.services.output.ConsoleOutputServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleOutputServiceImplTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private final PrintStream originalOut = System.out;

    private ConsoleOutputServiceImpl consoleOutputService = new ConsoleOutputServiceImpl();

    @BeforeEach
    public void setupStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void displayDisplaysStandardDefinedConsoleOutputFormat()    {
        List<String> displayStrings  = Arrays.asList("abc", "bcd");
        consoleOutputService.display(displayStrings);
        assertEquals("abc bcd", outContent.toString());
    }

    @Test
    void displayDisplaysSortedStringsConsoleOutput()  {
        List<String> displayStrings  = Arrays.asList("bcd", "abc");
        consoleOutputService.display(displayStrings);
        assertEquals("abc bcd", outContent.toString());
    }

    @Test
    void displayDisplaysEmptyOutputOnEmptyListAsArgument()    {
        consoleOutputService.display(Collections.emptyList());
        assertEquals("", outContent.toString());

    }

}
