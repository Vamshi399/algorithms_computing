package graph;

//represent dense graph with complete adjacency matrix
//input format: 
//line 1:  n
//each of following n lines is a row of n values of the adjacency matrix
//0 for no connection
import java.io.*;
import java.util.*;

public class GraphDense implements Graph {
  private int n;      // nbr of nodes
  private int[][] w;  // w[i][j] adjacency matrix, weight from i to j, 0 for no connection

  public GraphDense(String fileName) {
    try {
      Scanner in = new Scanner(new File(fileName));
      // Read vertex number, and create the adjacency array w for the graph
      n = in.nextInt(); 
      w = new int[n][n];
      for (int i = 0; i < n; i++) 
        for (int j = 0; j < n; j++) 
          w[i][j] = in.nextInt();  
    }
    catch (Exception e) {}
  }

  public int size() { return n; } // number of nodes

  public int w(int i, int j) { // edge weight from node i to node j
    return w[i][j];
  }

  public void setWeight(int i, int j, int v) { // set new weight value for edges (i, j)
    w[i][j] = v;
  }

  public void print(String label) { // print adjacent matrices, "-" for no connection
    System.out.println(label + ": ");
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) 
          if (w[i][j] == 0) 
            System.out.printf("%4s", "-");
          else
            System.out.printf("%4d", w[i][j]);
        System.out.println(); 
      }
    System.out.println(); 
  }

  public static void main(String[] args) { // the graph must be symmetric
    GraphDense g;
    if (args.length == 0) {
      System.out.println("Usage: java GraphDense graphFileName");
      System.out.println("File graph5dense.txt is used for demo");
      g = new GraphDense("graph5dense.txt");  
    }
    else
      g = new GraphDense(args[0]);
    g.print("Input");
  }
}