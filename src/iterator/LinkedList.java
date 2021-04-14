package iterator;

public class LinkedList<T> {
  public Node<T> buildLinkedList(T[] v) { // build a linked list with successive values in v[], v[0] at the tail
	Node<T> p = null;
	for (int i = 0; i < v.length; i++) {
	  p = new Node<T>(v[i], p);
	}
	return reverse(p);
  }

  public Node<T> reverse(Node<T> head) {
	Node<T> reversedHead = null;
	while (head != null) {
	  Node<T> temp = head;
	  head = head.next;
	  temp.next = reversedHead;
	  reversedHead = temp;
	}
	return reversedHead;
  }

  public void printLinkedList(Node<T> p) {
	while (p != null) {
	  System.out.print(p.v + " ");
	  p = p.next;
	}
  }
  
  public void printLinkedListWithIterator(Node p) {
    Iterator<T> i = p.iterator();
    System.out.print("Print with iterator: ");
    while (i.hasNext()) 
      System.out.print(i.next() + " ");
      System.out.println();
    }
	
	public int length(Node<T> p) {
	  int count = 0;
	  while (p != null) {
		count++;
		p = p.next;
	  }
	  return count;
  }
  
  public LinkedList() {}
	
  public <T> LinkedList(String[] args) {
	LinkedList list = new LinkedList<T>();
	Integer[] v = { 1, 2, 3, 4, 5, 6};
	Node<T> p = list.buildLinkedList(v);
	System.out.println("List forward listing: ");
	list.printLinkedList(p);
    System.out.println();
    System.out.println("List length: " + list.length(p));
    printLinkedListWithIterator(p);
	p = list.reverse(p);
    System.out.println("List backward listing: ");
    list.printLinkedList(p);
	System.out.println();
	System.out.println("List length: " + list.length(p));
    printLinkedListWithIterator(p);
  }
  
  public static void main(String[] args) {
    new LinkedList<Integer>(args);
  }
}
