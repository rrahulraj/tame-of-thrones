package com.rahul.geektrust.service.impl.kingdom;

import com.rahul.geektrust.enums.SoutherosKingdoms;
import com.rahul.geektrust.services.cipher.CipherService;
import com.rahul.geektrust.services.kingdom.KingdomService;
import com.rahul.geektrust.services.kingdom.SoutherosKingdomServiceImpl;
import com.rahul.geektrust.services.cipher.SeasarCipherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class SoutherosKingdomServiceImplTest {

    @Mock
    CipherService cipherService = new SeasarCipherServiceImpl();

    @InjectMocks
    KingdomService kingdomService = new SoutherosKingdomServiceImpl(SoutherosKingdoms.AIR.name());

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void evaluateMessageReturnsTrueForEmblemPresentInDecryptedMessage() {
        String message = "ROZO";
        when(cipherService.decrypt(anyString(), anyInt())).thenReturn("OLWL");
        boolean isWonOver = kingdomService.evaluateMessage(message);
        assertTrue(isWonOver);
    }

    @Test
    void evaluateMessageReturnsFalseForEmblemPresentInDecryptedMessage() {
        String message = "ROO";
        when(cipherService.decrypt(anyString(), anyInt())).thenReturn("OLL");
        boolean isWonOver = kingdomService.evaluateMessage(message);
        assertFalse(isWonOver);
    }


}
