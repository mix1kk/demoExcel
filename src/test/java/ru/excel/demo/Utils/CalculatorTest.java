package ru.excel.demo.Utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.excel.demo.Models.Line;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @Test
    void testCalculate() {

        List<String> tokens = Arrays.asList("-1","-2","*");
        assertEquals(2.0,Calculator.calculate(tokens));

    }

    @Test
    void testMakePolishRevert() throws Exception {
        String expression = "(5-6)*7";
        List<String> expected = Arrays.asList("5", "6","-","7","*");
        List < Line > listLines = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Line line = new Line(i,"1","1","1","1","1","1","1","1");
            listLines.add(line);
        }
        Assertions.assertEquals(expected,Calculator.makePolishRevert(expression,listLines));

    }
}