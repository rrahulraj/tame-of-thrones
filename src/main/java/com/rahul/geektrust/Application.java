package com.rahul.geektrust;

import com.rahul.geektrust.exceptions.InvalidInputException;
import com.rahul.geektrust.services.tameofthrones.TameOfThronesService;
import com.rahul.geektrust.services.tameofthrones.GoldenCrownServiceImpl;

public class Application {

    public static void main(String[] args) throws InvalidInputException {
        TameOfThronesService tameOfThronesService = new GoldenCrownServiceImpl();
        tameOfThronesService.playTameOfThrones(args[0]);
    }

}

