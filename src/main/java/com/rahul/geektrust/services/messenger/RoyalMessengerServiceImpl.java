package com.rahul.geektrust.services.messenger;

import com.rahul.geektrust.services.kingdom.KingdomService;
import com.rahul.geektrust.services.kingdom.SoutherosKingdomServiceImpl;

public class RoyalMessengerServiceImpl implements RoyalMessengerService {

    @Override
    public boolean send(String receiverKingdomName, String message)   {
        KingdomService kingdomService = new SoutherosKingdomServiceImpl(receiverKingdomName);
        return kingdomService.evaluateMessage(message);
    }
}
