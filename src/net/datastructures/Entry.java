package net.datastructures;

/**
 * Interface for a key-value pair.
 *
 * @author Vamshi Krishna Shanagonda
 */
public interface Entry<K,V> {
  /**
   * Returns the key stored in this entry.
   * @return the entry's key
   */
  K getKey();

  /**
   * Returns the value stored in this entry.
   * @return the entry's value
   */
  V getValue();
}
