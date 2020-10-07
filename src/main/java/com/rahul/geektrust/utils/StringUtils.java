package com.rahul.geektrust.utils;

import java.util.HashMap;
import java.util.Map;

public final class StringUtils {
    
    private StringUtils()   {
        //private constructor to prevent instantiation of utils class
    }

    public static Map<Character, Integer> generateFrequencyMapOfChars(String inputString) {
        Map<Character, Integer> frequencies = new HashMap<>();
        for (int i = 0; i < inputString.length(); i++) {
            char currentCharacter = inputString.charAt(i);
            frequencies.merge(currentCharacter, 1, Integer::sum);
        }

        return frequencies;
    }

    public static boolean baseStringHasAllCharsOfPattern(String baseString, String pattern) {
        Map<Character, Integer> patternCharsFrequencyMap = generateFrequencyMapOfChars(pattern);

        int patternCharsMatched = 0;
        for(int i=0; i<baseString.length(); i++)   {
            char currentChar = baseString.charAt(i);

            if(patternCharsFrequencyMap.containsKey(currentChar)
                    && patternCharsFrequencyMap.get(currentChar) > 0)    {
                patternCharsMatched++;
                int newEmblemCharFrequency = patternCharsFrequencyMap.get(currentChar) - 1;
                patternCharsFrequencyMap.put(currentChar, newEmblemCharFrequency);
            }

            if(patternCharsMatched == pattern.length())   {
                return true;
            }
        }

        return false;
    }

}
