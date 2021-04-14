public class Sorting {
  // swap values in a[i] and a[j]
  void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }
  
  void bubbleSort(int[] a) { 
    int n = a.length; 
    for (int i = 0; i < n-1; i++) 
      // move the largest value in a[0:n-i-1] in a[n-i-1]
      for (int j = 0; j < n-i-1; j++) 
        if (a[j] > a[j+1]) swap(a, j, j+1);
  } 
    
  void selectionSort(int[] a) {  
    int n = a.length; 
    int i, j, min_idx;  
  
    // One by one move boundary of unsorted subarray  
    for (i = 0; i < n-1; i++) {  
      // Find the minimum element in unsorted array  
      min_idx = i;  
      for (j = i+1; j < n; j++)  
        if (a[j] < a[min_idx])  
          min_idx = j;  
  
      // Swap the found minimum element with the first element  
      swap(a, min_idx, i);  
    }  
  }  

  void insertionSort(int[] a) {
    int n = a.length; 
    for (int i = 1; i < n; i++) { // insert a[i] in sorted a[0:i-1]
      int current = a[i];
      int j = i - 1;
      while(j >= 0 && current < a[j]) {
        a[j+1] = a[j];
        j--;
      }
      a[j+1] = current;
    }
  }
  
  // Quicksort starts
  // https://en.wikipedia.org/wiki/Quicksort  
  // https://en.wikipedia.org/wiki/Quicksort#/media/File:Quicksort-diagram.svg
  // let pivot = a[r], return i and a[] so 
  // a[l:i-1] < pivot, a[i] = pivot, a[i+1:r] >= pivot
  int partition(int a[], int l, int r) {
    int pivot = a[r];
    int i = l;  // a[l:i-1] < pivot; initially a[l:i-1] is empty since l>i-1
    for (int j = l; j <= r; j++) 
      if (a[j] < pivot)
        swap(a, i++, j);
    swap(a, i, r);
    return i;
  }
  
  public void quicksort(int a[], int l, int r) {
    if (l < r) {
      int p = partition(a, l, r);
      quicksort(a, l, p-1);
      quicksort(a, p+1, r);
    }
  }
  // Quicksort ends
  
  // MergeSort starts
  // https://www.baeldung.com/java-merge-sort
  public void mergeSort(int[] a, int n) {
    if (n < 2) return;
    int mid = n/2;
    int[] l = new int[mid];
    int[] r = new int[n - mid];
    for (int i = 0; i < mid; i++) l[i] = a[i];
    for (int i = mid; i < n; i++) r[i - mid] = a[i];
    mergeSort(l, mid);
    mergeSort(r, n - mid);
    merge(a, l, r, mid, n - mid);
  }
  
  void merge(int[] a, int[] l, int[] r, int left, int right) {
    int i = 0, j = 0, k = 0;
    while (i < left && j < right) { 
      if (l[i] <= r[j]) 
        a[k++] = l[i++];
      else 
        a[k++] = r[j++];
    }
    while (i < left) a[k++] = l[i++];
    while (j < right) a[k++] = r[j++];
  }
  // MergeSort ends

  void print(int[] v) {
    for (int i = 0; i < v.length; i++)
      System.out.print(v[i] + " ");
    System.out.println();
  }
  
  public Sorting(String[] args) {
    int[] a = {4, 2, 87, 42, 5, 64, 32, 22, 8, 34, 77, 35, 25};
    //quicksort(a, 0, a.length-1);
    System.out.print("Unsorted data: ");
    print(a);
    int[] b = java.util.Arrays.copyOf(a, a.length);
    bubbleSort(b);
    System.out.print("Data sorted by bubbleSort: ");
    print(b);
    b = java.util.Arrays.copyOf(a, a.length);
    selectionSort(b);
    System.out.print("Data sorted by selectionSort: ");
    print(b);
    b = java.util.Arrays.copyOf(a, a.length);
    insertionSort(b);
    System.out.print("Data sorted by insertionSort: ");
    print(b);    
    b = java.util.Arrays.copyOf(a, a.length);
    quicksort(b, 0, b.length-1);
    System.out.print("Data sorted by quicksort: ");
    print(b);
    b = java.util.Arrays.copyOf(a, a.length);
    mergeSort(b, b.length);
    System.out.print("Data sorted by mergeSort: ");
    print(b);
  }
  
  public static void main(String[] args) {
    new Sorting(args);
  }
}
