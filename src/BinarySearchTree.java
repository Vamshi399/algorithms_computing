// Ref: http://cslibrary.stanford.edu/110/BinaryTrees.html#java
// binary search tree of values with comparable type T in sorted inorder.
// O(n) as worst-case time complexity and O(log n) as best-case time complexity
// for insert/lookup/delete a value in a tree with n values.
import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> {
  private Node root = null;
  
  public class Node {
    Node left;
    Node right;
    T data;
    
    Node(T v) {
      data = v; left = right = null;
    }
  }
  
  // return node having v as its value in the sorted binary tree
  public Node lookup(T v) {
    return lookup(root, v);
  }
  
  // private recursive worker for lookup(v)
  private Node lookup(Node node, T v) {
    if (node == null) return null;
    if (v.compareTo(node.data) == 0) return node;  // v == node.data
    if (v.compareTo(node.data) < 0) return lookup(node.left, v); // v < node.data
    return lookup(node.right, v);
  }
  
  // Insert v in the binary tree sorted by inorder
  public void insert(T v) {
    root = insert(root, v);
  }
  
  // private recursive worker for insert(v)
  private Node insert(Node node, T v) {
    if (node == null) return new Node(v);
    if (v.compareTo(node.data) < 0) 
      node.left = insert(node.left, v);
    else 
      node.right = insert(node.right, v);
    return node;
  }
  
  public int size() {
    return size(root);
  }
  
  private int size(Node node) {
    if (node == null) return 0;
    return size(node.left) + 1 + size(node.right);
  }
  
  public int maxDepth() {
    return maxDepth(root);
  }
  
  private int maxDepth(Node node) {
    if (node == null) return 0;
    return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
  }
  
  public T minValue() {
    return minValue(root);
  }
  
  private T minValue(Node node) {
    while (node.left != null) node = node.left;
    return node.data;
  }
  
  public void printInorder() {
    printInorder(root);
    System.out.println();
  }
  
  private void printInorder(Node node) {
    if (node == null) return;
    printInorder(node.left);
    System.out.print(node.data + " ");
    printInorder(node.right);
  }
  
  public void printPostorder() {
    printPostorder(root);
    System.out.println();
  }
  
  private void printPostorder(Node node) {
    if (node == null) return;
    printPostorder(node.left);
    printPostorder(node.right);
    System.out.print(node.data + " ");
  }
  
  public void printPreorder() {
    printPreorder(root);
    System.out.println();
  }
  
  private void printPreorder(Node node) {
    if (node == null) return;
    System.out.print(node.data + " ");    
    printPreorder(node.left);
    printPreorder(node.right);
  }
  
  public void deleteNode(T v) { // delete node with value v
    root = deleteNode(root, v);
  }
  
  private Node deleteNode(Node node, T v) {
    if (node == null) return null;
    if (v.compareTo(node.data) < 0) { // v in left tree
      node.left = deleteNode(node.left, v);
      return node;
    }
    if (v.compareTo(node.data) == 0) { // delete this node
      // Leaf node deletion case
      if (node.left == null && node.right == null) node = null;
      else if (node.left == null) // node has only right child
        node = node.right;
      else if (node.right == null) // node has only left child
        node = node.left;
      else { // node to be deleted has two children
        Node successor = smallest(node.right);
        node.data = successor.data;
        node.right = deleteNode(node.right, successor.data);
      }
      return node;
    }
    node.right = deleteNode(node.right, v); // v in right tree
    return node;
  }
  
  private Node smallest(Node node) { // return the node with the smallest value under this input node
    if (node.left == null)
      return node;
    else
      return smallest(node.left);
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter list of strings in insertion order, or hit Enter for using the default input");
    String input = sc.nextLine();
    if (input.trim().length() == 0) input = "d b f a c e g";
    String[] v = input.split("\\s+"); ;
    BinarySearchTree<String> tree = new BinarySearchTree<String>();
    System.out.print("Insert order:\t");
    for (String s: v) { System.out.print(s + " "); tree.insert(s); } 
    System.out.println();
    do {
      System.out.print("Preorder:\t");
      tree.printPreorder();
      System.out.print("Inorder:\t");
      tree.printInorder();
      System.out.print("Postorder:\t");
      tree.printPostorder();
      System.out.println("Enter a tree node name to delete it, or enter nothing to exit the execution.");
      input = sc.nextLine();
      if (input.trim().length() == 0) break;
      String value = input.split("\\s+")[0];
      if (tree.lookup(value) == null) {
        System.out.printf("\"%s\" is not value of a tree node\n", value);
        continue;
      }
      tree.deleteNode(value);
      System.out.println("Delete " + value);
    } while (true);
  }    
}
