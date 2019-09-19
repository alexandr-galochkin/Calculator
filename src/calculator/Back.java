package calculator;

import java.util.LinkedList;

public class Back {


    private static boolean isOperation(char c) {
        return (c == '+') || (c == '-') || (c == '/') || (c == '%') || (c == '*') || (c == '^');
    }

    private static int opearatorsPriority(char operand) {
        switch (operand) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    private static void operator(LinkedList<Double> operand, char op) throws Exception{
        Double f = operand.removeLast();
        Double s = operand.removeLast();
        switch (op) {
            case '+':
                operand.add(s + f);
                break;
            case '-':
                operand.add(s- f);
                break;
            case '*':
                operand.add(s * f);
                break;
            case '/':
                operand.add(s / f);
                break;
            case '%':
                operand.add(s % f);
                break;
            case '^':
                operand.add(Math.pow(s, f));
                break;
        }
    }


    public static Double evaluate(String expression) throws Exception{
        LinkedList<Double> listOfOperands = new LinkedList<>();
        LinkedList<Character> listOfOperator = new LinkedList<>();
        expression = expression.replaceAll("\\s+", "");

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(') {
                listOfOperator.add('(');
            } else if (c == ')') {
                while (listOfOperator.getLast() != '(') {
                    operator(listOfOperands, listOfOperator.removeLast());
                }
                listOfOperator.removeLast();
            } else if (isOperation(c)) {
                while ((!listOfOperator.isEmpty()) && (opearatorsPriority(listOfOperator.getLast()) >= opearatorsPriority(c))) {
                    operator(listOfOperands, listOfOperator.removeLast());
                }
                listOfOperator.add(c);
            } else {
                StringBuilder operand = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || (expression.charAt(i) == '.')
                        || (expression.charAt(i) == ','))) {
                    operand.append(expression.charAt(i));
                    i++;
                }
                i--;
                listOfOperands.add(Double.parseDouble(operand.toString()));
            }
        }
        while (!listOfOperator.isEmpty()) {
            operator(listOfOperands, listOfOperator.removeLast());
        }
        return listOfOperands.get(0);
    }
}