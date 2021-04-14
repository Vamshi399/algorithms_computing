package oop;

public class StaticDataMember {
  public static void main(String[] args) {
    Circle3 x = new Circle3(2);
    System.out.println("x: " + x);
    Circle3 y = new Circle3(3);
    System.out.println("x: " + x);
    System.out.println("y: " + y);
    System.out.println("x.nbrOfCircles: " + x.nbrOfCircles);
    System.out.println("y.nbrOfCircles: " + y.nbrOfCircles);
    System.out.println("Circle3.nbrOfCircles: " + Circle3.nbrOfCircles);
  }
}

class Circle3 {
  private int radius = 0;
  static int nbrOfCircles = 0;
  
  public Circle3(int radius) {
    this.radius = radius;
    nbrOfCircles++;
  }
  
  public int getRadius() {
    return radius;
  }
  
  public String toString() {
    return "Circle3(" + radius + ":" + nbrOfCircles + ")";
  }
}