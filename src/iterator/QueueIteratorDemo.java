package iterator;

import java.util.*;

public class QueueIteratorDemo {
  public static void main(String[] args) {
    Queue<String> s = new java.util.LinkedList<String>();
    for (int i = 1; i < 5; i++) {
      System.out.println("add " + i); 
      s.add(""+i);  // pushing String objects
    }
    java.util.Iterator i = s.iterator();  // retrieve an Iterator for the stack
    // An Iterator is a list of references for the objects in a data structure,
    // with a read pointer initialized to before the first value
    while (i.hasNext())  // does i still have unvisited values?
      System.out.println(i.next());  // if yes, retrieve and return the next value, and move i to the next value
    while (!s.isEmpty()) 
      System.out.println("delete " + s.remove());
  }
}
