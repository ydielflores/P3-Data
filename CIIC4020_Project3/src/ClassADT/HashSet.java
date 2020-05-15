package ClassADT;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashSet<E> implements Set<E> {

	//private field Embedded HashTable
	private HashTableSC<E,E> EmbeddedHashTable;
	private HashFunction<E> hashFunction;

	public HashSet(int initialCapacity, HashFunction<E> hashFunction) {
		this.EmbeddedHashTable = new HashTableSC<E,E>(initialCapacity, hashFunction);
		this.hashFunction = hashFunction;
	}
	private class HashSetIterator<I> implements Iterator<I> {
		private int currentPosition;
		private List<E> ValuesInTable;

		public HashSetIterator() {
			this.currentPosition = 0;
			this.ValuesInTable = EmbeddedHashTable.getValues();
		}

		@Override
		public boolean hasNext() {
			return this.currentPosition < size();
		}

		@SuppressWarnings("unchecked")
		@Override
		public I next() {
			if (this.hasNext()) {
				I result = (I) this.ValuesInTable.get(this.currentPosition++);
				return result;
			}
			else
				throw new NoSuchElementException();				
		}
	}

	@Override
	public boolean add(E obj) {
		if(!this.isMember(obj)) {
			this.EmbeddedHashTable.put(obj, obj);
			return true;
		}			
		return false;
	}

	@Override
	public boolean isMember(E obj) {
		return this.EmbeddedHashTable.containsKey(obj);
	}

	@Override
	public boolean remove(E obj) {
		if(this.EmbeddedHashTable.remove(obj)!=null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		return this.EmbeddedHashTable.isEmpty();
	}

	@Override
	public int size() {
		return this.EmbeddedHashTable.size();
	}

	@Override
	public void clear() {
		this.EmbeddedHashTable.clear();
	}

	@Override
	public Set<E> union(Set<E> S2) {
		HashSet<E> United = new HashSet<E>(this.size(), this.hashFunction);
		for(E tempObj: this) {
			United.add(tempObj);
		}
		for(E tempObj: S2) {
			if(!United.isMember(tempObj))
				United.add(tempObj);
		}
		return United;
	}

	@Override
	public Set<E> difference(Set<E> S2) {
		HashSet<E> United = new HashSet<E>(this.size(), this.hashFunction);
		for(E tempObj: this) {
			if(!S2.isMember(tempObj)) {
				United.add(tempObj);
			}
		}
		return United;
	}

	@Override
	public Set<E> intersection(Set<E> S2) {
		return this.difference(this.difference(S2));
	}

	@Override
	public Iterator<E> iterator(){
		return new HashSetIterator<E>();
	}
	public void print(PrintStream out) {
		this.EmbeddedHashTable.print(out);
	}

	@Override
	public boolean isSubSet(Set<E> S2) {
		
		for(E tempObj: this) {
			if(!S2.isMember(tempObj)) {
				return false;
			}
		}
		return true;
	}

}