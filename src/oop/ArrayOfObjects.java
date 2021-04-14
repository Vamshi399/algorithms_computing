package oop;

public class ArrayOfObjects {
  public static void main(String[] args) {
    Circle2[] x = new Circle2[5];  // create array of 5 Circle reference variables
    for (int i = 0; i < x.length; i++)
      x[i] = new Circle2(i); // create Circle objects
    printArray("x[]: ", x);
    
    // making a copy of array of objects in c; array c[] and x[] are separate
    Circle2[] c = new Circle2[x.length];
    for (int i = 0; i < x.length; i++)
      c[i] = x[i].clone();
      
    Circle2[] y = x;    // y is an alias of x; they refer to the same array.
    y[0].setRadius(6);
    System.out.println("After setting y[0] = 6");
    printArray("x[]: ", x);
    printArray("y[]: ", y);
    printArray("c[]: ", c);
  }
  
  static void printArray(String title, Circle2[] a) {
  System.out.print(title);
    for (int i = 0; i < a.length; i++)
      System.out.print(a[i] + " ");
    System.out.println();
  }
}

class Circle2 implements Cloneable {
  private int radius = 0;
  
  public Circle2(int radius) {
    this.radius = radius;
  }
  
  public void setRadius(int r) {
    radius = r;
  }
  
  public String toString() {
    return "Circle2(" + radius + ")";
  }
  
  public Circle2 clone() {
    Circle2 c = null;
    try {
      c = (Circle2)super.clone();
      c.radius = radius;  // not necessary; done in super.clone() already
    }
    catch(Exception e){}
    return c;
  }
}