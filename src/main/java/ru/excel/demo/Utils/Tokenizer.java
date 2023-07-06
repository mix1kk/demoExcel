package ru.excel.demo.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Tokenizer {
    public static List<String> getTokens(String expression) throws Exception {
        String[] stringArray = expression.split("");
        int counter = 0;
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i].equals("("))
                counter++;
            if (stringArray[i].equals(")"))
                counter--;
        }
        if (counter != 0) throw new RuntimeException("Brackets not even");
        List<String> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(expression, " +-*/()", true);
        while (tokenizer.hasMoreTokens()) {
            tokens.add(tokenizer.nextToken());
        }
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).equals("-")) {
                if (i == 0 || (i > 0 && i < tokens.size() - 1 && (tokens.get(i - 1).equals("(") || tokens.get(i - 1).equals("+") || tokens.get(i - 1).equals("-") ||
                        tokens.get(i - 1).equals("*") || tokens.get(i - 1).equals("/")))) {
                    tokens.set(i + 1, "-" + tokens.get(i + 1));
                    tokens.remove(i);
                }
            }
        }
        return tokens;
    }
}
