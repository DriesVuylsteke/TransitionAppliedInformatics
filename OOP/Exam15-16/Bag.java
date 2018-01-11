package sets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Consumer;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * A class of unordered bags of elements of type T. A bag can store the same
 * element several times.
 */
public class Bag<T> implements UnorderedCollection<T> {
	// Some useful methods offered by the interface Map<K,V> :
	// boolean containsKey(Object key)
	// V get(Object key)
	// Set<K> keySet()
	// V put(K key, V value)
	// Collection<V> values()
	private final HashMap<T, Integer> elements = new HashMap<T, Integer>();

	/**
	 * Return the number of occurrences of the given element in this bag.
	 */
	@Basic
	@Override
	public int getNbOccurrencesOf(T element) {
		// Implementation irrelevant for the exercise.
		if (elements.containsKey(element))
			return elements.get(element);
		return 0;
	}

	/**
	 * Check whether this bag can have the given number of occurrences of the given
	 * element.
	 */
	@Override
	public boolean canHaveAsNbOccurrencesOf(T element, int number) {
		return element != null;
	}

	/**
	 * Return the total number of elements in this bag.
	 */
	@Override
	public int getNbElements() {
		// Implementation irrelevant for the exercise.
		int result = 0;
		for (Integer nbOccs : elements.values())
			result += nbOccs;
		return result;
	}

	/**
	 * Return an array containing all the elements of this unordered collection.
	 */
	@Override
	public Object[] toArray() {
		// Implementation irrelevant for the exercise.
		Object[] result = new Object[this.getNbElements()];
		int index = 0;
		for (T element : elements.keySet())
			for (int i = 0; i < this.getNbOccurrencesOf(element); i++)
				result[index++] = element;
		return result;
	}

	/**
	 * Add the given element to this bag.
	 */
	@Override
	public void add(T element) {
		assert element != null;
		assert canHaveAsNbOccurrencesOf(element, getNbOccurrencesOf(element) + 1);
		
		elements.put(element, getNbOccurrencesOf(element) + 1);
	}

	@Override
	public Iterator<T> iterator() {
		ArrayList<T> list = new ArrayList<T>();
		elements.keySet().stream().forEach(new Consumer<T> () {
			@Override
			public void accept(T elem) {
				for(int i = 0; i < elements.get(elem); i++)
					list.add(elem);
			}
		});
		return list.iterator();
	}
}
