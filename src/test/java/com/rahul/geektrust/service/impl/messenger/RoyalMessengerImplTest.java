package com.rahul.geektrust.service.impl.messenger;

import com.rahul.geektrust.enums.SoutherosKingdoms;
import com.rahul.geektrust.services.kingdom.KingdomService;
import com.rahul.geektrust.services.messenger.RoyalMessengerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class RoyalMessengerImplTest {

    @Mock
    KingdomService kingdomService;

    @InjectMocks
    RoyalMessengerServiceImpl royalMessengerService = new RoyalMessengerServiceImpl();;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void sendReturnsTrueIfKingdomServiceReturnsTrue()    {
        when(kingdomService.evaluateMessage(anyString())).thenReturn(true);
        boolean isWonOver = royalMessengerService.send(SoutherosKingdoms.AIR.name(), "ROZO");
        assertTrue(isWonOver);
    }

    @Test
    void sendReturnsFalseIfKingdomServiceReturnsFalse()    {
        when(kingdomService.evaluateMessage(anyString())).thenReturn(false);
        boolean isWonOver = royalMessengerService.send(SoutherosKingdoms.AIR.name(), "ROZ");
        assertTrue(isWonOver);
    }

}
