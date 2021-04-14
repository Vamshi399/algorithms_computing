package oop;

public class TestCircle {
  public static void main(String[] args) {
    Circle0 x = new Circle0(1.0);
    System.out.println(x);
    Circle0 y = new Circle0();
    System.out.println(y);
    y.setRadius(2.0);
    System.out.println(y + " has area " + y.area());
  }
}

class Circle0 {
  private double radius;
  // deine PI as nickname for constant 3.14
  private final double PI = 3.14;  

  // Constructor 1
  public Circle0(double radius) {
    System.out.println("Enter constructor Circle0(" + radius + ")");    
    this.radius = radius; // this = the current object  
  } 

  // Constructor 2: the default constructor
  public Circle0() {
    this(0.0); // call constructor 1; this = class name
  }
  
  // Setter method
  public void setRadius(double radius) {
    this.radius = radius;
  }

  // Getter method
  public double getRadius() {
    return radius;
  }

  // Calculate area of the circle
  public double area() {
    return PI * radius * radius;
  }

  // Define how to print Circle objects
  public String toString() {
    return "Circle0(" + radius + ")";
  }
}
