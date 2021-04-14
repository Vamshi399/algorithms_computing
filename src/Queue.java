public class Queue<T> {
  private int capacity;   // queue capacity
  private T[] v;      // queue value array 
  private int front;  // index for the front element if size > 0
  private int size;   // number of values in the queue
  
  @SuppressWarnings({"unchecked"})
  public Queue(int n) { // create a queue of type T with capacity n
    capacity = n;
    v = (T[])new Object[n];
    front = 0;
    size = 0;
  }
  
  public Queue() {
    this(1000);       // default queue capacity is 1000
  }
  
  public boolean isFull() { return size == capacity; } // queue is full

  public boolean isEmpty() { return size == 0; }       // queue is empty

  public int size() { return size; } // number of values in the queue

  public void push(T x) throws Exception { // add x at the end of the queue
    if (size == capacity) throw new Exception("queue full");
    int last = (front + size) % capacity;  // last: first space beyond last value - circular sense
    v[last] = x;
    size++;
  }
  
  public T first() throws Exception { // return the first value of the queue
    if (size == 0) throw new Exception("queue empty");
    return v[front];
  }
  
  public T pop() throws Exception { // remove and return the first value of the queue
    if (size == 0) throw new Exception("queue empty");
    T temp = v[front];
    size--;
    front = (front + 1) % capacity;   // move front right by one box - circular sense
    return temp;
  }
  
  // testing a queue
  public static void main(String[] args) throws Exception {
    Queue<Integer> q = new Queue<Integer>();
    for (int i = 0; i < 10; i++) 
      q.push(i);
    while (!q.isEmpty())
      System.out.println(q.pop());
  }
}
