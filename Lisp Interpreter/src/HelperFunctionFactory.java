import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;


class HelperFunctionFactory{

    public String InputStrExp;
    public static Map aList;
    public static Map dList;


    //Remove extra whitespaces from the input string
   public static String formatInputString(String InputStrExp) {
	
        InputStrExp = InputStrExp.toUpperCase();
		
        if(InputStrExp.contains(".")){
        	if(InputStrExp.charAt(InputStrExp.indexOf(".")+1)!=' ' && InputStrExp.charAt(InputStrExp.indexOf(".")-1)!=' '){
        		return null;
        	}
        }
        InputStrExp = InputStrExp.replace(" . ", ".");
        //InputStrExp = InputStrExp.replace(". ", ".");
        //InputStrExp = InputStrExp.replace(" .", ".");
        InputStrExp = InputStrExp.replace(" )", ")");
        InputStrExp = InputStrExp.replace("( ", "(");
        InputStrExp = InputStrExp.replace("()", "NIL");
        return InputStrExp.trim();
    }


    public static Vector getNextToken(String str) {
        Vector nextToken = new Vector();
        int parenthesisCount;
        String tokens = new String();
        tokens = str.trim();
        if (tokens.startsWith("(")) {
            parenthesisCount = 0;
            for (int i = 0; i < tokens.length(); i++) {
                if (tokens.charAt(i) == '(') {
                    parenthesisCount++;
                } else if (tokens.charAt(i) == ')') {
                    parenthesisCount--;
                }
                if (parenthesisCount == 0) {                                      
                    nextToken.add(0, tokens.substring(0, i + 1)); 
                    nextToken.add(1, tokens.substring(i + 1));
                    nextToken.add(2, "sexp"); 
                    return nextToken;
                }
            }
            if (parenthesisCount > 0) {
                nextToken.add(0, "");
                nextToken.add(1, tokens);
                nextToken.add(2, "error");
                return nextToken;
            }
        }
        if (tokens.startsWith(".")) {
            nextToken.add(0, ".");
            nextToken.add(1, tokens.substring(1));
            nextToken.add(2, "dot");
            return nextToken;
        }
        for (int j = 0; j < tokens.length(); j++) {
            if (tokens.charAt(j) == ' ' || tokens.charAt(j) == '.' || tokens.charAt(j) == '(') {
                nextToken.add(0, tokens.substring(0, j));
                nextToken.add(1, tokens.substring(j));
                nextToken.add(2, "atom");
                return nextToken;
            }
        }
        nextToken.add(0, tokens);
        nextToken.add(1, "");
        nextToken.add(2, "atom");
        return nextToken;
    }

    public static boolean checkForParanthesis(String InputStrExp) {
        int parenthesisCount = 0;
        for (int i = 0; i < InputStrExp.length(); i++) {
            if (InputStrExp.charAt(i) == '(') {
                parenthesisCount++;
            } else if (InputStrExp.charAt(i) == ')') {
                parenthesisCount--;
            }
            if (parenthesisCount < 0) {
                return false;
            }
        }
        if (parenthesisCount != 0) {
            return false;
        }
        return true;
    }

    public static String removeParenthesis(String InputStrExp) {
        InputStrExp = InputStrExp.trim();
        if (InputStrExp.startsWith("(") && InputStrExp.endsWith(")")) {
            InputStrExp = InputStrExp.substring(1, InputStrExp.length() - 1);
        }
        return InputStrExp;
    }

    
    public static String sExpressionToString(Object sExpression, boolean flag) {
        String result;
        if (!sExpression.getClass().getName().equalsIgnoreCase("java.util.Vector")) {
            if (flag) {
                return ")";
            } else {
                return sExpression.toString();
            }
        }
        if (((Vector) sExpression).size() == 1) {
            return ((Vector) sExpression).elementAt(0).toString();
        }
        Object left = new Object();
        Object right = new Object();
        left = ((Vector) sExpression).elementAt(0);
        right =((Vector) sExpression).elementAt(1);

        if (isList( sExpression)) {
            if (flag) {
                result = sExpressionToString(left, false) + " " + sExpressionToString(right, true);
            } else {
                result = "(" + sExpressionToString(left, false) + " " + sExpressionToString(right, true);
            }
        } else {
            result = "(" + sExpressionToString(left, false) + " . " + sExpressionToString(right, false) + ")";
        }
        result = result.replace(" )", ")");
        return result;
    }

    

    public static Vector InpStrToSExp(String exp) {
        String local = exp;
        Vector tokens = new Vector();
        Vector output = new Vector();
        boolean flag = false;
        //int dotCount;
        while (local.length() != 0) {
            Vector token1 = new Vector();
            Vector nextToken = new Vector();
            nextToken = getNextToken(local);
            Object token = new Object();
            token = nextToken.elementAt(0);
            String remaining = (String) nextToken.elementAt(1);
            String type = (String) nextToken.elementAt(2);
            if (type.equalsIgnoreCase("sexp"))
            {
                token = removeParenthesis(token.toString());
                token = InpStrToSExp(token.toString());
            }
            local = remaining;
            tokens.add(token);
        }
        if (tokens.elementAt(0).toString().equals(".")) {
            System.out.println("Error: SExpression begining with a '.'");
            System.exit(-1);
        }
        int dotCount = 0;
        for (int i = 0; i < tokens.size(); i++)//Compute the number of dots
        {
            if (tokens.elementAt(i).toString().equals(".")) {
                dotCount++;
            }
        }
        if (dotCount > 1) {
            System.out.println("Error: Invalid Dot-Notation");
            System.exit(-1);
        }
        if (tokens.size() == 1) {
            output.add(tokens.elementAt(0));
            output.add("NIL");
            return output;
        }
        if (tokens.elementAt(1).toString().equals(".")) {
            return dotToTree(tokens);
        }
        if (dotCount > 0) {
            System.out.println("Error: Invalid List-Notation");
            System.exit(-1);
        }

        return listToTree(tokens);
    }

     public static Vector listToTree(Vector tokens) {           //if input is in list form
        Vector output = new Vector();
        if (tokens.size() == 1) {
            output.add(tokens.elementAt(0));
            output.add("NIL");
            return output;
        }
        output.add(tokens.elementAt(0));
        tokens.removeElementAt(0);
        output.add(listToTree(tokens));
        return output;
    }

    public static Vector dotToTree(Vector tokens) {            //if input is in dot form
        tokens.removeElementAt(1);     
        Vector tree = new Vector();
        if (tokens.size() != 2) {
            System.out.println("Error: Invalid Dot-Notation");
            System.exit(-1);
        }
        tree.add(tokens.elementAt(0));
        tree.add(tokens.elementAt(1));
        return tree;
    }
        public static boolean isList(Object sExpression) {
        Object _sExpr = new Object();
        if (!sExpression.getClass().getName().equalsIgnoreCase("java.util.Vector")) {
            return false;
        }
        if (((Vector)sExpression).size() == 1) {
            return false;
        }
        if(sExpression.getClass().getName().equalsIgnoreCase("java.util.Vector")){
            _sExpr = ((Vector)sExpression).clone();
            while (_sExpr.getClass().getName().equalsIgnoreCase("java.util.Vector") && ((Vector)_sExpr).size() > 1) {
                _sExpr = ((Vector)_sExpr).elementAt(1);
            }
            if (_sExpr.equals("NIL")) {
                return true;
            }
        }
        return false;
    }}