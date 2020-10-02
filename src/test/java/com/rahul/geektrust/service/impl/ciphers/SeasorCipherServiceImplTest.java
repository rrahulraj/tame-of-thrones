package com.rahul.geektrust.service.impl.ciphers;

import com.rahul.geektrust.services.ciphers.SeasarCipherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SeasorCipherServiceImplTest {

    @Mock
    SeasarCipherServiceImpl seasorCipherService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void standardSeasorDecryption() {
        String encryptedString = "ROZO";
        seasorCipherService = new SeasarCipherServiceImpl();
        String decryptedString = seasorCipherService.decrypt(encryptedString, 3);
        assertEquals("OLWL", decryptedString);
    }

    @Test
    void noDecryptionForSpaces() {
        String encryptedString = "RO Z O";
        seasorCipherService = new SeasarCipherServiceImpl();
        String decryptedString = seasorCipherService.decrypt(encryptedString, 3);
        assertEquals("OL W L", decryptedString);
    }

}
