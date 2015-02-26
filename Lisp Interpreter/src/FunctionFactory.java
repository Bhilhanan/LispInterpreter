import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class FunctionFactory {
    
	public static Object apply(Object function, Object x, Map aList, Map dList) {
        Vector output = new Vector();
        Vector CAR = new Vector();
        CAR.add("CAR");
        Vector CDR = new Vector();
        CDR.add("CDR");
        Vector CONS = new Vector();
        CONS.add("CONS");
        Vector ATOM = new Vector();
        ATOM.add("ATOM");
        Vector EQ = new Vector();
        EQ.add("EQ");
        Vector NULL = new Vector();
        NULL.add("NULL");
        Vector INT = new Vector();
        INT.add("INT");
        Vector PLUS = new Vector();
        PLUS.add("PLUS");
        Vector MINUS = new Vector();
        MINUS.add("MINUS");
        Vector TIMES = new Vector();
        TIMES.add("TIMES");
        Vector QUOTIENT = new Vector();
        QUOTIENT.add("QUOTIENT");
        Vector REMAINDER = new Vector();
        REMAINDER.add("REMAINDER");
        Vector LESS = new Vector();
        LESS.add("LESS");
        Vector GREATER = new Vector();
        GREATER.add("GREATER");
        if (!atom(function).equalsIgnoreCase("T")) {
            System.out.println("Error: Apply " + function);
            System.exit(-1);
            return null;
        }
        if (eq(function, CAR).equalsIgnoreCase("T")) {
            return car(car(x));
        } else if (eq(function, CDR).equalsIgnoreCase("T")) {
            return cdr(car(x));
        } else if (eq(function, CONS).equalsIgnoreCase("T")) {
            if (x.getClass().getName().equalsIgnoreCase("java.util.Vector")) {
                if (length(x) != 2) {
                    System.out.println("Error: Invalid number of parameters");
                    System.exit(-1);
                }
                return cons(car(x), car(cdr(x)));
            }

        } else if (eq(function, ATOM).equalsIgnoreCase("T")) {
            if (x.getClass().getName().equalsIgnoreCase("java.util.Vector")) {
                if (length(x) != 1) {
                    System.out.println("Error: Invalid number of parameters");
                    System.exit(-1);
                }
                output.removeAllElements();
                output.add(atom(car(x)));
                return output;
            }

        } else if (eq(function, EQ).equalsIgnoreCase("T")) {
            if (x.getClass().getName().equalsIgnoreCase("java.util.Vector")) {
                if (length(x) != 2) {
                    System.out.println("Error: Invalid number of parameters");
                    System.exit(-1);
                }
                output.removeAllElements();
                output.add(eq(car(x), car(cdr(x))));
                return output;
            }

        } else if (eq(function, NULL).equalsIgnoreCase("T")) {
            if (x.getClass().getName().equalsIgnoreCase("java.util.Vector")) {
                if (length(x) != 1) {
                    System.out.println("Error: Invalid number of parameters");
                    System.exit(-1);
                }
                output.removeAllElements();
                output.add(NULL(car(x)));
                return output;
            }

        } else if (eq(function, INT).equalsIgnoreCase("T")) {
            if (x.getClass().getName().equalsIgnoreCase("java.util.Vector")) {
                if (length(x) != 1) {
                    System.out.println("Error: Invalid number of parameters");
                    System.exit(-1);
                }
                output.removeAllElements();
                output.add(checkInt(car(x)));
                return output;
            }

        } else if (eq(function, PLUS).equalsIgnoreCase("T")) {
            if (x.getClass().getName().equalsIgnoreCase("java.util.Vector")) {
                if (length(x) != 2) {
                    System.out.println("Error: Invalid number of parameters");
                    System.exit(-1);
                }
                output.removeAllElements();
                output.add(plus(car(x), car(cdr(x))));
                return output;
            }

        } else if (eq(function, MINUS).equalsIgnoreCase("T")) {
            if (x.getClass().getName().equalsIgnoreCase("java.util.Vector")) {
                if (length(x) != 2) {
                    System.out.println("Error: Invalid number of parameters");
                    System.exit(-1);
                }
                output.removeAllElements();
                output.add(minus(car(x), car(cdr(x))));
                return output;
            }

        } else if (eq(function, TIMES).equalsIgnoreCase("T")) {
            if (x.getClass().getName().equalsIgnoreCase("java.util.Vector")) {
                if (length(x) != 2) {
                    System.out.println("Error: Invalid number of parameters");
                    System.exit(-1);
                }
                output.removeAllElements();
                output.add(times(car(x), car(cdr(x))));
                return output;
            }

        } else if (eq(function, QUOTIENT).equalsIgnoreCase("T")) {
            if (x.getClass().getName().equalsIgnoreCase("java.util.Vector")) {
                if (length(x) != 2) {
                    System.out.println("Error: Invalid number of parameters");
                    System.exit(-1);
                }
                output.removeAllElements();
                output.add(quotient(car(x), car(cdr(x))));
                return output;
            }

        } else if (eq(function, REMAINDER).equalsIgnoreCase("T")) {
            if (x.getClass().getName().equalsIgnoreCase("java.util.Vector")) {
                if (length(x)!= 2) {
                    System.out.println("Error: Invalid number of parameters");
                    System.exit(-1);
                }
                output.removeAllElements();
                output.add(remainder(car(x), car(cdr(x))));
                return output;
            }

        } else if (eq(function, LESS).equalsIgnoreCase("T")) {
            if (x.getClass().getName().equalsIgnoreCase("java.util.Vector")) {
                if (length(x)!= 2) {
                    System.out.println("Error: Invalid number of parameters");
                    System.exit(-1);
                }
                output.removeAllElements();
                output.add(less(car(x), car(cdr(x))));
                return output;
            }

        } else if (eq(function, GREATER).equalsIgnoreCase("T")) {
            if (x.getClass().getName().equalsIgnoreCase("java.util.Vector")) {
                if (length(x) != 2) {
                    System.out.println("Error: Invalid number of parameters");
                    System.exit(-1);
                }
                output.removeAllElements();
                output.add(greater(car(x), car(cdr(x))));
                return output;
            }
        } else {
            if (function.getClass().getName().equalsIgnoreCase("java.util.Vector")) {
                if (!dList.containsKey(((Vector) function).elementAt(0))) {
                    System.out.println("Error: Function " + ((Vector) function).elementAt(0).toString() + " unbound");
                    System.exit(-1);
                }
            }

            Vector pList = new Vector();
            Vector functionBody = new Vector();
            if (function.getClass().getName().equalsIgnoreCase("java.util.Vector")) {
                pList = (Vector)car(dList.get(((Vector) function).elementAt(0)));
                functionBody = (Vector)cdr(dList.get(((Vector) function).elementAt(0)));
            }

            if (x.getClass().getName().equalsIgnoreCase("java.util.Vector")) {
                if (length(pList) != length(x)) {
                    System.out.println("Error: Parameter mismatch");
                    System.exit(-1);
                }
            }

            Map aListNew = new HashMap();
            if (length(pList) == 0) {
                aListNew.putAll(aList);
            } else {
                aListNew = updatePair(pList, x, aList);
            }
            return eval(functionBody, aListNew, dList);
        }
        return null;
    }

    public static Object eval(Object sExpression, Map aList, Map dList) {
        Vector TRUE = new Vector();
        TRUE.add("T");
        Vector FALSE = new Vector();
        FALSE.add("NIL");
        Vector QUOTE = new Vector();
        QUOTE.add("QUOTE");
        Vector COND = new Vector();
        COND.add("COND");
        Vector DEFUN = new Vector();
        DEFUN.add("DEFUN");
        String name;
        Vector pList = new Vector();
        Vector funcBody = new Vector();
        Vector temp = new Vector();
        if (atom(sExpression).equalsIgnoreCase("T")) {
        	if (checkInt(sExpression)) {
                return sExpression;
            } else if (eq(sExpression, TRUE).equalsIgnoreCase("T")) {
                return TRUE;
            } else if (eq(sExpression, FALSE).equalsIgnoreCase("T")) {
                return FALSE;
            } else if (aList.containsKey(((Vector) sExpression).elementAt(0))) {
                return aList.get(((Vector) sExpression).elementAt(0));
            } else {
                System.out.println(sExpression + ":unbound");
            }
        } else if ((atom(car(sExpression))).equals("T")) {
            if (eq(car(sExpression), QUOTE).equalsIgnoreCase("T")) {
                return car(cdr(sExpression));
            } else if (eq(car(sExpression), COND).equalsIgnoreCase("T")) {
                return evcon(cdr(sExpression), aList, dList);
            } else if (eq(car(sExpression), DEFUN).equalsIgnoreCase("T")) {
                temp = (Vector) car(cdr(sExpression));
                name = temp.elementAt(0).toString();
                pList = (Vector) car(cdr(cdr(sExpression)));
                funcBody = (Vector) car(cdr(cdr(cdr(sExpression))));
                dList.put(name, cons(pList, funcBody));
                return name;
            } else {
                return apply(car(sExpression), evlis(cdr(sExpression), aList, dList), aList, dList);
            }
        }
        System.out.println("Error: Invalid s-exp: " + sExpression);
        System.exit(-1);
        return null;
    }

    public static Object evcon(Object bExpression, Map aList, Map dList) {
    	Vector TRUE = new Vector();
    	TRUE.add("T");
        if ((NULL(bExpression)).equals("T")) {
            System.out.println("Error: Invalid/empty expression for COND");
            System.exit(-1);
            return null;
        } else if ((eval(car(car(bExpression)), aList, dList)).equals(TRUE)) {
            return eval(car(cdr(car(bExpression))), aList, dList);
        } else {
            return evcon(cdr(bExpression), aList, dList);
        }
    }

    public static Object evlis(Object list, Map aList, Map dList) {
        if (NULL(list).equals("T")) {
            return "NIL";
        } else {
            return cons(eval(car(list), aList, dList), evlis(cdr(list), aList, dList));
        }
    }

    public static Map updatePair(Object pairList, Object arg, Map aList) {
        Map alist = new HashMap();
        alist.putAll(aList);
        Object value = new Object();
        String key;
        Vector NIL = new Vector();
        NIL.add("NIL");
        while (true) {
            key = ((Vector) car(pairList)).elementAt(0).toString();
            value = ((Vector) car(arg));
            alist.put(key, value);
            if ((cdr(pairList).equals(NIL))) {
                break;
            } else {
                pairList = (Vector) cdr(pairList);
                arg = (Vector) cdr(arg);
            }
        }
        return alist;
    }



    public static int length(Object sExpression) {
        int length = 0;
        Object sexpr = new Object();
        if (sExpression.getClass().getName().equalsIgnoreCase("java.util.Vector")) {
            sexpr = ((Vector)sExpression).clone();
            while (sexpr.getClass().getName().equalsIgnoreCase("java.util.Vector") && ((Vector) sExpression).size() > 1) {
                sexpr = ((Vector)sexpr).elementAt(1);
                length++;
            }
        }
        return length;
    }




    public static Object car(Object sExpression) {
        Vector sexpr = new Vector();
        if (atom(sExpression).equals("T")) {
            System.out.println("Error: Invalid number of arguments");
            System.exit(-1);
        }
        if (sExpression.getClass().getName().equals("java.util.Vector")) {
            sexpr = (Vector) sExpression;
            Vector carElem = new Vector();
            if (!(((Vector)sExpression).elementAt(0).getClass().getName().equalsIgnoreCase("java.util.Vector"))) {
                carElem.add(((Vector)sExpression).elementAt(0));
                return carElem;
            }
            return ((Vector)sExpression).elementAt(0);
        }
        return null;
    }

    public static Object cdr(Object sExpression) {
        Vector localSexp = new Vector();
        Vector cdrElem = new Vector();
        Vector sexpr = new Vector();
        if (atom(sExpression).equals("T")) {
            System.out.println("Error: trying cdr on atomic expression");
            System.exit(-1);
        }
        if (sExpression.getClass().getName().equals("java.util.Vector")) {
            localSexp = (Vector) sExpression;

            sexpr = (Vector) localSexp.clone();
            if (!sexpr.elementAt(1).getClass().getName().equalsIgnoreCase("java.util.Vector")) {
                cdrElem.add(sexpr.elementAt(1));
                return cdrElem;
            }
            return sexpr.elementAt(1);

        }
        return null;

    }

    public static String atom(Object sExpression) {
        if (sExpression.getClass().getName().equals("java.util.Vector")) {
            Vector sexpr = new Vector();
            sexpr = (Vector) sExpression;
            if (sexpr.size() == 1) {
                return "T";
            }
            return "NIL";
        }
        return "T";
    }

    public static Object cons(Object sExpression1, Object sExpresssion2) {
        Vector sexp = new Vector();
        sexp.add(sExpression1);
        sexp.add(sExpresssion2);
        return (Vector)sexp;
    }

    public static String eq(Object sExpression1, Object sExpression2) {
        if (!((atom(sExpression1)).equalsIgnoreCase("T") && (atom(sExpression2)).equalsIgnoreCase("T"))) {
            System.out.println("Error: trying EQ on non-atoms");
            System.exit(-1);
        }
        if (sExpression1.equals(sExpression2)) {
            return "T";
        }
        return "NIL";
    }

    public static String NULL(Object sExpression) {
        Vector NIL = new Vector();
        NIL.add("NIL");
        if ((atom(sExpression)).equalsIgnoreCase("T") && (eq(sExpression, NIL)).equalsIgnoreCase("T")) {
            return "T";
        }
        return "NIL";
    }

    public static boolean checkInt(Object sExpression) {
        Vector localSexp = new Vector();
        Vector sexpr = new Vector();
        if (sExpression.getClass().getName().equalsIgnoreCase("java.util.Vector")) {
        	String tmpStr;
        	localSexp = (Vector) sExpression;
            sexpr = (Vector) localSexp.clone();
            String firstString = sexpr.elementAt(0).toString();
            char firstChar = firstString.charAt(0);
            if (firstChar == '-') {
                tmpStr = firstString.substring(1);
            } else if (firstChar == '+') {
                tmpStr = firstString.substring(1);
            } else {
                tmpStr = firstString;
            }
            if (tmpStr.getClass().getName().equalsIgnoreCase("java.util.Vector")) {
                return false;
            }
            return isInt(tmpStr);
        }
        return isInt(sExpression.toString());
    }

    public static boolean isInt(String inputStr) {
        try {
            Integer.parseInt(inputStr.toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String plus(Object sExpression1, Object sExpression2) {
        if (!(atom(sExpression1).equalsIgnoreCase("T")) && (atom(sExpression2).equalsIgnoreCase("T"))) {
            System.out.println("Error: trying PLUS on non-atoms");
            System.exit(-1);
        }
        if (!(checkInt(sExpression1) && checkInt(sExpression2))) {
            System.out.println("Error: trying PLUS on non-integers");
            System.exit(-1);
        }
        Integer result = new Integer(0);
        result = Integer.parseInt(((Vector) sExpression1).elementAt(0).toString().replace("+", "")) + Integer.parseInt(((Vector) sExpression2).elementAt(0).toString().replace("+",""));
        return result.toString();
    }

    public static String minus(Object sExpression1, Object sExpression2) {
        if (!(atom(sExpression1).equalsIgnoreCase("T")) && (atom(sExpression2).equalsIgnoreCase("T"))) {
            System.out.println("Error: Tyring MINUS on non-atoms");
            System.exit(-1);
        }
        if (!(checkInt(sExpression1) && checkInt(sExpression2))) {
            System.out.println("Error: Trying MINUS on non-integers");
            System.exit(-1);
        }
        Integer result = new Integer(0);
        result = Integer.parseInt(((Vector) sExpression1).elementAt(0).toString().replace("+", "")) - Integer.parseInt(((Vector) sExpression2).elementAt(0).toString().replace("+",""));
        return result.toString();
    }

    public static String times(Object sExpression1, Object sExpression2) {
        if (!(atom(sExpression1).equalsIgnoreCase("T")) && (atom(sExpression2).equalsIgnoreCase("T"))) {
            System.out.println("Error: Trying TIMES on non-atoms");
            System.exit(-1);
        }
        if (!(checkInt(sExpression1) && checkInt(sExpression2))) {
            System.out.println("Error: Trying TIMES on non-integers");
            System.exit(-1);
        }
        Integer result = new Integer(0);
        result = Integer.parseInt(((Vector) sExpression1).elementAt(0).toString().replace("+", "")) * Integer.parseInt(((Vector) sExpression2).elementAt(0).toString().replace("+",""));
        return result.toString();
    }

    public static String quotient(Object sExpression1, Object sExpression2) {
        if (!(atom(sExpression1).equalsIgnoreCase("T")) && (atom(sExpression2).equalsIgnoreCase("T"))) {
            System.out.println("Error: Trying QUOTIENT on non-atoms");
            System.exit(-1);
        }
        if (!(checkInt(sExpression1) && checkInt(sExpression2))) {
            System.out.println("Error: Trying QUOTIENT on non-integers");
            System.exit(-1);
        }
        Integer result = new Integer(0);
        result = Integer.parseInt(((Vector) sExpression1).elementAt(0).toString().replace("+", "")) / Integer.parseInt(((Vector) sExpression2).elementAt(0).toString().replace("+",""));
        return result.toString();
    }

    public static String remainder(Object sExpression1, Object sExpression2) {
        if (!(atom(sExpression1).equalsIgnoreCase("T")) && (atom(sExpression2).equalsIgnoreCase("T"))) {
            System.out.println("Error: Trying REMAINDER on non-atoms");
            System.exit(-1);
        }
        if (!(checkInt(sExpression1) && checkInt(sExpression2))) {
            System.out.println("Error: Trying REMAINDER on non-integers");
            System.exit(-1);
        }
        Integer result = new Integer(0);
        result = Integer.parseInt(((Vector) sExpression1).elementAt(0).toString().replace("+", "")) % Integer.parseInt(((Vector) sExpression2).elementAt(0).toString().replace("+",""));
        return result.toString();
    }

    public static String less(Object sExpression1, Object sExpression2) {
        if (!(atom(sExpression1).equalsIgnoreCase("T")) && (atom(sExpression2).equalsIgnoreCase("T"))) {
            System.out.println("Error: Trying LESS on non-atoms");
            System.exit(-1);
        }
        if (!(checkInt(sExpression1) && checkInt(sExpression2))) {
            System.out.println("Error: Trying LESS on non-integers");
            System.exit(-1);
        }
        int input1;
        int input2;
        input1 = Integer.parseInt(((Vector) sExpression1).elementAt(0).toString().replace("+", ""));
        input2 = Integer.parseInt(((Vector) sExpression2).elementAt(0).toString().replace("+", ""));
        if (input1 < input2) {
            return "T";
        }
        return "NIL";
    }

    public static String greater(Object sExpression1, Object sExpression2) {
        if (!(atom(sExpression1).equalsIgnoreCase("T")) && (atom(sExpression2).equalsIgnoreCase("T"))) {
            System.out.println("Error: Trying GREATER on non-atoms");
            System.exit(-1);
        }
        if (!(checkInt(sExpression1) && checkInt(sExpression2))) {
            System.out.println("Error: Trying GREATER on non-integers");
            System.exit(-1);
        }
        int input1;
        int input2;
        input1 = Integer.parseInt(((Vector) sExpression1).elementAt(0).toString().replace("+", ""));
        input2 = Integer.parseInt(((Vector) sExpression2).elementAt(0).toString().replace("+", ""));
        if (input1 > input2) {
            return "T";
        }
        return "NIL";
    }


}