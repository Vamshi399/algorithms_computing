public class Stack<T> {
  private int capacity;   // stack capacilty
  private T[] v;      // stack value array 
  private int top;    // index for stack top element, -1 for empty stack
  
  @SuppressWarnings({"unchecked"})
  public Stack(int n) { // create a stack of type T with capacity n
    capacity = n;
    v = (T[])new Object[n];
    top = -1;
  }
  
  public Stack() {
    this(1000);       // default stack capacity is 1000
  }
  
  public boolean isFull() { return top == capacity - 1; } // stack is full

  public boolean isEmpty() { return top == -1; }        // stack is empty

  public int size() { return top+1; } // number of values in the stack

  public void push(T x) throws Exception { 
    if (top >= capacity-1) throw new Exception("stack full");
    v[++top] = x;
  }

  public T top() throws Exception { // return stack-top element
    if (top == -1) throw new Exception("stack empty");
    return v[top];
  }

  public T pop() throws Exception { // return and delete stack-top element
    if (top == -1) throw new Exception("stack empty");
    return v[top--];
  }
  
  // testing a stack
  public static void main(String[] args) throws Exception {
    Stack<Integer> s = new Stack<Integer>();
    for (int i = 0; i < 10; i++) 
      s.push(i);
    while (!s.isEmpty())
      System.out.println(s.pop());
  }
}
