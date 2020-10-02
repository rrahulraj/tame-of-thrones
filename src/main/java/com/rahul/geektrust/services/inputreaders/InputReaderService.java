package com.rahul.geektrust.services.inputreaders;

import com.rahul.geektrust.exceptions.InvalidInputException;
import com.rahul.geektrust.models.MessageDraft;

import java.util.Set;

public interface InputReaderService {

    Set<MessageDraft> readMessageDrafts(String filePath) throws InvalidInputException;

}
