// Priority queue: whatever order for (key, value) insertion, deletion always removes/returns the value with the largest key value
// Implemened by a heap tree, in which each node has key value larger than those of its son(s)
// If data member isIncreasing = true, the large/small and order above reverse

public class Heap<T extends Comparable<T>> {
  // T in general has structure k or <k, v> where k is the key. 
  // For the later, define T's Comparable interface by implementing compareTo().
  private int capacity; // implementation array size
  private int size;     // current number of values in the heap
  private T[] v;        // data array
  private boolean isIncreasing = false; // false/true: next() and removeNext() return values largest-first/smallest-first
  
  protected int left(int p) { return 2*p + 1; }  // p is index of a node, return index of the node's left son
  protected int right(int p) { return 2*p + 2;}  // p is index of a node, return index of the node's right son
  protected int parent(int p) { return (p-1)/2; }   // p is index of a node, return index of its parent node
  protected boolean hasLeft(int p) { return left(p) < size; }    // node with index p has left son
  protected boolean hasRight(int p) { return right(p) < size; }  // node with index p has right son
  protected void swap(int i, int j) { T temp = v[i]; v[i] = v[j]; v[j] = temp; } // swap nodes at indices i and j
  public int getSize() { return size; } // return number of nodes in the heap
  
  @SuppressWarnings({"unchecked"})
  public Heap(int n) { // create an empty heap of element type T with capacity n
    capacity = n;
    v = (T[])new Comparable[n];
    size = 0;
  }
  
  public Heap() { this(100); } // create an empty heap of element/node type T with capacity 1000
  
  @SuppressWarnings({"unchecked"})
  public Heap(T[] w, boolean isIncreasing) throws Exception { // initialize the heap with an array of values/nodes; unused cells must have null
                                                              // isIncreasing: false/true for removing nodes in decreasing/increasing order
    capacity = w.length;
    this.isIncreasing = isIncreasing;
    v = (T[])new Comparable[capacity];
    size = 0;
    for (int i = 0; i < w.length; i++) {
      if (w[i] == null) continue;
      insert(w[i]);
    }
  }
  
  // restore heap property after inserting a new node at the first available leaf vacancy of the heap tree, with index j, for decreasing heap
  protected void upheapDecreasing(int j) { // move node at index j up, if necessary, to restore the heap property
    while (j > 0) {
      int p = parent(j);
      if (v[j].compareTo(v[p]) <= 0) break; // if v[j] <= v[p], heap property is restored, quit
      // v[j] > v[p], so swap v[j] and v[p], and let j point to the larger one
      swap(j, p);
      j = p;
    }
  }
  
  // restore heap property after inserting a new node at the first available leaf vacancy of the heap tree, with index j, for increasing heap
  protected void upheapIncreasing(int j) { // move node at index j up, if necessary, to restore the heap property
    while (j > 0) {
      int p = parent(j);
      if (v[j].compareTo(v[p]) >= 0) break; // if v[j] >= v[p], heap property is restored, quit
      // v[j] < v[p], so swap v[j] and v[p], and let j point to the smaller one
      swap(j, p);
      j = p;
    }
  }
  
  // a new value is put in the heap at index j, move it and its descendents down the heap until the heap property is restored for the sub-heap rooted at j.
  // this version is for decreasing heap
  protected void downheapDecreasing(int j) {
    while (hasLeft(j)) {
      int leftIndex = left(j);
      int largerChildIndex = leftIndex;  
      if (hasRight(j)) {
        int rightIndex = right(j);
        if (v[leftIndex].compareTo(v[rightIndex]) < 0) 
          largerChildIndex = rightIndex;
      }
      if (v[largerChildIndex].compareTo(v[j]) <= 0) break;
      swap(j, largerChildIndex); 
      j = largerChildIndex;
    }
  }
  
  // a new value is put in the heap at index j, move it and its descendents down the heap until the heap property is restored for the sub-heap rooted at j.
  // this version is for increasing heap
  protected void downheapIncreasing(int j) {
    while (hasLeft(j)) {
      int leftIndex = left(j);
      int smallerChildIndex = leftIndex;  
      if (hasRight(j)) {
        int rightIndex = right(j);
        if (v[leftIndex].compareTo(v[rightIndex]) > 0) 
          smallerChildIndex = rightIndex;
      }
      if (v[smallerChildIndex].compareTo(v[j]) >= 0) break;
      swap(j, smallerChildIndex); 
      j = smallerChildIndex;
    }
  }
  
  public void insert(T value) throws Exception { // insert new value in the heap
    if (size == capacity) throw new Exception("Insert into full heap");
    v[size] = value;
    if (!isIncreasing)
      upheapDecreasing(size++);
    else
      upheapIncreasing(size++);
  }
  
  public T getNext() throws Exception { // return the next value in the heap: 
                                        // isIncreasing=false, largest-first; isIncreasing=true, smallest-first
    if (size == 0) throw new Exception("Access empty heap");
    return v[0];
  }
  
  public T removeNext() throws Exception { // remove and return the next value in the heap: 
                                           // isIncreasing=false, largest-first; isIncreasing=true, smallest-first
    if (size == 0) throw new Exception("Remove from empty heap");
    T value = v[0];
    v[0] = v[size-1];
    size--;
    if (!isIncreasing)
      downheapDecreasing(0);
    else
      downheapIncreasing(0);
    return value;
  }  
  
  public void heapSort(T[] w) throws Exception { // sort x in non-descending/non-increasing order in w[], assuming no null in w[], depending on isIncreasing being false/true
    int n = w.length;
    for (int i = 0; i < n; i++) insert(w[i]);
    for (int i = 0; i < n; i++) w[n-1-i] = removeNext();
  }
  
  public void setIncreasing(boolean useIncreasing) { // useIncreasing: true for smaller-first heap, false for larger-first heap
    isIncreasing = useIncreasing;
  }
  
  static void testing(boolean increasing) throws Exception {
    // test Heap<Integer>
    int[] v1 = {45, 12, 7, 33, 43, 7};
    Heap<Integer> h1 = new Heap<Integer>(10);
    // create increasing/decreasing heap: value with smaller/larger key gets removed first
    h1.setIncreasing(increasing); 
    for (int i : v1) h1.insert(i);
    int n = h1.getSize();
    for (int i = 0; i < n; i++) 
      System.out.print(h1.removeNext() + " ");
    System.out.println();
    // test Heap<String>
    String[] v2 = {"Tom", "Mary", "Alexander", "Cindy", "Rob"};
    // create increasing/decreasing heap: value with smaller/larger key gets removed first
    Heap<String> h2 = new Heap<String>(v2, increasing);
    n = h2.getSize();
    for (int i = 0; i < n; i++) 
      System.out.print(h2.removeNext() + " ");
    System.out.println();
    // test Heap<HeapSampleNode>

    System.out.println(); 
  }
  
  public static void main(String[] args) throws Exception {
    System.out.println("------Testing with decreasing heap------");
    testing(false);
    System.out.println("------Testing with increasing heap------");
    testing(true);
  }
}

/*
>javac Heap.java
>java Heap
------Testing with decreasing heap------
45 43 33 12 7 7
Tom Rob Mary Cindy Alexander

------Testing with increasing heap------
7 7 12 33 43 45
Alexander Cindy Mary Rob Tom
*/
