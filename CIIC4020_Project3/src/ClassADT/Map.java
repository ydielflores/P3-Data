package ClassADT;

import java.io.PrintStream;

/**
 * This class represents a binary tree node with key, value,
 * left child, right child, and parent.
 * @author Juan O. Lopez 
 *
 * @param <K> Generic type for the key of the values to be stored in the map
 * @param <V> Generic type for the values to be stored in the map
 */
public interface Map<K, V> {

	V get(K key);
	void put(K key, V value);
	V remove(K key);
	boolean containsKey(K key);
	List<K> getKeys();
	List<V> getValues();
	int size();
	boolean isEmpty();
	void clear();
	void print(PrintStream out); /* For debugging purposes */
}