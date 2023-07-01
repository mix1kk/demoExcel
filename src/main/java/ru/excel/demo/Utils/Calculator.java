package ru.excel.demo.Utils;
import ru.excel.demo.Models.Line;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    public static List<String> tokenizeAndMakePolishRevert (String expression,List<Line> listLines){
        Pattern digitsPattern = Pattern.compile(("(\\d*\\.)?\\d+"));
        Pattern addressPattern = Pattern.compile(("[ABCD][1-4]"));
        //итоговая строка в обратной польской записи
        List<String> result = new ArrayList<>();
        //стек операций
        Deque<String> operationStack = new LinkedList<>();
        StringTokenizer tokenizer = new StringTokenizer(expression.replace("=","")," +-*/()",true);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            Matcher matcher1 = digitsPattern.matcher(token);
            Matcher matcher2 = addressPattern.matcher(token);
            //если число, то кладем в результат
            if(matcher1.matches())
                result.add(token);
            //если ссылка на ячейку, получаем число из ячейки и кладем в результат
            if(matcher2.matches()){
                String [] splittedToken = token.split("");
                switch (splittedToken[0]) {
                    case "A": token = listLines.get(Integer.parseInt(splittedToken[1])-1).getA();
                    break;
                    case "B": token = listLines.get(Integer.parseInt(splittedToken[1])-1).getB();
                    break;
                    case "C": token = listLines.get(Integer.parseInt(splittedToken[1])-1).getC();
                    break;
                    case "D": token = listLines.get(Integer.parseInt(splittedToken[1])-1).getD();
                    break;
                }

                result.add(token);
            }
            //если открывающая скобка, кладем в стек
            if (token.equals("("))
                operationStack.push(token);
            //если закрывающая скобка, все операции из стека добавляем в результат
            if (token.equals(")")){
                while (!operationStack.isEmpty()&&!operationStack.peek().equals("(")) {
                    result.add(operationStack.pop());
                }
            //удаляем саму открывающую скобку
             operationStack.pop();
            }
            // если плюс или минус, то в соответствии с приоритетом извлекаем из стека умножение и деление,а в стек помещаем сложение или вычитание
            if (token.equals("+")||token.equals("-")){
                while (!operationStack.isEmpty()&&(operationStack.peek().equals("*")||operationStack.peek().equals("/")||
                        operationStack.peek().equals("+")||operationStack.peek().equals("-"))) {
                    result.add(operationStack.pop());
                }
            operationStack.push(token);
            }
            if (token.equals("*")||token.equals("/")){
                while (!operationStack.isEmpty()&&(operationStack.peek().equals("*")||operationStack.peek().equals("/"))) {
                    result.add(operationStack.pop());
                }
                operationStack.push(token);
            }

        }
        //добавляем все, что осталось в стеке
        while ((!operationStack.isEmpty())){
            result.add(operationStack.pop());
        }
        for (int i=0; i< result.size();i++){
           System.out.println(result.get(i));}

        return result;
    }
//    public static calculate(){
//
//    }
}
