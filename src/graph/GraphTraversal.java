package graph;

//https://www.baeldung.com/java-depth-first-search
//https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/
//https://www.hackerearth.com/practice/algorithms/graphs/topological-sort/tutorial/
//https://www.geeksforgeeks.org/topological-sorting/
import java.util.*;

public class GraphTraversal {
  private Graph g;

  public GraphTraversal(String fileName) {
    g = new GraphSparse(fileName);
    depthFirstSearch(g);
    breadthFirstSearch(g);
    topologicalSort(g);
  }

  private void visit(int i) {
    System.out.print(i + " ");
  }

  private void depthFirstSearch(Graph g) {
    int n = g.size();
    boolean[] visited = new boolean[n]; // has been visited; avoid cycling or repeated visit
    Stack<Integer> stack = new Stack<Integer>();
    System.out.print("Depth-First-Search: ");
    for (int i = 0; i < n; i++) {
      if (visited[i]) continue;
      stack.push(i);
      while (!stack.isEmpty()) {
        int current = stack.pop();
        visit(current);
        visited[current] = true;
        for (int j = 0; j < n; j++) {
          if (g.w(current, j) > 0 && !visited[j])
            stack.push(j);
        }
      }
    }
    System.out.println();
  }

  private void breadthFirstSearch(Graph g) {
    int n = g.size();
    boolean[] visited = new boolean[n]; // has been visited; avoid cycling or repeated visit
    Queue<Integer> queue = new LinkedList<Integer>();
    System.out.print("Breadth-First-Search: ");
    for (int i = 0; i < n; i++) { 
      if (visited[i]) continue;
      queue.add(i);
      while (!queue.isEmpty()) {
        int current = queue.remove(); // remove and return the head element in the queue
        visit(current);
        visited[current] = true;
        for (int j = 0; j < n; j++) {
          if (g.w(current, j) > 0 && !visited[j])
          queue.add(j);
        }
      }
    }    
    System.out.println();
  }

  private void topologicalSortUtil(Graph g, int v, boolean[] visited, Stack<Integer> stack) {
    visited[v] = true;
    for (int j = 0; j < g.size(); j++)
      if ((g.w(v, j) > 0) && !visited[j])
        topologicalSortUtil(g, j, visited, stack);
    stack.push(v);
  }
 
  private void topologicalSort(Graph g) {
    int n = g.size();
    boolean[] visited = new boolean[n]; // has been visited; avoid cycling or repeated visit
    Stack<Integer> stack = new Stack<Integer>();
    for (int i = 0; i < n; i++) 
      if (!visited[i]) topologicalSortUtil(g, i, visited, stack);
    System.out.print("Topological Sort: ");
    while (!stack.isEmpty()) 
      System.out.print(stack.pop() + " ");
    System.out.println();  
  }
 
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Usage: java GraphTraversal sparseGraphFileName");
      System.out.println(" Demo: java GraphTraversal BS-CS-prerequisites.txt");
      new GraphTraversal("BS-CS-prerequisites.txt");
    }
    else
      new GraphTraversal(args[0]);
  }
}