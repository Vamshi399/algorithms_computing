public class HashTableString {
  private int size;
  private HashEntry[] hashtable;
  
  public class HashEntry {
    String key;
    String value;
    HashEntry next;
    
    public HashEntry(String key, String value) {
      this.key = key;
      this.value = value;
      this.next = null;
    }
    
    public HashEntry(String key, String value, HashEntry next) {
      this(key, value);
      this.next = next;
    }
    
    @Override
    public String toString() { return "[" + key + ", " + value + "]"; }
  }
  
  @SuppressWarnings({"unchecked"})
  public HashTableString(int size) {
    this.size = size;
    this.hashtable = new HashEntry[size];
  }
  
  public HashTableString() { this(16); }

  public void put(String key, String value) { // insert value associated with key
    int hash = getHash(key);
    if (hashtable[hash] == null) {
      hashtable[hash] = new HashEntry(key, value);
      return;
    }
    // hashtable[hash] already has value chain, add entry to this value chain
    HashEntry p = hashtable[hash];
    while (p.key.compareTo(key) != 0 && p.next != null) // find an entry with matching key
      p = p.next;
    if (p.key.compareTo(key) == 0) 
      p.value = value; // new value overrides old one with the same key
    else 
      p.next = new HashEntry(key, value);
  }
  
  public String get(String key) { // return value associated with key
    int hash = getHash(key);
    if (hashtable[hash] == null) return null;
    HashEntry p = hashtable[hash];
    while (!p.key.equals(key) && p.next != null) p = p.next;
    if (p == null) return null; // key is not in the hashtable
    return p.value;
  }
    
  private int getHash(String key) { // map a key to a hash table linked list of HashEntries
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
  
  private static void log(String msg) {
    System.out.println(msg);
  }

  public static void main(String[] args) {
    HashTableString hashTable = new HashTableString();
    // Put in some key values.
    for (int i=0; i<30; i++) {
      String key = String.valueOf(i);
      hashTable.put(key, key);
    }
    // Print the HashTable structure
    log("****   HashTable  ***");
    log(hashTable.toString());
    log("\nValue for key(20) : " + hashTable.get("20") );
  }
}
