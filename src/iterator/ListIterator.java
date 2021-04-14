package iterator;

public class ListIterator<T> implements Iterator<T> {
  private Node<T> p;
  
  public ListIterator(Node<T> p) { this.p = p; }
  
  public boolean hasNext() {
    return p != null;
  }
  
  public T next() {
    T v = p.v;
    p = p.next;
    return v;
  }
}
