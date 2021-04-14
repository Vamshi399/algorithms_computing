package graph;

//represent sparse graph with edge list
//input format: 
//line 1:  n m   // n is node number, and m is number of edges that follow (may not list all symmetric cases)
//each of following m lines: a b w  // (a, b) and (b, a) have weight w
import java.io.*;
import java.util.*;

public class GraphSparse implements Graph {
  private int n;      // nbr of nodes
  private int[][] w;    // w[i][j] adjacency matrix, weight from i to j, 0 for no connection

  public GraphSparse(String fileName) {
    try {
      Scanner in = new Scanner(new File(fileName));
      // Read vertex number, and create the adjacency array w for the graph
      n = in.nextInt(); 
      w = new int[n][n];
      int edgeNbr = in.nextInt();
      for (int i = 0; i < edgeNbr; i++) {
        int from = in.nextInt();
        int to = in.nextInt();
        int weight = in.nextInt();
        w[from][to] = weight;  
      }
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

  public static void main(String[] args) {
    GraphSparse g;
    if (args.length == 0) {
      System.out.println("Usage: java GraphSparse graphFileName");
      System.out.println("File graph5sparse.txt is used for demo");
      g = new GraphSparse("graph5sparse.txt");
    }
    else
      g = new GraphSparse(args[0]);
    g.print("Input");
  }
}