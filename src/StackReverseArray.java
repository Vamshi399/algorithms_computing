public class StackReverseArray {

  /** A generic method for reversing an array. */
  public static <E> void reverse(E[] a) throws Exception {
	Stack<E> buffer = new Stack<>(a.length);
	for (int i=0; i < a.length; i++)
	  buffer.push(a[i]);
	for (int i=0; i < a.length; i++)
	  a[i] = buffer.pop( );
  }	

  /** Tester routine for reversing arrays */
  public static void main(String args[ ]) throws Exception { 
	Integer[ ] a = {4, 8, 15, 16, 23, 42}; // autoboxing allows this
	String[ ] s = {"Jack", "Kate", "Hurley", "Jin", "Michael"};
	System.out.println("a = " + java.util.Arrays.toString(a));
	System.out.println("s = " + java.util.Arrays.toString(s));
	System.out.println("Reversing...");
	reverse(a);
	reverse(s);
	System.out.println("a = " + java.util.Arrays.toString(a));
	System.out.println("s = " + java.util.Arrays.toString(s));
  }	
}
