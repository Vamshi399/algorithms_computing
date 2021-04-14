/** Simplified test of matching delimiters in a string. */
import java.util.Scanner;

public class StackMatchDelimiters {
  /** Tests if delimiters in the given expression are properly matched. */
  public static boolean isMatched(String expression) throws Exception {
    final String opening  = "({[";                // opening delimiters
    final String closing  = ")}]";                // respective closing delimiters
    Stack<Character> buffer = new Stack<>();
    for (char c : expression.toCharArray()) {
      if (opening.indexOf(c) != -1)               // this is a left delimiter
        buffer.push(c);
      else if (closing.indexOf(c) != -1) {        // this is a right delimiter
        if (buffer.isEmpty())                     // nothing to match with
          return false;
        if (closing.indexOf(c) != opening.indexOf(buffer.pop()))
          return false;                           // mismatched delimiter
      }
    }
    return buffer.isEmpty();                      // were all opening delimiters matched?
  }

  final static String[] valid = {
    "()(()){([()])}",
    "( ) ( ( ) ) {( [ ( )  ] ) } ",
    "(3) (3 + (4 - 5) ) {( [ ( )  ] ) } ",
    "((()(()){([()])}))",
    "[(5+x)-(y+z)]"
  };

  final static String[] invalid = {
    ")(()){([()])}",
    "({[])}",
    "("
  };

  public static void main(String[] args) throws Exception {
    for (String s : valid)
      if (!isMatched(s))
        System.out.println("Error evaluating matched string: " + s);

    for (String s : invalid)
      if (isMatched(s))
        System.out.println("Error evaluating unmatched string: " + s); 
     
    Scanner keyboard = new Scanner(System.in);
    String input = keyboard.nextLine();
    if (isMatched(input))
    	System.out.println("Input string has matched braces"); 
    else
      System.out.println("Input string has unmatched braces"); 
  }
}
