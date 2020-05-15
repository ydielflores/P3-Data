package ClassADT;

public class HashTableOAFactory<K, V> implements HashTableFactory<K, V> {

	@Override
	public Map<K, V> getInstance(int initialCapacity) {
		return new HashTableOA<K, V>(initialCapacity, new SimpleHashFunction<K>());
	}

}