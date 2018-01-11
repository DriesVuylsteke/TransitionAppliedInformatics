// Author: Daniël Mertens

import java.math.BigInteger;
import java.util.function.Predicate;

import diskItemsStart.*;

public class ExperimentStart {

	public static void main(String[] args) {
		Directory dir = new Directory(null,BigInteger.TEN);
		// Disk items added to dir here.
		
		// Compute the total size of all the files in the directory
		// 'dir', and assign it to a variable 'totalSize' of proper
		// type.
		// - You must use each of the methods filter, map and reduce
		//   at least once in the computation.
		// - You must use lambda expressions as actual arguments for
		//   each of these invocations.

		dir.getAllDirectDiskItems().stream()
			.filter(item -> item instanceof File)
			.map(item -> item.getSize())
			.reduce((item,acc) -> acc.add(item));
		
		
		// Check whether the directory 'dir' has at least one direct
		// subdirectory that is empty and store the result of that
		// check is a variable 'hasEmptySubDirecctory' of proper type.
		// - You must use the method anyMatch in this computation and
		//   supply an object of an anonymous class implementing the
		//   interface Predicate as actual argument to that function.

		boolean test = dir.getAllDirectDiskItems().stream()
			.anyMatch(new Predicate<DiskItem>() {
				public boolean test(DiskItem t) {
					if (t instanceof Directory)
						if(((Directory) t).getAllDirectDiskItems().isEmpty())
							return true;
					return false;
				};
			});
		
		
		
	}
	
	

}
