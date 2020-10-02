package com.rahul.geektrust.service.impl.inputreaders;

import com.rahul.geektrust.exceptions.ErrorMessages;
import com.rahul.geektrust.exceptions.InvalidInputException;
import com.rahul.geektrust.models.MessageDraft;
import com.rahul.geektrust.services.inputreaders.TxtInputReaderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class TxtInputReaderServiceImplTest {

    static final String invalidKingdomNameInputPath = "src/test/resources/invalid_kingdom_name_input.txt";

    static final String noMessageOnAInputLinePath = "src/test/resources/invalid_input.txt";

    static final String properInputPath = "src/test/resources/input_proper.txt";

    static final String duplicateInputLinesInputPath = "src/test/resources/input_duplicate_input_lines.txt";

    static final String anyNumberOfWhiteSpacesInMessageInputPath = "src/test/resources/white_spaces_in_message_input.txt";

    static final String emptyInputPath = "/Users/rahulraj/Desktop/Int/crio/tame-of-thrones/src/test/resources/input_empty.txt";

    TxtInputReaderServiceImpl inputReaderService;

    @BeforeEach
    void init() {
        inputReaderService = new TxtInputReaderServiceImpl();
    }

    @Test
    void throwsInvalidInputExceptionOnInaccessibleFile()    {
        Exception exception = assertThrows(InvalidInputException.class, () -> inputReaderService.readMessageDrafts(""));

        String expectedMessage = ErrorMessages.ERROR_ACCESSING_INPUT_FILE;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void throwsInvalidInputExceptionOnInvalidKingdomNameForSomeInputLine()   {
        Exception exception = assertThrows(InvalidInputException.class, () -> inputReaderService.readMessageDrafts(invalidKingdomNameInputPath));

        String expectedMessage = ErrorMessages.INVALID_KINGDOM_NAME_IN_INPUT_FILE;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void throwsInvalidInputExceptionOnNoMessageForSomeInputLine()   {
        Exception exception = assertThrows(InvalidInputException.class, () -> inputReaderService.readMessageDrafts(noMessageOnAInputLinePath));

        String expectedMessage = ErrorMessages.INVALID_STRING_IN_INPUT_FILE;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void standardInput() throws InvalidInputException    {
        Set<MessageDraft> messageDrafts = inputReaderService.readMessageDrafts(properInputPath);
        assertEquals(2, messageDrafts.size());
    }

    @Test
    void duplicateInputLinesTreatedAsOne() throws InvalidInputException    {
        Set<MessageDraft> messageDrafts = inputReaderService.readMessageDrafts(duplicateInputLinesInputPath);
        assertEquals(3, messageDrafts.size());
    }

    @Test
    void anyNumberOfWhiteSpacesTreatedAsSingleMessage() throws InvalidInputException    {
        Set<MessageDraft> messageDrafts = inputReaderService.readMessageDrafts(anyNumberOfWhiteSpacesInMessageInputPath);
        assertEquals(3, messageDrafts.size());
    }

    @Test
    void emptyInputFileGivesEmptyArrayOfAllies() throws InvalidInputException   {
        Set<MessageDraft> messageDrafts = inputReaderService.readMessageDrafts(emptyInputPath);
        assertEquals(0, messageDrafts.size());
    }

}
