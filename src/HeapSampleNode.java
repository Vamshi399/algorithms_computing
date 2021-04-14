public class HeapSampleNode {
  public int key;
  public String value;
  
  public HeapSampleNode(int k, String v) {
    key = k;
    value = v;
  }
    
  public int compareTo(HeapSampleNode n) {
    if (key == n.key) return 0;
    else if (key > n.key) return 1;
    else return -1;
  }
    
  public String toString() { return "(" + key + ", " + value + ")"; }
}
