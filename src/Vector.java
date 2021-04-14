public class Vector<T> { // growable array of values of type T
  private int size;      // size of v[]
  private int last;      // largest index for saved values, -1 means no values saved yet
  private T[]  value;    // vector value array
	
  @SuppressWarnings({"unchecked"})
  public Vector(int n) {  // create an array with n cells
	size = n;
	value = (T[])new Object[size];
	last = -1;  
  }
	
  public Vector() {  // by default, create an array with 10 cells
	this(10);
  }
	
  public T get(int i) {  // return the i-th value in the array; i=0 for the first value
    if (i < 0 || i > last) 
	  return null;
	else
	  return value[i];
  }
	
  public void add(T v) {  // add value v to the immediate right of the right-most value
	if (last == size-1) grow(2*size);   // no more space for v, extend the vector length
	value[++last] = v;
  }
	
  public void add(int i, T v) {  // add value v in value[i], overriding if there was value in value[i]
	if (i > size-1) grow(2*i);
	if (i > last) last = i;
	value[i] = v;
  }
	
  @SuppressWarnings({"unchecked"})
  private void grow(int newSize) {  // increase size of value[] to newSize
	T[] value2 = (T[])new Object[newSize];
	for (int i = 0; i < size; i++) value2[i] = value[i];
	value = value2;
	size = newSize;
  }
	
  public static void main(String[] args) {
	Vector<Integer> v = new Vector<Integer>(5);
	for (int i = 0; i <= 100; i++) v.add(i);
	v.add(3, 33);
	for (int i = 0; i <= 100; i++) 
	  System.out.printf("v[%d]=%d ", i, v.get(i));
  }
}