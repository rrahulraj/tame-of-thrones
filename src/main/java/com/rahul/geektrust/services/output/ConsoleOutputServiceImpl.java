package com.rahul.geektrust.services.output;

import java.util.Collections;
import java.util.List;

public class ConsoleOutputServiceImpl implements OutputService {

    @Override
    public void display(List<String> allies) {
        Collections.sort(allies);

        StringBuilder outputBuilder = new StringBuilder();
        for(String name : allies)    {
            outputBuilder.append(name).append(" ");
        }

        String output = outputBuilder
                .toString()
                .trim();
        System.out.print(output);
    }

}
