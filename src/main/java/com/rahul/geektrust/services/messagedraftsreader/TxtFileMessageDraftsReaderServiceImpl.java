package com.rahul.geektrust.services.messagedraftsreader;

import com.rahul.geektrust.enums.SoutherosKingdoms;
import com.rahul.geektrust.exceptions.ErrorMessages;
import com.rahul.geektrust.exceptions.InvalidInputException;
import com.rahul.geektrust.models.MessageDraft;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class TxtFileMessageDraftsReaderServiceImpl implements MessageDraftsReaderService {

    public List<MessageDraft> read(String filePath) throws InvalidInputException {
        FileInputStream fileInputStream = createFileInputStream(filePath);
        List<MessageDraft> messageDrafts = new ArrayList<>();
        Set<String> receiverKingdomNames = new HashSet<>();

        Scanner scanner=new Scanner(fileInputStream);
        while(scanner.hasNextLine()) {
            String readLine = scanner.nextLine();
            String[] splitStr = readLine.split("\\s+");
            validateLine(splitStr);
            MessageDraft messageDraft = buildMessageDraft(splitStr);
            if(!receiverKingdomNames.contains(messageDraft.getKingdomName()))    {
                messageDrafts.add(messageDraft);
                receiverKingdomNames.add(messageDraft.getKingdomName());
            }
        }
        scanner.close();

        return messageDrafts;
    }

    private MessageDraft buildMessageDraft(String[] splitStr) {
        String kingdomName = splitStr[0];
        StringBuilder message = new StringBuilder();
        for(int i=1; i<splitStr.length; i++)    {
            message.append(splitStr[i]).append(" ");
        }
        String secretMessage = message
                .toString()
                .trim();
        return MessageDraft.builder()
                .kingdomName(kingdomName)
                .secretMessage(secretMessage)
                .build();
    }

    void validateLine(String[] splitStr) throws InvalidInputException {
        if(splitStr.length < 2) {
            throw new InvalidInputException(ErrorMessages.INVALID_STRING_IN_INPUT_FILE);
        }
        String kingdomName = splitStr[0];
        List<String> kingdomNames = Stream.of(SoutherosKingdoms.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        if(!kingdomNames.contains(kingdomName))    {
            throw new InvalidInputException(ErrorMessages.INVALID_KINGDOM_NAME_IN_INPUT_FILE);
        }
    }

    FileInputStream createFileInputStream(String filePath) throws InvalidInputException {
        try {
            return new FileInputStream(filePath);
        }
        catch (FileNotFoundException e) {
            throw new InvalidInputException(ErrorMessages.ERROR_ACCESSING_INPUT_FILE);
        }
    }

}
