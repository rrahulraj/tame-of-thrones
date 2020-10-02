package com.rahul.geektrust.services.outputs;

import java.util.List;

public class ConsoleOutputServiceImpl implements OutputService {

    @Override
    public void display(List<String> allies) {
        StringBuilder outputBuilder = new StringBuilder();
        for(String name : allies)    {
            outputBuilder.append(name).append(" ");
        }

        String output = outputBuilder.toString()
                .trim();
        System.out.print(output);
    }

}
