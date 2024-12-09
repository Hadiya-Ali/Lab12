package twitter;

public class MathParser {

    private static int i = 0;  

    public static double evaluate(String e) throws Exception {
        i = 0; 
        //removing the spaces
        return parseExp(e.replaceAll("\\s+", ""));  
    }

   

    private static double Term(String exp) throws Exception {
        double r = Factor(exp);  
        while (i < exp.length()) {
            char op = NextChar(exp);
            //checking the operator and solving it

            if (op == '*' || op == '/') {
                i++;  
                double f = Factor(exp);  
                r = applyOperator(r, op, f);
            } else {
                break;
            }
        }
        return r;
    }

    private static double parseExp(String e) throws Exception {
    	//checking the inputs
        double re = Term(e);  
        while (i< e.length()) {
            char op = NextChar(e);
//seeing the operator being used and performing accordingly
            if (op == '+' || op == '-') {
                i++;  
                double term = Term(e);  
                re = applyOperator(re, op, term);
            } else {
                break;
            }
        }
        return re;
    }

    private static double Number(String exp) throws Exception {
        int startI = i;
//checks the number
        while (i < exp.length() && (Character.isDigit(NextChar(exp)) || NextChar(exp) == '.')) {
            i++;
        }
//once parsed, it converts to double
        String numStr = exp.substring(startI, i);
        try {
            return Double.parseDouble(numStr);
        } catch (NumberFormatException e) {
            throw new Exception("Invalid number format: " + numStr);
        }
    }

    private static double applyOperator(double l, char op, double r) {
    	//checking the operator and applying accordingly
        if (op == '+') {
            return l + r;
        } else if (op == '-') {
            return l - r;
        } else if (op == '*') {
            return l * r;
        } else if (op == '/') {
            return l / r;
        } else {
            throw new IllegalArgumentException("Operator not found: " + op);
        }
    }

    private static char NextChar(String exp) {
        if (i < exp.length()) {
        	// returning the character if i< length of expression else \0
            return exp.charAt(i);
        }
        return '\0'; 
    }
    private static double Factor(String exp) throws Exception {
        if (i >= exp.length()) {
            throw new Exception("Unexpected end ");
        }

        char curChar = NextChar(exp);

        if (curChar == '(') {
        	//if its an opening paraenthesis then it goes to parseExp
            i++; 
            double result = parseExp(exp);  
            if (NextChar(exp) == ')') {
            	//if its close then it doesnt go anywhere
                i++;  
            } else {
                throw new Exception("Mismatched ");
            }
            return result;
        } else if (Character.isDigit(curChar) || curChar == '.') {
            return Number(exp);
        } else {
            throw new Exception("character: " + curChar);
        }
    }
}
