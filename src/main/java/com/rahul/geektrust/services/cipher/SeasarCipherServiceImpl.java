package com.rahul.geektrust.services.cipher;

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

        int characterSetSize = 26;
        char messageWheelStart = 'A';

        int encryptedCharNumber = encryptedChar - messageWheelStart;
        int decryptedCharNumber = encryptedCharNumber - messageWheelRotations;
        if(decryptedCharNumber < 0)    {
            decryptedCharNumber += characterSetSize;
        }

        return (char) (messageWheelStart + decryptedCharNumber);
    }
}
