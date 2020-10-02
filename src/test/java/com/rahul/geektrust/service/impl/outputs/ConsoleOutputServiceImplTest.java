package com.rahul.geektrust.service.impl.outputs;

import com.rahul.geektrust.services.outputs.ConsoleOutputServiceImpl;
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

    private ConsoleOutputServiceImpl consoleOutputService;

    @BeforeEach
    public void init() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void standardDefinedConsoleOutputFormat()    {
        consoleOutputService = new ConsoleOutputServiceImpl();
        List<String> displayStrings  = Arrays.asList("abc", "bcd");
        consoleOutputService.display(displayStrings);
        assertEquals("abc bcd", outContent.toString());
    }

    @Test
    void emptyOutputOnEmptyListAsArgument()    {
        consoleOutputService = new ConsoleOutputServiceImpl();
        consoleOutputService.display(Collections.emptyList());
        assertEquals("", outContent.toString());

    }

}
