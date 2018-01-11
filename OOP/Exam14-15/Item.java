package exam.game;

import be.kuleuven.cs.som.annotate.*;

/**
 * * A class of items involving an identification and a holder. * * @invar …|
 * canHaveAsidentification(getIdentification())
 */
public abstract class Item {
	@Basic
	@Raw
	@Immutable
	public long getIdentification() {
		return this.identification;
	}
	
	/**
	 * * Check whether this item can have the given identification as 
	 * its identification. 
	 * 
	 * @return 
	 * 		| result == (identification > 0)
	 */
	@Raw
	public abstract boolean canHaveAsIdentification(long identification);

	private final long identification = 0;

	/** * Return the holder of this item, if any. */
	@Basic
	@Raw
	public BackPack getHolder() {
		return this.holder;
	}
	
	@Basic
	@Raw
	public void setHolder(BackPack holder)
	{
		this.holder = holder;
	}

	private BackPack holder;
}
