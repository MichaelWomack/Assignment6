/** Michael Womack
 *  CS 3401
 *  Assignment 6
 *  3/15/2015*/
package assignment;

import java.util.Comparator;

public class CompareValues<E extends Comparable<E>> implements Comparator<E>{
	
	@Override
	public int compare(E o1, E o2) {
		
		return o1.compareTo(o2);
	}
			
}


