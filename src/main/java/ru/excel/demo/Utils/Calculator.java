package ru.excel.demo.Utils;
import ru.excel.demo.Models.Line;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    static private final Pattern DIGITSPATTERN = Pattern.compile(("\\-?(\\d*\\.)?\\d+"));
    static private final Pattern ADDRESSPATTERN = Pattern.compile(("\\-?[ABCD][1-4]"));
    public static Line recalculateLine(Line line, List<Line> listLines) throws Exception {
        //проверка на обычное число

        //пересчитываем значение поля А
        String expression = line.getHiddenFieldA();
        Matcher matcher = DIGITSPATTERN.matcher(expression);
        if(!matcher.matches()) {
            String temp = Calculator.calculate(Calculator.makePolishRevert(expression.replace("=", ""), listLines)).toString();
            if (temp.matches("\\-?\\d+\\.[0]"))
            line.setFieldA(temp.replaceAll("\\.[0]",""));
            else line.setFieldA(temp);
        }
        //пересчитываем значение поля B
        expression = line.getHiddenFieldB();
        matcher = DIGITSPATTERN.matcher(expression);
        if(!matcher.matches()) {
            String temp = Calculator.calculate(Calculator.makePolishRevert(expression.replace("=", ""), listLines)).toString();
            if (temp.matches("\\-?\\d+\\.[0]"))
                line.setFieldB(temp.replaceAll("\\.[0]",""));
            else line.setFieldB(temp);
        }
        //пересчитываем значение поля C
        expression = line.getHiddenFieldC();
        matcher = DIGITSPATTERN.matcher(expression);
        if(!matcher.matches()) {
            String temp = Calculator.calculate(Calculator.makePolishRevert(expression.replace("=", ""), listLines)).toString();
            if (temp.matches("\\-?\\d+\\.[0]"))
                line.setFieldC(temp.replaceAll("\\.[0]",""));
            else line.setFieldC(temp);
        }
        //пересчитываем значение поля D
        expression = line.getHiddenFieldD();
        matcher = DIGITSPATTERN.matcher(expression);
        if(!matcher.matches()) {
            String temp = Calculator.calculate(Calculator.makePolishRevert(expression.replace("=", ""), listLines)).toString();
            if (temp.matches("\\-?\\d+\\.[0]"))
                line.setFieldD(temp.replaceAll("\\.[0]",""));
            else line.setFieldD(temp);
        }
        return line;
    }
    public static List<String> makePolishRevert(String expression, List<Line> listLines) throws Exception {



        //итоговая строка в обратной польской записи
        List<String> result = new ArrayList<>();

        //стек операций
        Deque<String> operationStack = new LinkedList<>();
        List<String> tokens = Tokenizer.getTokens(expression.replace("=",""));
        for (int i = 0; i<tokens.size();i++) {
            Matcher matcher1 = DIGITSPATTERN.matcher(tokens.get(i));
            Matcher matcher2 = ADDRESSPATTERN.matcher(tokens.get(i));
            //если число, то кладем в результат
            if(matcher1.matches())
                result.add(tokens.get(i));
            //если ссылка на ячейку, получаем число из ячейки и кладем в результат
            if(matcher2.matches()){
                 String [] splittedToken = tokens.get(i).replace("-","").split("");
                 String token = switch (splittedToken[0]) {
                    case "B" ->  listLines.get(Integer.parseInt(splittedToken[1])-1).getFieldB();
                    case "C" ->  listLines.get(Integer.parseInt(splittedToken[1])-1).getFieldC();
                    case "D" ->  listLines.get(Integer.parseInt(splittedToken[1])-1).getFieldD();
                     default -> listLines.get(Integer.parseInt(splittedToken[1])-1).getFieldA();
                };
                 //блок для исключения двойных минусов
                 if(tokens.get(i).startsWith("-")) {
                     if (token.startsWith("-"))
                         result.add(token.replace("-",""));
                     else result.add("-" + token);
                 }
                 else
                        result.add(token);
            }
            //если открывающая скобка, кладем в стек
            if (tokens.get(i).equals("("))
                operationStack.push(tokens.get(i));
            //если закрывающая скобка, все операции из стека добавляем в результат
            if (tokens.get(i).equals(")")){
                while (!operationStack.isEmpty()&&!operationStack.peek().equals("(")) {
                    result.add(operationStack.pop());
                }
            //удаляем саму открывающую скобку
             operationStack.pop();
            }
            // если плюс или минус, то в соответствии с приоритетом извлекаем из стека умножение и деление,а в стек помещаем сложение или вычитание
            if (tokens.get(i).equals("+")||tokens.get(i).equals("-")){
                while (!operationStack.isEmpty()&&(operationStack.peek().equals("*")||operationStack.peek().equals("/")||
                        operationStack.peek().equals("+")||operationStack.peek().equals("-"))) {
                    result.add(operationStack.pop());
                }
            operationStack.push(tokens.get(i));
            }
            if (tokens.get(i).equals("*")||tokens.get(i).equals("/")){
                while (!operationStack.isEmpty()&&(operationStack.peek().equals("*")||operationStack.peek().equals("/"))) {
                    result.add(operationStack.pop());
                }
                operationStack.push(tokens.get(i));
            }

        }
        //добавляем все, что осталось в стеке
        while ((!operationStack.isEmpty())){
            result.add(operationStack.pop());
        }
        return result;
    }
    public static Double calculate(List<String> tokens){

        //создаем стек для вычислений по обратной польской записи
        Deque<Double> valueStack = new LinkedList<>();
        for (String token : tokens){
            Matcher matcher = DIGITSPATTERN.matcher(token);
            if (matcher.matches())
                valueStack.push(Double.parseDouble(token));
            else {
                Double right = valueStack.pop();
                Double left = valueStack.pop();
                Double result = switch (token){
                    case "+" -> left + right;
                    case "-" -> left - right;
                    case "*" -> left * right;
                    case "/" -> left / right;
                    default -> 0.0;
                    };
                valueStack.push(result);
                }

        }
        return valueStack.pop();
    }
}
