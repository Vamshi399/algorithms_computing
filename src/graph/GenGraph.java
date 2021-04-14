package graph;

// Generate random symmetric graphs
// Invocation: java GenGraph vertexNbr outputFileName
import java.io.*;
import java.util.Random;

public class GenGraph {
  static int vertexNbr = 10;
  static int expectedDegree = 5; // a vertex/node on average connects to how many other vertices
  static int weightRange = 6; // edge weights are in range [0, weightRange)
  static String outputFileName = "graph10.txt";         // Default output file name
  static Random r = null;
  
  public static void main(String args[]) {
    System.out.println("Usage:  java GenGraph  [vertexNbr]  [outputFileName]");
    if (args.length > 0) 
      vertexNbr = Integer.parseInt(args[0].trim());     // Get vertex number from command-line
    // If vertexNbr is odd, promote it to the next even integer    
    if (2*(vertexNbr/2) != vertexNbr)           
      vertexNbr++;     
    if (args.length > 1)
      outputFileName = args[1];                         // Get file name from command-line
                
    System.out.println("Uing default vertext number " + vertexNbr + " and output file name " + outputFileName);
                
    // Calculate the expected number of edges incident to each vertex
    expectedDegree = vertexNbr/2;  

    long randomSeed = System.currentTimeMillis();       // Get milliseconds from 12AM 1970
    r = new Random(randomSeed);   // Use current time as random generator seed
    PrintWriter pw = null;
    File file = new File(outputFileName);
    /*
    // Use the following code if you don't want to rewrite existing files
    if (file.exists()) {
      System.out.println("File " + outputFileName + " already exists,"
                         + " delete or rename it, rerun the program");
      System.exit(0);
    }
    */
    try {
      pw = new PrintWriter(new FileOutputStream(file), true);
      genRandomGraph(pw, vertexNbr, expectedDegree);   // Generate and write out random graph
    }
    catch (IOException e) {
      System.out.println(e.getMessage());
    }
    finally {
      if (pw != null) pw.close();
    }
  }

  // Write to stream the upper-right corner data of a random symmetric graph with 
  // vertexNbr vertices, each vertex has on the average expectedDegree neighbors,
  // and each edge has on the average weightRange/2 random weight.
  static void genRandomGraph(PrintWriter out, int vertexNbr, int expectedDegree) {
    double p =((double)expectedDegree)/(vertexNbr - 1); // Probability to generate an edge
    try {
      out.println(vertexNbr);
      // Generate random edges
      for (int i = 1; i < vertexNbr; i++) { 
        for (int j = i; j < vertexNbr; j++) { 
          int value = 0;
          if (r.nextDouble() < p) // Generate an edge with probability p
            value = r.nextInt(weightRange);
          out.print(value + " ");
        }
        out.println();
      }
    }
    catch (Exception e) {}
  }
}
