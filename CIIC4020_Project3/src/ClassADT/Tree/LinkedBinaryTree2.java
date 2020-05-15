package ClassADT.Tree;

public class LinkedBinaryTree2<E extends Comparable<E>> extends LinkedBinaryTree<E> {
	
	public void insert(E e) { 
		if (isEmpty())
			addRoot(e); 
		else
			recInsert(root(), e); 
	}

	private void recInsert(Position<E> r, E e) {
		//int c = e.compareTo(r.getElement()); Commented this to see if it was necessary to create an int.
		System.out.println(e.compareTo(r.getElement())); // I always forget how compareTo works so this helped me understand better.
		if (e.compareTo(r.getElement()) < 0) 
			if (!hasLeft(r))
				addLeft(r, e); 
			else 
				recInsert(left(r), e); 
		else 
			if (!hasRight(r))
				addRight(r, e); 
			else 
				recInsert(right(r), e); 		
	}

}