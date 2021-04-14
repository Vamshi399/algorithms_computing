package graph;

public class GraphShortestPath {
  private Graph g;
  private int n;           // node number
  private int[][] path;    // path[i][j]: the minimal edge weight total (length) on a path from i to j
  private int[][] bridge;  // bridge[i][j] is a node on shortest path from i to j
  final int INF = 999;     // impossible large edge/path weight/length for no connection, same as 0 in graph file
  
  public GraphShortestPath(String fileName) { 
    //g = new GraphSymmetric(fileName);
    //g = new GraphSparse(fileName);
    g = new GraphDense(fileName);
    g.print("Input Graph");
    n = g.size();
    path = new int[n][n];
    bridge = new int[n][n];
    shortestPath(g);
    printMatrix(path, "Paths");
    printMatrix(bridge, "Bridges");
    printAllPaths();
  }
  
  // The Floyd–Warshall All-Pairs Algorithm
  // https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm
  void shortestPath(Graph g) { // calculate shortest path between all pairs of (i, j)
    for (int i = 0; i < n; i++) // make path/bridge def true for path length being 1
      for (int j = 0; j < n; j++) {
        bridge[i][j] = path[i][j] = INF;
        int temp = g.w(i, j);
        if (temp > 0) path[i][j] = temp;  // g.w(i, j) == 0 iff there is no edge (i, j)
      }
    for (int k = 0; k < n; k++) { // can i passing k to j reduce the current path from i to j?
      for (int i = 0; i < n; i++) {
        if (i == k) continue;
        for (int j = 0; j < n; j++) { // for all combinations of different k, i and j
          if ((j == i) || (j == k)) continue;
          int temp = path[i][k] + path[k][j];   // path length for i->k->j
          if (temp < path[i][j]) {
            path[i][j] = temp;
            bridge[i][j] = k;  // k is on shorter path from i to j 
          }
        }
      }
    }
  }
  
  public void printMatrix(int[][] m, String label) { // utility for printing path[][] and bridge[][]
    System.out.println(label + ":");
    for (int i = 0; i < m.length; i++) {
      for (int j = 0; j < m[0].length; j++)
        if (m[i][j] == INF) 
          System.out.printf("%4s", "-");
        else
          System.out.printf("%4d", m[i][j]);
      System.out.println();
    }
    System.out.println();    
  }
  
  void printAllPaths() {
    for (int i = 0; i < n-1; i++)
      for (int j = i+1; j < n; j++)
        if (path[i][j] < INF) printPath(i, j);
  }
  
  void printPath(int i, int j) { // print shortest path from i to j
    System.out.print("Path " + i + " -> " + j + ": length = " + path[i][j] + ", path: " + i + " ");
    printPathRecursive(i, j);
    System.out.println();
  }
  
  void printPathRecursive(int i, int j) { // print shortest path from i to j directly or through bridge nodes
    if (bridge[i][j] == INF) // i is directly connected to j
      System.out.print(j + " ");
    else {
      printPathRecursive(i, bridge[i][j]); // the shortest path from i to j goes through bridge[i][j]
      printPathRecursive(bridge[i][j], j);
    }
  }
  
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Usage: java GraphShortestPath graphFileName");
      System.exit(-1);
    }
    new GraphShortestPath(args[0]);
  }
}
