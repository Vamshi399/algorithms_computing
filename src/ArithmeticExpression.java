// Ref: https://runestone.academy/runestone/books/published/pythonds/BasicDS/InfixPrefixandPostfixExpressions.html
public class ArithmeticExpression { // showcase conversion/evaluation of arithmetic expressions in infix/postfix/prefix
  static boolean isOperator(String s) {
    char c = s.charAt(0);
    if (c == '+' || c == '-' || c == '*' || c == '/') return true;
    else return false;
  }
  
  static char matching(char c) { // return the matching left brace
    switch (c) {
      case ')': return '(';
      case ']': return '[';
      case '}': return '{';
      default: System.out.println("Matching brace falied: " + c); return (char)0;
    }
  }

  static boolean isOperand(String s) {
    char c = s.charAt(0);
    if (Character.isDigit(c) || c == '.' || Character.isLetter(c)) return true;
    else return false;
  }
  
  public static String[] infixToPostfix(String[] infix) throws Exception {
    Stack<String> stack = new Stack<String>(); // stack for operators
    String[] postfix = new String[infix.length];
    int i = -1;  // postfix[i] is the last string in postfix
      
    for (String token: infix) {  
      if (isOperand(token)) { postfix[++i] = token; continue; }
      char c = token.charAt(0);
      if (c == '(' || c == '[' || c == '{') { stack.push(token); continue; }
      if (c == ')' || c == ']' || c == '}') { 
        char rightBrace = c; 
        char leftBrace = matching(rightBrace); 
        while (!stack.isEmpty() && (stack.top().charAt(0) != leftBrace)) postfix[++i] = stack.pop();
        stack.pop(); // pop away the matching left brace
        continue;
      }
      if (c == '+' || c == '-') {
        while (!stack.isEmpty() && (stack.top().charAt(0) == '+' || stack.top().charAt(0) == '-' || 
               stack.top().charAt(0) == '*' || stack.top().charAt(0) == '/')) 
          postfix[++i] = stack.pop();
        stack.push(token);
        continue;
      } 
      if (c == '*' || c == '/') {
        while (!stack.isEmpty() && (stack.top().charAt(0) == '*' || stack.top().charAt(0) == '/')) 
          postfix[++i] = stack.pop();
        stack.push(token);   
        continue;
      }
    }
    while (!stack.isEmpty()) postfix[++i] = stack.pop();
    return java.util.Arrays.copyOfRange(postfix, 0, i+1);
  }
  
  public static double postfixEval(String[] postfix) throws Exception {
    Stack<Double> stack = new Stack<Double>();
    for (String token: postfix) {
      if (isOperand(token)) { stack.push(Double.parseDouble(token)); continue; }
      if (isOperator(token)) {
        double y = stack.pop();
        double x = stack.pop();
        switch (token.charAt(0)) {
          case '+': stack.push(x+y); break;
          case '-': stack.push(x-y); break;
          case '*': stack.push(x*y); break;
          case '/': stack.push(x/y); break;
        }
      }
    }
    return stack.pop();
  }
  
  public static String[] infixToPrefix(String[] e) { // to be completed
    return null;     
  }
  
  static void print(String label, String[] e) {
    System.out.print(label + ": ");
    for (String s: e) 
      System.out.print(s + " ");
    System.out.println();
  }
  
  static boolean evaluable(char[] exp) { // exp has no variable thus can be evaluated
    for (char c: exp)
      if (Character.isLetter(c)) return false;
    return true;
  }
  
  public static void main(String[] args) throws Exception {
    if (args.length < 1) {
      System.out.printf("Usage: java  ArithmeticExpression [arithmetic expression with numbers/variables, braces, +, -, * and / in double quotes");
      System.exit(-1);
    }
    String[] infix = Tokenizer.parse(args[0]);
    print("Infix", infix);
    String[] postfix = infixToPostfix(infix);
    print("Postfix", postfix);
    if (evaluable(args[0].toCharArray()))  // if exp has no variable thus evaluable
      System.out.printf("Evaluation result: %5.2f", postfixEval(postfix));
  }
}