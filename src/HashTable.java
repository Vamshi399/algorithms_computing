public class HashTable<K extends Comparable<K>, T> {
  private int size; 
  private HashEntry[] hashtable;
 
  public class HashEntry<K extends Comparable<K>, T> {
    K key;
    T value;
    HashEntry next;
    
    public HashEntry(K key, T value) {
      this.key = key;
      this.value = value;
      this.next = null;
    }
    
    public HashEntry(K key, T value, HashEntry next) {
      this(key, value);
      this.next = next;
    }
    
    @Override
    public String toString() { return "[" + key + ", " + value + "]"; }
  }
  
  @SuppressWarnings({"unchecked"})
  public HashTable(int size) {
    this.size = size;
    this.hashtable =  new HashEntry[size];
  }
  
  public HashTable() { this(16); }

  public void put(K key, T value) { // insert value associated with key
    int hash = getHash(key);
    if (hashtable[hash] == null) {
      hashtable[hash] = new HashEntry<K, T>(key, value);
      return;
    }
    // hashtable[hash] already has value chain, add entry to this value chain
    HashEntry p = hashtable[hash];
    while ((p.key.compareTo(key) != 0) && p.next != null) // find an entry with matching key
      p = p.next;
    if (p.key.compareTo(key) == 0) 
      p.value = value; // new value overrides old one with the same key
    else 
      p.next = new HashEntry<K, T>(key, value);
  }
  
  public T get(K key) { // return value associated with key
    int hash = getHash(key);
    if (hashtable[hash] == null) return null;
    HashEntry p = hashtable[hash];
    while ((p.key.compareTo(key) != 0) && p.next != null) p = p.next;
    if (p == null) return (T)null; // key is not in the hashtable
    return (T)p.value;
  }
    
  private int getHash(K key) { // map a key to the index of a hash table linked list of HashEntries
    return key.hashCode() % size;
  }

  @Override
  public String toString() {
    int bucket = 0;
    StringBuilder hashTableStr = new StringBuilder();
    for (HashEntry entry : hashtable) {
      if (entry == null) {
        continue;
      }
      hashTableStr.append("\n bucket[")
        .append(bucket)
        .append("] = ")
        .append(entry.toString());
      bucket++;
      HashEntry temp = entry.next;
      while(temp != null) {
        hashTableStr.append(" -> ");
        hashTableStr.append(temp.toString());
        temp = temp.next;
      }
    }
    return hashTableStr.toString();
  }
   
  public static void main(String[] args) {
    HashTable<Integer, String> hashTable = new HashTable<Integer, String>();
    for (int i = 0; i < 30; i++) {
      int key = i;
      hashTable.put(key, key + "-" + "value");
    }
    // Print the HashTable structure
    System.out.println("****   HashTable  ***");
    System.out.println(hashTable.toString());
    System.out.println("\nValue for key = 20 : " + hashTable.get(20) );
    System.out.println();
    HashTable<String, Integer> hashTable2 = new HashTable<String, Integer>();
    for (int i = 0; i < 30; i++) {
      String key = String.valueOf(i);
      hashTable2.put(key, 100+i);
    }
    // Print the HashTable structure
    System.out.println("****   HashTable  ***");
    System.out.println(hashTable2.toString());
    System.out.println("\nValue for key = \"20\" : " + hashTable2.get("20") );
  }
}
