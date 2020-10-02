package com.rahul.geektrust.services.messengers;

import com.rahul.geektrust.services.kingdoms.KingdomService;
import com.rahul.geektrust.services.kingdoms.SoutherosKingdomServiceImpl;

public class RoyalMessengerServiceImpl implements RoyalMessengerService {

    @Override
    public boolean send(String receiverKingdomName, String message)   {
        KingdomService kingdomService = new SoutherosKingdomServiceImpl(receiverKingdomName);
        return kingdomService.evaluateMessage(message);
    }
}
