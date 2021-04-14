package dsaj.primer;

/**
 * A demonstration of Java's wrapper types.
 *
 * @author Vamshi Krishna Shanagonda
 */
public class WrapperDemo {
  public static void main(String[] args) {
    int j = 8;
    Integer a = new Integer(12);
    int k = a;                         // implicit call to a.intValue()
    int m = j + a;                     // a is automatically unboxed before the addition
    a = 3 * m;                         // result is automatically boxed before assignment
    Integer b = new Integer("-135");   // constructor accepts a String
    int n = Integer.parseInt("2013");  // using static method of Integer class
    System.out.println("a = " + a);
    System.out.println("b = " + b);
    System.out.println("j = " + j);
    System.out.println("k = " + k);
    System.out.println("m = " + m);
    System.out.println("n = " + n);
  }
}


