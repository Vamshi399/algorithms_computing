package graph;

//represent symmetric graph with right-upper triangle of the adjacency matrix
//input format: 
//line 1:  n
//following n-1 lines are the right-upper triangle of the adjacency matrix
//  excluding the main diagonal values, separating values with space
//0 for no connection
//only the right-upper triangle is stored for saving memory space
//good for dense symmetric graph
import java.io.*;
import java.util.*;

public class GraphSymmetric implements Graph {
  private int n;        // nbr of nodes
  private int adjSize;  // adj[] size
  private int[] adj;    // upper-right triangle of the adjacency matrix, adj[index(i, j)] is weight from i to j, 0 for no connection

  public GraphSymmetric(String fileName) {
    try {
      Scanner in = new Scanner(new File(fileName));
      // Read vertex number, and create the adjacency array for the graph
      n = in.nextInt(); 
      adjSize = n*(n-1)/2;
      adj = new int[adjSize];
      for (int i = 0; i < adjSize; i++) 
        adj[i] = in.nextInt();  
    }
    catch (Exception e) {}
  }

  public int size() { return n; } // number of nodes

//     0    1    2    3    4     5     6     7
//  0  -    0    1    2    3     4     5     6
//  1  -    -    7    8    9     10    11    12
//  2  -    -    -    13   14    15    16    17
//  3  -    -    -    -    18    19    20    21
//  4  -    -    -    -    -     22    23    24
//  5  -    -    -    -    -     -     25    26
//  6  -    -    -    -    -     -     -     27
//  7  -    -    -    -    -     -     -     -  
// Mapping index [i, j] to 1-D index k, displayed as 2-D value, where i < j.
// Justify the 2D to 1D index mapping i*n - (i+1)*i/2 + j - i - 1.
// i*n - (i+1)*i/2 + j - i - 1 = i*n -[(i+1)*(i+2)/2 - (i+1)] + [j-(i+1)].
// The first term i*n is # of 2D cells in rows above [i, j].
// The second term is # of empty cells in the left upper triangle in the first i rows.
// The first term minus the second term is the 1-D index of the first value to the right of the main diagonal on the i-th row.
// The 3rd term [j - (i+1)] is number of cells to the right of the first value to the right of the main diagonal on the i-th row.
// Example for n = 8: [3, 5] -> 3*8 - [(3+1)*(3+2)/2 - 4] + (5-4)
//                           -> 24 - 6 + 1 -> 19
// where 24 is # of cells above [3, 5], 6 is # of unused cells to the left of the first 3 rows; 24-6 is the 
// 1-D index of the first value (18) on the i-th row, and the 3rd term 5-4=1 is # of cells to the right of the first value (18)
// on the j-th row.
  private int index(int i, int j) { // i != j
    if (i > j) { int temp = i; i = j; j = temp; } // swap i and j
    return i*n - (i+1)*i/2 + j - i - 1; // i < j
  }

  // access adjacent matrix value at [i][j]
  public int w(int i, int j) {
    if (i == j) return 0;    // 0 means no connection
    return adj[index(i, j)];
  }

  // rewrite adjacent matrix at [i][j]
  public void setWeight(int i, int j, int value) {
   adj[index(i, j)] = value;
  }

  public void print(String label) { // print adjacent matrices, "-" for no connection
    System.out.println(label + ": ");
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        int temp = w(i, j);
        if (temp == 0) 
          System.out.printf("%4s", "-");
        else
          System.out.printf("%4d", temp);
      }
      System.out.println(); 
    }
    System.out.println(); 
  }

  public static void main(String[] args) {
    GraphSymmetric g;
    if (args.length == 0) {
      System.out.println("Usage: java GraphSymmetric symmetricGraphFileName");
      System.out.println("File graph5symmetric.txt is used for demo");
      g = new GraphSymmetric("graph5symmetric.txt");
    }
    else
      g = new GraphSymmetric(args[0]);
    g.print("Input");
  }
}