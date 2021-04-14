public class DoubleLinkNode<T> {
  public T v;
  public DoubleLinkNode<T> left, right;
  public DoubleLinkNode() {
	this(null, null, null);
  }
  
  public DoubleLinkNode(T v, DoubleLinkNode<T> left, DoubleLinkNode<T> right) {
	this.v = v;
	this.left = left; 
	this.right = right;	
  }

  public static void main(String[] args) {
	DoubleLinkNode<Integer> a = new DoubleLinkNode<Integer>(1, null, null);
	DoubleLinkNode<Integer> b = new DoubleLinkNode<Integer>(2, a, null);
	a.right = b;
	DoubleLinkNode<Integer> c = a;
	while (c != null) {
	  System.out.println(c.v + " ");
	  c = c.right;
	}
	System.out.println();
	c = b;
	while (c != null) {
	  System.out.println(c.v + " ");
	  c = c.left;
	}
  }
}
