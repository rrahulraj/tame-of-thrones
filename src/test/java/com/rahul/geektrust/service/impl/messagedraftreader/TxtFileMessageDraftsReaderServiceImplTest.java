package com.rahul.geektrust.service.impl.messagedraftreader;

import com.rahul.geektrust.exceptions.ErrorMessages;
import com.rahul.geektrust.exceptions.InvalidInputException;
import com.rahul.geektrust.models.MessageDraft;
import com.rahul.geektrust.services.messagedraftsreader.TxtFileMessageDraftsReaderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class TxtFileMessageDraftsReaderServiceImplTest {

    static final String invalidKingdomNameInputPath = "src/test/resources/invalid_kingdom_name_input.txt";

    static final String noMessageOnAInputLinePath = "src/test/resources/invalid_input.txt";

    static final String properInputPath = "src/test/resources/input_proper.txt";

    static final String duplicateInputLinesInputPath = "src/test/resources/input_duplicate_input_lines.txt";

    static final String whiteSpacesInMessageInputPath = "src/test/resources/white_spaces_in_message_input.txt";

    static final String emptyInputPath = "src/test/resources/input_empty.txt";

    TxtFileMessageDraftsReaderServiceImpl messageDraftsReaderService = new TxtFileMessageDraftsReaderServiceImpl();;

    @Test
    void readThrowsInvalidInputExceptionOnInaccessibleFile()    {
        String expectedMessage = ErrorMessages.ERROR_ACCESSING_INPUT_FILE;
        Exception exception = assertThrows(InvalidInputException.class, () -> messageDraftsReaderService.read(""));
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void readThrowsInvalidInputExceptionOnInvalidKingdomNameForSomeInputLine()   {
        String expectedMessage = ErrorMessages.INVALID_KINGDOM_NAME_IN_INPUT_FILE;
        Exception exception = assertThrows(InvalidInputException.class, () -> messageDraftsReaderService.read(invalidKingdomNameInputPath));
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void readThrowsInvalidInputExceptionOnNoMessageForSomeInputLine()   {
        String expectedMessage = ErrorMessages.INVALID_STRING_IN_INPUT_FILE;
        Exception exception = assertThrows(InvalidInputException.class, () -> messageDraftsReaderService.read(noMessageOnAInputLinePath));
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void readReturnsMessageDraftsForValidInputInSameOrderAsInput() throws InvalidInputException    {
        MessageDraft messageDraft1 = MessageDraft.builder()
                .kingdomName("AIR")
                .secretMessage("ROZO")
                .build();
        MessageDraft messageDraft2 = MessageDraft.builder()
                .kingdomName("LAND")
                .secretMessage("FAIJWJSOOFAMAU")
                .build();
        List<MessageDraft> expectedMessageDrafts = Arrays.asList(messageDraft1, messageDraft2);
        List<MessageDraft> actualMessageDrafts = messageDraftsReaderService.read(properInputPath);

        for(int i = 0; i < 2; i++)  {
            assertEquals(expectedMessageDrafts.get(i).getKingdomName(), actualMessageDrafts.get(i).getKingdomName());
            assertEquals(expectedMessageDrafts.get(i).getSecretMessage(), actualMessageDrafts.get(i).getSecretMessage());
        }
    }

    @Test
    void readReturnsDuplicateInputLinesAsOne() throws InvalidInputException    {
        List<MessageDraft> actualMessageDrafts = messageDraftsReaderService.read(duplicateInputLinesInputPath);
        int numberOfUniqueMessageDrafts = 3;
        assertEquals(numberOfUniqueMessageDrafts, actualMessageDrafts.size());
    }

    @Test
    void readReturnsMessageDraftsContainingMessagesWithSpaces() throws InvalidInputException    {
        List<MessageDraft> actualMessageDrafts = messageDraftsReaderService.read(whiteSpacesInMessageInputPath);
        MessageDraft firstMessageDraft = actualMessageDrafts.get(0);
        String actualKingdomName = firstMessageDraft.getKingdomName();
        String actualMessage = firstMessageDraft.getSecretMessage();
        assertEquals("AIR", actualKingdomName);
        assertEquals("ROZO HEL KD DK", actualMessage);
    }

    @Test
    void readReturnsEmptyArrayOfAlliesOnEmptyInputFile() throws InvalidInputException   {
        List<MessageDraft> actualMessageDrafts = messageDraftsReaderService.read(emptyInputPath);
        assertEquals(0, actualMessageDrafts.size());
    }

}
