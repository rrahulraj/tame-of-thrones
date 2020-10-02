package com.rahul.geektrust.services.ciphers;

public interface CipherService {

    String decrypt(String encryptedString, int messageWheelRotations);

}
