//  This program presents some features of Java interface.
//  Java interface consists of a set of public method signatures with no implementations.
//  A class implementing an interface means the class promises to implement 
//  all methods declared in the interface.
//  The name of an interface can be used to declare variables that can hold references
//  to any objects instantiated from classes that implement the same interface; only
//  the methods listed in the interface can be called from such variables.
//  Interface is used for systematically processing objects of mixed classes
package oop;

public class InterfaceDemo {
  public static void main(String arg[]) {
    Pet2 pets[] = new Pet2[2]; // creates an array of 3 Pet2 references
    pets[0] = new Cat2("Pussy");    // since Cat2 implements interface Pet2
    pets[1] = new Dog2("Champion"); // since Dog2 implements interface Pet2
    
    for (int i = 0; i < pets.length; i++)
      pets[i].talk();
      
    Cat2 c = (Cat2)pets[0];  // cast a Pet2 type object to a Cat2 object
    c.hello();
    // pets[0].hello();    // this line must be commented off; pets[0] has type Pet2,
                           // only methods listed in interface Pet2 can be called from it.
  }
}

interface Pet2 {
  void talk();   // all pets must implement method talk()
}

class Cat2 implements Pet2 {
  String name;

  public Cat2(String name) {
    this.name = name;
  }
  
  public void talk() {  // this method is required by the interface Pet2
    System.out.println("I am cat " + name + ", meow.");
  }
  
  public void hello() { // this is a method not required by interface Pet2
    System.out.println("Cat " + name + " says hello to you.");
  }  
}

class Dog2 implements Pet2 {
  String name;

  public Dog2(String name) {
    this.name = name;
  }
  
  public void talk() {  // this method is required by the interface Pet2
    System.out.println("I am dog " + name + ", wow.");
  }
  
  public void hello() { // this is a method not required by interface Pet2
    System.out.println("Dog " + name + " says hello to you.");
  }  
}
