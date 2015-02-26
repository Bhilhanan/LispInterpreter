import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Vector;

class Foo { 
    public void m() {
    	//System.out.println("foo: 5");
    	String stringExp=new String();
        BufferedReader br = null;
        Reader r = new InputStreamReader(System.in);
       br = new BufferedReader(r);
              
            try {  
             HelperFunctionFactory helperFunctions = new HelperFunctionFactory();   
		     FunctionFactory functionFactory = new FunctionFactory();				
        HelperFunctionFactory.aList = new HashMap();
        HelperFunctionFactory.dList = new HashMap();
        helperFunctions.InputStrExp = null;
do{ 
		     System.out.flush();
            helperFunctions.InputStrExp = br.readLine();
        String exitInt=new String("exit");	
        String normalizedString = new String();			
        normalizedString = HelperFunctionFactory.formatInputString(helperFunctions.InputStrExp);
        Vector FullToken = new Vector();
        String token = new String();
        String strRemaining = new String();
        String type = new String();
        while (normalizedString.length() > 0) {
            FullToken = helperFunctions.getNextToken(normalizedString);
            token = (String) FullToken.elementAt(0).toString();
            strRemaining = (String) FullToken.elementAt(1).toString();
            type = (String) FullToken.elementAt(2).toString();
            System.out.println(token);
            if (!(helperFunctions.checkForParanthesis(token))) {
                System.out.println("Error: Invalid parenthesis");
                break;
            }
            if (type.equalsIgnoreCase("sexp")) {
                Vector sExp = new Vector();
                sExp = HelperFunctionFactory.InpStrToSExp(HelperFunctionFactory.removeParenthesis(token));
                Object evalop = new Object();
                evalop = functionFactory.eval(sExp, HelperFunctionFactory.aList, HelperFunctionFactory.dList);
                String opt = HelperFunctionFactory.sExpressionToString(evalop, false);
                System.out.println("Output: " + opt);
            } else if (type.equalsIgnoreCase("atom")) {
                Vector temp = new Vector();
                temp.add(token);
                if (functionFactory.checkInt(temp) || token.equalsIgnoreCase("T") || token.equalsIgnoreCase("NIL")) {
                    System.out.println("Output : " + token);
                }
            } else {
                System.out.println("Error: Invalid SExp: " + strRemaining);
                System.exit(-1);
            }
			System.out.println("\n");
            normalizedString = strRemaining;
			stringExp=helperFunctions.InputStrExp;
			
        }}while (!stringExp.equalsIgnoreCase("EXIT"));
		
        
    } catch (IOException e) {
        System.out.println("Error: While reading input");
    } catch (NumberFormatException e) {
        System.out.println("Error: "+e.getMessage());
    } catch (Exception e) {
        System.out.println("Error executing command");
    } finally{
       try{
           if(br != null) br.close();
        }catch(Exception ex){}
		System.out.println("\n");
    }


    }
}

