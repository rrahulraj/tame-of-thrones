package com.rahul.geektrust.service.impl.utils;

import org.junit.jupiter.api.Test;
import com.rahul.geektrust.utils.StringUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringUtilsTest {

    @Test
    void StringHasAllCharsOfPatternReturnsTrueWhenBaseStringHasAllCharsOfPattern()   {
        String baseString = "abcdefgh";
        String pattern = "dahg";
        assertTrue(StringUtils.baseStringHasAllCharsOfPattern(baseString, pattern));
    }

    @Test
    void StringHasAllCharsOfPatternReturnsFalseWhenBaseStringHasAllCharsOfPattern()   {
        String baseString = "abcdefgh";
        String pattern = "dahgx";
        assertFalse(StringUtils.baseStringHasAllCharsOfPattern(baseString, pattern));
    }

}
