package com.rahul.geektrust.services.messagedraftsreader;

import com.rahul.geektrust.exceptions.InvalidInputException;
import com.rahul.geektrust.models.MessageDraft;

import java.util.List;

public interface MessageDraftsReaderService {

    List<MessageDraft> read(String filePath) throws InvalidInputException;

}
