package exam.game;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of backpacks involving a contents. *
 * 
 * @invar ... | for each item in Item: | if (this.hasAsItem(item)) | then
 *        this.canHaveAsItem(item) && (item.getHolder() == this)
 */
public class BackPack extends Item {

	private Set<Item> content = new HashSet<>();

	public BackPack(int i) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Check whether this backpack can have the given identification as its
	 * identification.
	 * @return | result == (identification > 0) && (identification % 2 == 0)
	 */
	@Override
	@Raw
	public boolean canHaveAsIdentification(long identification) {
		return identification > 0 && identification % 2 == 0;
	}

	/** * Check whether this backpack has the given item as one of its items. 
	 * param item: the item to be checked
	 * */
	@Basic
	@Raw
	public boolean hasAsItem(Item item) {
		return content.contains(item);
	}

	/** Return the number of items in this backpack. * * @return ... */
	public int size() {
		return content.size();
	}

	/**
	 * * Check whether this backpack can have the given item as * one of its items.
	 * @param item: the item to be checked
	 * @return ...
	 */
	@Raw
	public boolean canHaveAsDirectItem(Item item) {
		return (item != null);
	}

	/**
	 * Add the given item to this backpack.
	 * @param item: an item to be added to the backpack
	 * @throws IllegalArgumentException
	 *             | !canHaveAsDirectItem(item)
	 * @throws NullPointerException
	 *             | item == null
	 * @post the new backpack contains the item | new.hasAsItem(item)
	 * @post the new item has the backpack as holder | (new item).getHolder() ==
	 *       this
	 */
	public void addAsItem(Item item) throws NullPointerException, IllegalArgumentException { // throws {
		if (item == null)
			throw new NullPointerException();
		if (!canHaveAsDirectItem(item))
			throw new IllegalArgumentException();
		content.add(item);
		item.setHolder(this);
	}

	/**
	 * Add to this backpack all items of the given collection * that fit into this
	 * backpack.
	 * @param items: a collection of items to be added to the backpack
	 * 
	 * 
	 * @post all the items in the items collection have been added to the backpack
	 *       if they fit in the backpack
	 *	| for each item in items
	 *	| if(this.canHaveAsDirectItem && item != null)
	 		then new.contains(item)
	 */
	public void addAllItems(Collection<? extends Item> items) {
		if (items == null)
			return;
		for (Item i : items) {
			try { // Try-catch to not stop the loop when an invalid item is added (this item is skipped this way)
				addAsItem(i);
			} catch (NullPointerException | IllegalArgumentException e) {}
		}
	}

	/**
	 * Return the set of all items in this backpack that satisfy the given
	 * predicate.
	 * @param predicate: the predicate that has to be satisfied
	 * @pre ... | predicate != null
	 */
	public Set<Item> getAllItemsSatisfying(Predicate<Item> predicate) {
		Set<Item> satisfyingItems = new HashSet<>();
		for(Item i : content) {
			if(predicate.test(i))
				satisfyingItems.add(i);
		}
		return satisfyingItems;
	}

	/**
	 * Return a stream containing all the items in this backpack.
	 */
	public Stream<Item> stream() {
		return null; // Details are not relevant for the exercise. }
	}
}
