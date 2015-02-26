Bhilhanan A Jeyaram

Build information :
	The javac path is included in the makefile. If the javac path is different from /usr/local/jdk1.7.0_45/bin/javac then change the path in makefile.

Design :
	There program starts execution from main() present in MyInt. Inside this function, an object for Foo is created and m() is invoked. Inside m(), input is scanned from System.in and the output will be displayed in System.out. After initial checks for correct parenthesis using checkForParanthesis() and special characters using checkForSpecialCharacters(), the input string is parsed and stored using Vectors. HelperFunctionFactory class contains methods that are used for formatting the input and converting from one form to other.
	During parsing, the input string is parsed into tokens using getNextToken() and is used to populate the Vector.
	All the functions required by the program is split into two classes :
	(1) FunctionFactory -
			This class has all the functions that are predefined in Lisp. These functions include apply, eval, evcon, evlist, update, length, car, cdr, atom, cons, eq, NULL, checkInt, isInt, plus, minus, times, quotient, remainder, less and greater
	
	(2) HelperFunctionFactory -
			The HelperFunctionFactory includes functions which are required while parsing the input string for purposes like formatting the input, converting from one notation to another, maintaining a list of arguments or defined functions. The functions that are defined in this class are explained below -

			InputStrExp - which holds the input string from console

			aList - holds the argument-value pairs

			dList - holds the list of defined functions in the domain

			formatInputString - checks the string for presence of proper whitespaces. If so, this method returns a string which has been trimmed for excess of whitespaces

			getNextToken - given a string, this method will split the string into tokens and return a Vector which holds the tokens followed by the kind of input("sexp", "dot", "error") from which the tokens were extracted 

			checkForParenthesis - checks the correct number and order of open and close parenthesis

			removeParenthesis - the argument will be a string which has parenthesis as its first and last character. This method will return a string without the outmost parenthesis

			sExpressionToString - convert a given string in SExpression to strings for further processing

			InputStrToSExp - converts a given string into SExpression

			listToTree - converts a given Vector from list notation to tree notation

			dotToTree - converts a Ventor from dot notation to tree notation

			isList - takes an object as argument and returns a boolean value based on whether the object is a list
	When the m() of Foo is invoked, the program takes an input from the console and checks for validity. Once it is done, the input string is then processed by the methods in FunctionFactory and HelperFunctionFactory to evaluate the input.
	The input may be either an error in which case an error message is displayed, or an atom in which case the value of the atom is displayed or an SExpression in which case the value that is evaluated is displayed.
	The evaluation of the expression is done in recursive manner, invoking one of the FunctionFactory methods as nested expressions are evaluated. 
Input :
	Command-line input

Output :
	The output will be displayed on the console. If the input is valid then the program will evaluate the input and display the result. Otherwise it will display an error message.