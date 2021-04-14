import java.util.Scanner; 
import java.util.Stack;

public class InfixExpressionTree {
  private static class Node {
    char v;
    Node left;
    Node right;

    Node(char x, Node left, Node right) { 
      v = x; 
      this.left = left;
      this.right = right;
    }

    void display() { System.out.print(v); }
  }

  private static class Conversion { // convert infix exp into postfix exp
    private Stack<Character> opStack; // it holds +, -, *, /, or ( only
    private String input;  // infix expression with potential parentheses
    private String output = "";  // postfix expression to return

    Conversion(String str) {
      input = str;
      opStack = new Stack<Character>();
    }

    String inToPost() { // convert infix exp in input to postfix exp in output
      for (int i = 0; i < input.length(); i++) {
        char ch = input.charAt(i);
        switch (ch) {
          case '+':
          case '-':
            gotOperator(ch, 1);
            break;
          case '*':
          case '/':
            gotOperator(ch, 2);
            break;
          case '(':
            opStack.push(ch);
            break;
          case ')':
            rightParenthesis();
            break;
          default: // operands: letters or digits
            output = output + ch;
        }
      }
      while (!opStack.isEmpty())
        output = output + opStack.pop();
      return output;
    }

    // stack-top ops with larger or equal precedence should be popped/evaluated
    private void gotOperator(char op, int prec1) {
      while (!opStack.isEmpty()) {
        char opTop = opStack.pop();
        if (opTop == '(') {
          opStack.push(opTop); // '(' should only be popped by ')'
          break;
        } else { // opTop must be operator
          int prec2;
          if (opTop == '+' || opTop == '-')
            prec2 = 1;
          else
            prec2 = 2;
          if (prec2 < prec1) { // opTop has low precedence thus be evaluated later
            opStack.push(opTop); 
            break;
          } else
            output = output + opTop;
        }
      }
      opStack.push(op);
    }

    void rightParenthesis() {
      while (!opStack.isEmpty()) {
        char ch = opStack.pop();
        if (ch == '(')
          break;
        else
          output = output + ch;
      }
    }
  }   

  private static class Tree {
    private Node root = null;

    // insert each char of a postfix expression into the empty binary tree
    public void insert(String s) { 
      Conversion c = new Conversion(s);
      s = c.inToPost();
      Stack<Node> stk = new Stack<Node>();
      s = s + "#";
      int i = 0;
      char symbol = s.charAt(i);
      Node newNode;
      while (symbol != '#') {
        if (symbol >= '0' && symbol <= '9' || symbol >= 'A'
            && symbol <= 'Z' || symbol >= 'a' && symbol <= 'z') {
          newNode = new Node(symbol, null, null);
          stk.push(newNode);
        } else if (symbol == '+' || symbol == '-' || symbol == '*'
                   || symbol == '/') {
          Node ptr1 = stk.pop();
          Node ptr2 = stk.pop();
          newNode = new Node(symbol, ptr2, ptr1);
          stk.push(newNode);
        }
        symbol = s.charAt(++i);
      }
      root = stk.pop();
    }
    
    public void traverse(int type) {
      switch (type) {
        case 1:
          System.out.print("Preorder Traversal:-    ");
          preOrder(root);
          break;
        case 2:
          System.out.print("Inorder Traversal:-     ");
          inOrder(root);
          break;
        case 3:
          System.out.print("Postorder Traversal:-   ");
          postOrder(root);
          break;
        default:
          System.out.println("Invalid Choice");
      }
    }

    void preOrder(Node localRoot) {
      if (localRoot == null) return;
      localRoot.display();
      preOrder(localRoot.left);
      preOrder(localRoot.right);
    }

    void inOrder(Node localRoot) {
      if (localRoot == null) return;
      inOrder(localRoot.left);
      localRoot.display();
      inOrder(localRoot.right);
    }
    
    void postOrder(Node localRoot) {
      if (localRoot == null) return;
      postOrder(localRoot.left);
      postOrder(localRoot.right);
      localRoot.display();
    }
  }
  
  public static void main(String args[]) {
    String ch = "y";
    Scanner inp = new Scanner(System.in);
    while (ch.equals("y")) {
      Tree t1 = new Tree();
      System.out.println("Enter an expression with single letters/digits combined by +, -, *, / and ()");
      String a = inp.nextLine();
      t1.insert(a);
      t1.traverse(1);
      System.out.println("");
      t1.traverse(2);
      System.out.println("");
      t1.traverse(3);
      System.out.println("");
      System.out.print("Enter y to continue ");
      ch = inp.nextLine();
    }
  }
}