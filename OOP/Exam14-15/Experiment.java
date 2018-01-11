package exam.game;

import java.util.*;
import java.util.function.Predicate;

import exam.game.*;

public class Experiment {
	public static void main(String[] args) {
		BackPack thePack = new BackPack(12);
		// Items being added to the backpack here. 
		// Details are not relevant for the
		// exercise.
		// Write instructions to fill a variable named 'result' with all 
		// the potions
		// in 'thePack' that have an identification that ends 
		// with the digit 0.
		// You must use an anonymous class at this point.
		
		Set<Item> result = thePack.getAllItemsSatisfying(new Predicate<Item>() {
			@Override
			public boolean test(Item i) {
				return i.getIdentification() % 10 == 0;
			}
		});
		
		// Write instructions to print out the largest identification 
		// among all the
		// backpacks stored in thePack that store at least 
		// at least three items. 
		//
		// The following methods can be applied to streams: 
		// Stream<T>
		// filter(Predicate<? super T> predicate) 
		// <R> Stream<R> map(Function<? super
		// T,? extends R> mapper) 
		// Optional<T> reduce(BinaryOperator<T> accumulator)
		
		System.out.println((thePack.stream()
			.filter(i -> (i instanceof BackPack) && ((BackPack)i).size() >= 3)
			.map(i -> i.getIdentification())
			.reduce((c,n) -> c > n ? c : n)
			.get()));
	} // End of main method.

} // End of class Experiment
