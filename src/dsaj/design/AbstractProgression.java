package dsaj.design;

/** Generates a simple progression. By default: 0, 1, 2, ... */
public abstract class AbstractProgression {

  // instance variable
  protected long current;

  /** Constructs a progression starting at zero. */
  public AbstractProgression() { this(0); }

  /** Constructs a progression with given start value. */
  public AbstractProgression(long start) { current = start; }

  /** Returns the next value of the progression. */
  public long nextValue() {                      // this is a concrete method
    long answer = current;
    advance();    // this protected call is responsible for advancing the current value
    return answer;
  }

  /** Utility that prints the next n values of the progression. */
  public void printProgression(int n) {          // this is a concrete method
    System.out.print(nextValue());               // print first value without leading space
    for (int j=1; j < n; j++)
      System.out.print(" " + nextValue());       // print leading space before others
    System.out.println();                        // end the line
  }

  /** Advances the current value to the next value of the progression. */
  protected abstract void advance();        // notice the lack of a method body
}
