public class StackPalindrome {
  static boolean isPalindrome(char[] s) throws Exception {
    int length =  s.length;
    int half = length/2;
    Stack<Character> stack = new Stack<>();
    int i = 0;  // index of character under inspection
    for (i = 0; i < half; i++) stack.push(s[i]);
    if (half*2 != length) i++; // if s is of odd length, skip the middle character
    for (; i < length; i++) 
      if (stack.pop() != s[i]) return false;
    return true;
  }
  
  public static void main(String args[]) throws Exception {
    if (args.length == 0) {
      System.out.println("Usage: java  StackPalindrome [a string with no white space]");
      System.exit(-1);
    }
    char[] input = args[0].toCharArray();
    if (isPalindrome(input))
      System.out.println("\"" + args[0] + "\" is a palindrome.");
    else
      System.out.println("\"" + args[0] + "\" is not a palindrome.");  
  }
}
