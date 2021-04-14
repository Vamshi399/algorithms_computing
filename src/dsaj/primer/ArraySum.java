package dsaj.primer;

/**
 * Example of a static method that analyzes an array
 *
 * @author Vamshi Krishna Shanagonda
 */
public class ArraySum {

  public static double sum(double[] data) {
    double total = 0;
    for (int j=0; j < data.length; j++)        // note the use of length
      total += data[j];
    return total;
  }
}
