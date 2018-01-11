package diskItemsStart;

import java.math.BigInteger;

import be.kuleuven.cs.som.annotate.Raw;

public class File extends DiskItem {
	
	/**
	 * Initialize this new file as a non-terminated disk item wand part of the given directory.
	 * 
	 * @effect This new file is initialized as a new disk item with given
	 *         size that is part of the given directory.
	 *       | super(directory,size)
	 */
	@Raw
	public File(Directory directory,BigInteger size) {
		super(directory,size);
	}

	/**
	 * Check whether the given directory is a valid directory for
	 * this file.
	 *  
	 * @param  directory
	 *         The directory to check.
	 * @return ...
	 *       | if (directory != null && directory.getDirectory == null)
	 *       |	 then  result == false
	 */
	@Override
	public boolean canHaveAsDirectory(Directory directory) {
		return false;
		
	}

	/**
	 * Check whether this file has the given disk item as one of its
	 * direct or indirect disk items.
	 * 
	 * @return Always false
	 *       | result == false
	 */
	@Raw
	final boolean hasAsDirectOrIndirectDiskItem(@Raw DiskItem diskItem) {
		return false;
	}

}
