package exam.game;

import be.kuleuven.cs.som.annotate.*;

/** class of potions. */
public class Potion extends Item {
	/**
	 * Check whether this potion can have the given identification as * its
	 * identification. 
	 * 
	 * @return ... 
	 * 	| (identification % 2 == 1)
	 */
	@Override
	@Raw
	public boolean canHaveAsIdentification(long identification) {
		return identification > 0 && identification % 2 == 1;
	}

}
