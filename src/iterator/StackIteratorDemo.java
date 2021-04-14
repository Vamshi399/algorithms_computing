package iterator;

import java.util.*;

public class StackIteratorDemo {
  public static void main(String[] args) {
    Stack<String> s = new Stack<String>();
    for (int i = 1; i < 5; i++) {
      System.out.println("Pushing " + i); 
      s.push(""+i);  // pushing String objects
    }
    java.util.Iterator i = s.iterator();  // retrieve an Iterator for the stack
    // An Iterator is a list of references for the objects in a data structure,
    // with a read pointer initialized to before the first value
    while (i.hasNext())  // does i still have unvisited values?
      System.out.println(i.next());  // if yes, retrieve and return the next value, and move i to the next value
    while (!s.isEmpty()) 
      System.out.println("popping " + s.pop());
  }
}
