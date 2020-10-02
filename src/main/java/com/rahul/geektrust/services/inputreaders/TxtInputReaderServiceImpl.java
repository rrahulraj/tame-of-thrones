package com.rahul.geektrust.services.inputreaders;

import com.rahul.geektrust.enums.SoutherosKingdoms;
import com.rahul.geektrust.exceptions.ErrorMessages;
import com.rahul.geektrust.exceptions.InvalidInputException;
import com.rahul.geektrust.models.MessageDraft;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class TxtInputReaderServiceImpl implements InputReaderService {

    public Set<MessageDraft> readMessageDrafts(String filePath) throws InvalidInputException {
        FileInputStream fileInputStream = createFileInputStream(filePath);
        List<MessageDraft> messageDrafts = new ArrayList<>();

        Scanner scanner=new Scanner(fileInputStream);
        while(scanner.hasNextLine()) {
            String readLine = scanner.nextLine();
            String[] splitStr = readLine.split("\\s+");
            validateLine(splitStr);
            MessageDraft messageDraft = buildMessageDraft(splitStr);
            messageDrafts.add(messageDraft);
        }
        scanner.close();

        return createUniqueMessageDraftsSet(messageDrafts);
    }

    private Set<MessageDraft> createUniqueMessageDraftsSet(List<MessageDraft> messageDrafts)   {
        return messageDrafts.stream()
                .collect(Collectors.toCollection(() ->
                        new TreeSet<>(Comparator.comparing(MessageDraft::getKingdomName))));
    }

    private MessageDraft buildMessageDraft(String[] splitStr) {
        String kingdomName = splitStr[0];
        StringBuilder message = new StringBuilder();
        for(int i=1; i<splitStr.length; i++)    {
            message.append(splitStr[i]);
        }
        return MessageDraft.builder()
                .kingdomName(kingdomName)
                .secretMessage(message.toString())
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
