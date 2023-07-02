package ru.excel.demo.Utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {

    @Test
    void testGetTokens() {
        String expression = "-1-2+(-A2)";
        List<String> expected = Arrays.asList("-1","-","2","+","(","-A2",")");
        assertEquals(expected,Tokenizer.getTokens(expression));
    }
}