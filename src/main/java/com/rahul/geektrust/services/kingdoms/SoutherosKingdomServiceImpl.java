package com.rahul.geektrust.services.kingdoms;

import com.rahul.geektrust.enums.SoutherosKingdoms;
import com.rahul.geektrust.services.ciphers.CipherService;
import com.rahul.geektrust.services.ciphers.SeasarCipherServiceImpl;
import utils.StringUtils;

public class SoutherosKingdomServiceImpl implements KingdomService {

    private String emblem;

    public SoutherosKingdomServiceImpl(String kingdomName) {
        this.emblem = SoutherosKingdoms.valueOf(kingdomName).getEmblem();
    }

    @Override
    public boolean evaluateMessage(String message) {
        String decryptedMessage = decryptMessage(message, emblem);
        return StringUtils.baseStringHasAllCharsOfPattern(decryptedMessage, emblem);
    }

    private String decryptMessage(String message, String emblem) {
        CipherService cipherService = new SeasarCipherServiceImpl();
        return cipherService.decrypt(message, emblem.length());
    }

}
