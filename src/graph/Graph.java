package graph;

public interface Graph {
  int size(); // number of nodes
  int w(int i, int j); // edge weight from node i to node j 
  void setWeight(int i, int j, int v); // set new weight value for edge (i, j)
  void print(String label); // print adjacent matrices, "-" for no connection
}
