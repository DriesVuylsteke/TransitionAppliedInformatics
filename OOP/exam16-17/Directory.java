package diskItemsStart;

import java.math.BigInteger;
import java.util.*;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of directories.
 * 
 * @invar   Each directory must have proper direct disk items.
 *        | hasProperDirectDiskItems()
 */
public class Directory extends DiskItem implements Iterable<DiskItem>{
	
	/**
	 * Initialize this new directory as a non-terminated disk item with 
	 * given size and part of the given directory and no direct disk items yet.
	 * 
	 * @effect This new directory is initialized as a new disk item with given
	 *         size that is part of the given directory.
	 *       | super(directory,size)
	 * @post   This new directory has no direct disk items yet.
	 *       | new.getAllDirectDiskItems().isEmpty()
	 */
	@Raw
	public Directory(Directory directory,BigInteger size) throws IllegalArgumentException {
		super(directory,size);
	}

	/**
	 * Check whether the given directory is a valid directory for
	 * this directory.
	 *  
	 * @param  directory
	 *         The directory to check.
	 * @return ...
	 * 		 | if(directory != null && directory.hasAsDirectOrIndirectDiskItem(this))
	 * 		 |   then result == false
	 */
	@Raw @Override
	public boolean canHaveAsDirectory(Directory directory) {
		return !(directory != null && directory.hasAsDirectOrIndirectDiskItem(this));
	}

	/**
	 * Check whether this directory has the given disk item as one of its
	 * direct disk items.
	 * 
	 * @param  diskItem
	 *         The disk item to check.
	 */
	@Basic
	@Raw
	public boolean hasAsDirectDiskItem(@Raw DiskItem diskItem) {
		return diskItems.contains(diskItem);
	}

	/**
	 * Check whether this directory has the given disk item as one of its
	 * direct or indirect disk items.
	 * 
	 * @param  diskItem
	 *         The disk item to check.
	 * @return ...
	 * 		 | 
	 */
	@Override
	public boolean hasAsDirectOrIndirectDiskItem(@Raw DiskItem diskItem) {
		for (DiskItem item : getAllDirectDiskItems()) {
			if(item == diskItem)
				return true;
			else if (item.hasAsDirectOrIndirectDiskItem(diskItem)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Return a set containing all the direct disk items in this directory.
	 * 
	 * @return ...
	 * 		 | Alle disk items die deze directory als directory hebben,
	 * 		 | zitten in de resulterende verzameling en alleen die elementen
	 * 		 | for each item in DiskItem
	 * 		 |	(item.getDirectory() == this == result.contains(item)
	 */
	public Set<DiskItem> getAllDirectDiskItems() {
		return new HashSet<DiskItem>(this.diskItems);
	}

	/**
	 * Check whether this directory can have the given disk item
	 * as one of its direct disk items.
	 * 
	 * @param  diskItem
	 *         The disk item to check.
	 * @return True if and only if the given disk item is effective
	 *         and that disk item can have this directory as its directory.
	 *       | result ==
	 *       |   (diskItem != null) &&
	 *       |   diskItem.canHaveAsDirectory(this)
	 */
	@Raw
	public boolean canHaveAsDirectDiskItem(DiskItem diskItem) {
		return (diskItem != null) && (diskItem.canHaveAsDirectory(this));
	}

	/**
	 * Check whether this directory has proper direct disk items attached to it.
	 * 
	 * @return True if and only if this directory can have each of the direct
	 *         disk items attached to it as one of its direct disk items,
	 *         and if each of these disk items references this directory as
	 *         the directory to which they are attached.
	 *       | for each diskItem in DiskItem:
	 *       |   if (hasAsDiskItem(diskItem))
	 *       |     then canHaveAsDiskItem(diskItem) &&
	 *       |          (diskItem.getDirectory() == this)
	 */
	public boolean hasProperDirectDiskItems() {
		for (DiskItem diskItem : diskItems) {
			if (!canHaveAsDirectDiskItem(diskItem))
				return false;
			if (diskItem.getDirectory() != this)
				return false;
		}
		return true;
	}

	/**
	 * Add the given disk item to the set of direct disk items of this directory.
	 * 
	 * @param  diskItem
	 *         The disk item to be added.
	 * @post   This directory has the given disk item as one of its direct disk items.
	 *       | new.hasAsDiskItem(diskItem)
	 * @post   The given disk item refers to this directory as its directory.
	 *       | (new diskItem).getDirectory() == this
	 * @throws IllegalArgumentException
	 *         This directory cannot have the given disk item as one of its
	 *         direct disk items.
	 *       | ! canHaveAsDirectDiskItem(diskItem)
	 * @throws IllegalStateException
	 *         The given disk item is already part of some directory.
	 *       | diskItem.getDirectory() != null
	 */
	public void addDirectDiskItem(@Raw DiskItem diskItem) {
		if (! canHaveAsDirectDiskItem(diskItem))
			throw new IllegalArgumentException();
		if (diskItem.getDirectory() != null)
			throw new IllegalStateException();
		diskItems.add(diskItem);
		diskItem.setDirectory(this);
	}
	
//	/**
//	 * 
//	 * @param other
//	 * @post   ...
//	 *       | for each item in other.getAllDiskItems():
//	 *       |   new.hasAsDirectDiskItem(item) &&
//	 *       |   (! (new other).hasAsDirectDiskItem(item)) &&
//	 *       |   (new item).getDirectory() == this
//	 * @throws  NullPointerException
//	 *          ...
//	 *        | other == null
//	 * @throws IllegalArgumentException
//	 *       | for some item in other.getAllDiskItems():
//	 *       |   ! this.canHaveAsDirectDiskItem(item)
//	 */
//	public void mergeWith(Directory other) throws NullPointerException, IllegalArgumentException {
//		for (DiskItem item: other.getAllDirectDiskItems())
//			if (! this.canHaveAsDirectDiskItem(item))
//				throw new IllegalArgumentException();
//		for (DiskItem item: other.getAllDirectDiskItems()) {
//			other.removeDiskItem(item);
//			this.addDiskItem(item);
//		}
//	}

	/**
	 * Remove the given disk item from the set of direct disk items of this directory.
	 * 
	 * @param  diskItem
	 *         The disk item to be removed.
	 * @post   ...
	 * 		| (new this).hasAsDirectDiskItem(diskItem) == false
	 * @post	...
	 * 		| (new diskItem).getDirectory == null
	 */
	@Raw
	public void removeAsDiskItem(DiskItem diskItem) throws NullPointerException, IllegalArgumentException{
		if(diskItem == null)
			throw new NullPointerException();
		if(! this.hasAsDirectDiskItem(diskItem))
			throw new IllegalArgumentException();
		diskItem.setDirectory(null);
		diskItems.remove(diskItem);
	}

	/**
	 * Variable referencing a set collecting all the disk items
	 * of this directory.
	 */
	private final Set<DiskItem> diskItems = new HashSet<DiskItem>();
	
	/**
	 * Return a iterator over the set of direct disk items of this
	 * directory.
	 * 
	 * @return The resulting iterator is effective and delivers each of the
	 *         direct disk items of this directory in random order.
	 */
	public Iterator<DiskItem> iterator() {
		return diskItems.iterator();
	}
	
	
}
