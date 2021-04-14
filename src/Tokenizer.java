// convert a string of arithmetic expression into tokens of numbers (integers or decimal values), 
// variables (sequence of letters), operators (+, -, *, /), (, ), [, ], { and }
// "12+x* 45.6/(5+yy)" => "12 + x * 45.6 / ( 5 + yy ) 
public class Tokenizer {
  public static String[] parse(String expression) { 
 	char[] input = expression.toCharArray(); 
    String[] stringToken = new String[input.length]; // conservative length
 	int lastTokenIndex = -1; // stringToken[index] is the last token found
 	int inputIndex = 0; // input[inputIndex] is currently under inspection 
 	char[] charToken = new char[10]; // limit tokens to have length <= 10

 	while (inputIndex < input.length) {  
 	  while (Character.isWhitespace(input[inputIndex])) inputIndex++; // skip white spaces
 	  int i = 0; 
 	  if (Character.isLetter(input[inputIndex])) { // tokenize variable name
 	    while ((inputIndex < input.length) && Character.isLetter(input[inputIndex])) 
 	      charToken[i++] = input[inputIndex++];
 	    stringToken[++lastTokenIndex] = new String(charToken, 0, i);  
 	    continue;
 	  }
 	  if (Character.isDigit(input[inputIndex]) || input[inputIndex] == '.') { // tokenize number
 	    boolean hasSeenPeriod = false; // avoid number sequence with more than one '.'
 	    while ((inputIndex < input.length) && 
 	           (Character.isDigit(input[inputIndex]) 
 	              || (input[inputIndex] == '.') && !hasSeenPeriod)) {
 	      if (input[inputIndex] == '.') hasSeenPeriod = true;
 	        charToken[i++] = input[inputIndex++];
 	    }
 	    stringToken[++lastTokenIndex] = new String(charToken, 0, i);  
 	    continue;
 	  }
 	  if (input[inputIndex] == '+') { stringToken[++lastTokenIndex] = "+"; inputIndex++; continue; }
      if (input[inputIndex] == '-') { stringToken[++lastTokenIndex] = "-"; inputIndex++; continue; }
      if (input[inputIndex] == '*') { stringToken[++lastTokenIndex] = "*"; inputIndex++; continue; }
      if (input[inputIndex] == '/') { stringToken[++lastTokenIndex] = "/"; inputIndex++; continue; }  
      if (input[inputIndex] == '(') { stringToken[++lastTokenIndex] = "("; inputIndex++; continue; }  
      if (input[inputIndex] == ')') { stringToken[++lastTokenIndex] = ")"; inputIndex++; continue; }  
      if (input[inputIndex] == '[') { stringToken[++lastTokenIndex] = "["; inputIndex++; continue; }  
      if (input[inputIndex] == ']') { stringToken[++lastTokenIndex] = "]"; inputIndex++; continue; }  
      if (input[inputIndex] == '{') { stringToken[++lastTokenIndex] = "{"; inputIndex++; continue; }  
      if (input[inputIndex] == '}') { stringToken[++lastTokenIndex] = "}"; inputIndex++; continue; }  
      inputIndex++; // skip any illegal char
    }
    return java.util.Arrays.copyOfRange(stringToken, 0, lastTokenIndex+1);
  }
 		
  public static void main(String[] args) {
    String[] token = parse(args[0]);
    for (int i = 0; i < token.length; i++)
	  System.out.print(token[i] + " ");
  }
}
