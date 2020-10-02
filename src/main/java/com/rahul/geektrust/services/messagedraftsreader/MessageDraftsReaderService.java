package com.rahul.geektrust.services.messagedraftsreader;

import com.rahul.geektrust.exceptions.InvalidInputException;
import com.rahul.geektrust.models.MessageDraft;

import java.util.Set;

public interface MessageDraftsReaderService {

    Set<MessageDraft> read(String filePath) throws InvalidInputException;

}
