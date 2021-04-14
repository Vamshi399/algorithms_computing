package oop; //inheritance;

public class Inheritance {
  public static void main(String[] args) {
    Cat1 c = new Cat1("Cathy");
    Dog1 d = new Dog1("Champion");
    c.talk();
    d.talk();
    System.out.println("I am " + c + ", my name is " + c.getName());
    System.out.println("I am " + d + ", my name is " + d.getName());
    // polymorphism demo: the version of toString() used is the one 
    // declared in the subclasses
    Pet1[] p = new Pet1[2];
    p[0] = c;
    p[1] = d;
    for (int i = 0; i < p.length; i++)
      System.out.println("I am " + p[i] + ", my name is " + p[i].getName());
    // p[0].talk(); // this will fail, since Pet1 has no method talk()
    for (int i = 0; i < p.length; i++) {
      if (p[i] instanceof Cat1) // Is p[i] an instance of Cat1?
        ((Cat1)p[i]).talk();    // Cast p[i] back into a reference for Cat1
    }
  }
}

class Pet1 {
  private String name;
  
  public Pet1(String n) {
    this.name = n;
    System.out.println("Pet1(" + n + ")");
  }
  
  public String getName() {
    return name;
  }
  
  public String toString() {
    return "Pet1(" + name + ")";
  }
}

class Cat1 extends Pet1 {
  public Cat1(String name) {
    super(name);  // call superclass constructor
    System.out.println(this + ", I am also " + super.toString());
  }

  public void talk() {
    System.out.println("I like fish.");
  }
  
  public String toString() { // override toString() method in superclass
    return "Cat1(" + getName() + ")";
  }
}

class Dog1 extends Pet1 {
  public Dog1(String name) {
    super(name);  // call superclass constructor
    System.out.println(this + ", I am also " + super.toString());
  }

  public void talk() {
    System.out.println("I like sports.");
  }
  
  public String toString() { // override toString() method in superclass
    return "Dog1(" + getName() + ")";
  }
}
