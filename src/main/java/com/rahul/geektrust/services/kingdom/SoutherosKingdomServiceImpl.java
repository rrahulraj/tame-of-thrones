package com.rahul.geektrust.services.kingdom;

import com.rahul.geektrust.enums.SoutherosKingdoms;
import com.rahul.geektrust.services.cipher.CipherService;
import com.rahul.geektrust.services.cipher.SeasarCipherServiceImpl;
import com.rahul.geektrust.utils.StringUtils;

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
