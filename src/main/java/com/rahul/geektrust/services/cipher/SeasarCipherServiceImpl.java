package com.rahul.geektrust.services.cipher;

import com.rahul.geektrust.constants.SeasarCipherConstants;

public class SeasarCipherServiceImpl implements CipherService {

    @Override
    public String decrypt(String encryptedString, int messageWheelRotations) {
        StringBuilder decryptedString = new StringBuilder();
        for(int i=0; i<encryptedString.length(); i++)   {
            char decryptedChar = rotateCharClockwise(encryptedString.charAt(i), messageWheelRotations);
            decryptedString.append(decryptedChar);
        }

        return decryptedString.toString();
    }

    private char rotateCharClockwise(char encryptedChar, int messageWheelRotations) {
        if(encryptedChar == ' ')    {
            return encryptedChar;
        }

        int encryptedCharNumber = encryptedChar - SeasarCipherConstants.MESSAGE_WHEEL_START_CHAR;
        int decryptedCharNumber = encryptedCharNumber - messageWheelRotations;
        if(decryptedCharNumber < 0)    {
            decryptedCharNumber += SeasarCipherConstants.MESSAGE_WHEEL_SIZE;
        }

        return (char) (SeasarCipherConstants.MESSAGE_WHEEL_START_CHAR + decryptedCharNumber);
    }
}
