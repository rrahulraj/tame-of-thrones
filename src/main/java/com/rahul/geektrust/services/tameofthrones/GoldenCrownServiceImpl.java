package com.rahul.geektrust.services.tameofthrones;

import com.rahul.geektrust.constants.GoldenCrownConstants;
import com.rahul.geektrust.exceptions.InvalidInputException;
import com.rahul.geektrust.models.MessageDraft;
import com.rahul.geektrust.services.outputs.ConsoleOutputServiceImpl;
import com.rahul.geektrust.services.inputreaders.InputReaderService;
import com.rahul.geektrust.services.outputs.OutputService;
import com.rahul.geektrust.services.messengers.RoyalMessengerService;
import com.rahul.geektrust.services.inputreaders.TxtInputReaderServiceImpl;
import com.rahul.geektrust.services.messengers.RoyalMessengerServiceImpl;
import utils.OutputMessages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GoldenCrownServiceImpl implements TameOfThronesService {

    public void playTameOfThrones(String inputFilePath) throws InvalidInputException {
        Set<MessageDraft> messageDrafts = getMessageDraftsFromInputFile(inputFilePath);
        List<String> allyNames = sendMessageDrafts(messageDrafts);
        outputResultToConsole(allyNames);
    }

    private Set<MessageDraft> getMessageDraftsFromInputFile(String arg) throws InvalidInputException {
        InputReaderService inputReader = new TxtInputReaderServiceImpl();
        return inputReader.readMessageDrafts(arg);
    }

    private List<String> sendMessageDrafts(Set<MessageDraft> messageDrafts) {
        RoyalMessengerService royalMessenger = new RoyalMessengerServiceImpl();
        List<String> allyNames = new ArrayList<>();
        messageDrafts.forEach(messageDraft -> {
            boolean isWonOver = royalMessenger.send(messageDraft.getKingdomName(), messageDraft.getSecretMessage());
            if(isWonOver)  {
                allyNames.add(messageDraft.getKingdomName());
            }
        });
        return allyNames;
    }

    private void outputResultToConsole(List<String> allyNames) {
        OutputService outputService = new ConsoleOutputServiceImpl();

        if (allyNames.size() >= GoldenCrownConstants.MIN_NUM_OF_ALLIES_TO_BE_RULER)  {
            System.out.print(GoldenCrownConstants.PROSPECTIVE_RULER_KINGDOM_NAME + " ");
            outputService.display(allyNames);
        }
        else    {
            System.out.print(OutputMessages.NOT_ENOUGH_ALLIES);
        }
    }

}
