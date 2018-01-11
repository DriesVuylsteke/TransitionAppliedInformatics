package sets;

import java.util.HashSet;
import java.util.Iterator;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * A class of unordered sets of elements of type T. A set cannot store the same
 * element several times.
 */
public class Set<T> implements UnorderedCollection<T> {
	private final HashSet<T> elements = new HashSet<T>();

	/**
	 * Return the number of occurrences of the given element in this set.
	 */
	@Basic
	@Override
	public int getNbOccurrencesOf(T element) {
		// Implementation irrelevant for the exercise.
		return (elements.contains(element)) ? 1 : 0;
	}

	/**
	 * Check whether this set can have the given number of occurrences of the given
	 * element.
	 * 
	 * @return this.getNbOccurencesOf(element) == 0
	 */
	@Override
	public boolean canHaveAsNbOccurrencesOf(T element, int number) {
		return element != null && getNbOccurrencesOf(element) == 0;
	}

	/**
	 * Return the total number of elements in this set.
	 */
	@Override
	public int getNbElements() {
		// Implementation irrelevant for the exercise.
		return elements.size();
	}

	/**
	 * Return an array containing all the elements of this unordered collection.
	 */
	@Override
	public Object[] toArray() {
		// Implementation irrelevant for the exercise.
		return elements.toArray();
	}

	/**
	 * Add the given element to this set.
	 */
	@Override
	public void add(T element) {
		assert element != null;
		assert canHaveAsNbOccurrencesOf(element, getNbOccurrencesOf(element) + 1);
		
		elements.add(element);
	}

	@Override
	public Iterator<T> iterator() {
		// Implementation irrelevant for the exercise.
		return elements.iterator();
	}
}
