package diskItemsStart;

import java.math.BigInteger;

import be.kuleuven.cs.som.annotate.*;

/**
 *  A class of disk items.
 *  
 * @invar  The size of each disk item must be a valid size for any
 *         disk item.
 *       | isValidSize(getSize())
 * @invar  Each disk item must have a proper directory.
 *       | hasProperDirectory()
 */
public abstract class DiskItem {

	/**
	 * Initialize this new disk item with size and given directory.
	 *
	 * @param  size
	 *         The size for this new disk item.
	 * @param  directory
	 *         The directory for this new disk item.
	 * @post   If the given size is a valid size for any disk item,
	 *         the size of this new disk item is equal to the given
	 *         size. Otherwise, the size of this new disk item is equal
	 *         to 0.
	 *       | if (isValidSize(size))
	 *       |   then new.getSize() == size
	 *       |   else new.getSize() == BigInteger.ZERO
	 * @effect The directory of this new disk item is set to
	 *         the given directory.
	 *       | this.setDirectory(directory)
	 */
	public DiskItem(Directory directory, BigInteger size) throws IllegalArgumentException {
		if (!isValidSize(size))
			size = BigInteger.ZERO;
		setSize(size);
		this.setDirectory(directory);
	}


	/**
	 * Terminate this disk item.
	 *
	 * @post   This disk item  is terminated.
	 *       | new.isTerminated()
	 * @effect This disk item is removed from its directory, if any. 
	 *       | if (this.getDirectory() != null)
	 *       |   then this.getDirectory().removeAsDirectDiskItem(this)
	 */
	public void terminate() {
		this.isTerminated = true;

	}

	/**
	 * Return a boolean indicating whether or not this disk item
	 * is terminated.
	 */
	@Basic
	@Raw
	public boolean isTerminated() {
		return this.isTerminated;
	}

	/**
	 * Variable registering whether this disk item is terminated.
	 */
	private boolean isTerminated = false;

	/**
	 * Return the size of this disk item.
	 */
	@Basic
	@Raw
	public BigInteger getSize() {
		return this.size;
	}

	/**
	 * Check whether the given size is a valid size for
	 * any disk item.
	 *  
	 * @param  size
	 *         The size to check.
	 * @return True if and only if the given size is effective and not negative.
	 *       | result == (size != null) and (size.signum() >= 0)
	*/
	public static boolean isValidSize(BigInteger size) {
		return (size != null) && (size.signum() >= 0);
	}

	/**
	 * Set the size of this disk item to the given size.
	 * 
	 * @param  size
	 *         The new size for this disk item.
	 * @post   If the given size is a valid size for any disk item,
	 *         the size of this new disk item is equal to the given
	 *         size.
	 *       | if (isValidSize(size))
	 *       |   then new.getSize() == size
	 */
	@Raw
	public void setSize(BigInteger size) {
		if (isValidSize(size))
			this.size = size;
	}

	/**
	 * Variable registering the size of this disk item.
	 */
	private BigInteger size;

	/**
	 * Return the directory of this disk item.
	 */
	@Basic
	@Raw
	public Directory getDirectory() {
		return this.directory;
	}

	/**
	 * Check whether the given directory is a valid directory for
	 * this disk item.
	 *  
	 * @param  directory
	 *         The directory to check.
	 * @return ...
	 * 		 | if (directory != null && directory.isTerminated())
	 * 		 |	then result == false
	 * 		 | if(this.isTerminated())
	 * 		 |  then result == (directory == null)
	 */
	@Raw
	public boolean canHaveAsDirectory(Directory directory) {
		if (directory != null && directory.isTerminated())
			return false;
		if(this.isTerminated())
			return (directory == null);
		return true;
	}

	/**
	 * Check whether this disk item has a proper directory.
	 * 
	 * @return ...
	 * 		 | 
	 */
	@Raw
	public boolean hasProperDirectory() {
		return canHaveAsDirectory(this.getDirectory())
				&& (this.getDirectory() == null 
					|| this.getDirectory().canHaveAsDirectDiskItem(this));
	}

	/**
	 * Set the directory of this disk item to the given directory.
	 * 
	 * @param  directory
	 *         The new directory for this disk item.
	 * @pre    ...
	 * 		 | 
	 * @post   ...
	 *       | 
	 */
	@Raw
	void setDirectory(@Raw Directory directory) {
		assert canHaveAsDirectory(directory);
		this.directory = directory;
	}

	/**
	 * Variable referencing the directory of this disk item.
	 */
	private Directory directory;

	/**
	 * Check whether this disk item has the given disk item as one of its
	 * direct or indirect disk items.
	 * 
	 * @return False if the given disk item is not effective or the same as this
	 *         disk item.
	 *       | if ( (diskItem == null) || (diskItem == this) )
	 *       |   then result == false
	 */
	@Raw
	abstract boolean hasAsDirectOrIndirectDiskItem(@Raw DiskItem diskItem);

}
