package iterator;

public class Node<T> implements Iterable<T> {
  public T v;
  public Node next;
  public Node() {
	this(null, null);
  }
  
  public Node(T v, Node<T> next) {
	this.v = v;
	this.next = next; 
  }
  
  public Iterator<T> iterator() {
    return new ListIterator<T>(this);
  }
	
  public static void main(String[] args) {
	Node<Integer> a = new Node<Integer>(1, null);
	Node<Integer> b = new Node<Integer>(2, a);
	Node<Integer> c = b;
	while (c != null) {
	  System.out.println(c.v + " ");
	  c = c.next;
    }
    Iterator<Integer> i = b.iterator();
    while (i.hasNext())
      System.out.print(i.next() + " ");
  }
}
