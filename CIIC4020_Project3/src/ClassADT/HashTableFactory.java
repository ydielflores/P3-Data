package ClassADT;

public interface HashTableFactory<K, V> {

	Map<K, V> getInstance(int initialCapacity);

}