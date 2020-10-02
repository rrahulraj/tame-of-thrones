package com.rahul.geektrust.services.cipher;

public interface CipherService {

    String decrypt(String encryptedString, int messageWheelRotations);

}
