package com.rahul.geektrust.service.impl.cipher;

import com.rahul.geektrust.services.cipher.SeasarCipherServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SeasorCipherServiceImplTest {

    SeasarCipherServiceImpl seasorCipherService = new SeasarCipherServiceImpl();

    @Test
    void decryptRotatesEachCharacterByGivenMessageWheelRotations() {
        String encryptedString = "ROZO";
        String decryptedString = seasorCipherService.decrypt(encryptedString, 3);
        assertEquals("OLWL", decryptedString);
    }

    @Test
    void decryptDoesNotDecryptForSpaces() {
        String encryptedString = "RO Z O";
        String decryptedString = seasorCipherService.decrypt(encryptedString, 3);
        assertEquals("OL W L", decryptedString);
    }

}
