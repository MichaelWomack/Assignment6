/** Michael Womack
 *  CS 3401
 *  Assignment 6
 *  3/15/2015*/
package assignment;

import java.util.Comparator;

public class DetermineIfSorted {

	public static void main(String[] args) {
		
		
		Integer[] a = {1,2,3,4,5,6,7,8}; //true for ascending
		Integer[] aa = {8,7,6,5,4,3,2,1}; //true for descending
		Double[] b = {3.0, 0.9, 0.8, 2.0, 0.05};//false
		String[] c = {"A", "V", "C", "B"};//false
		
		boolean ascend = true;
		boolean descend = false;
		System.out.println("List 1 is sorted: " + ordered(a, new CompareValues(), ascend));
		System.out.println("List 2 is sorted: " + ordered(aa, new CompareValues(), descend));
		System.out.println("List 3 is sorted: " + ordered(b, new CompareValues(), ascend));
		System.out.println("List 4 is sorted: " + ordered(c, new CompareValues(), ascend));
	}
	
	/** Method takes a list, comparator, and boolean value. If boolean value is true,
	 * it will call the overloaded method "ordered" and return whether the list is sorted in 
	 * ascending order. If the boolean is false, it will return true if the list is in descending
	 * order.*/
	public static <E> boolean ordered(E[] list, Comparator<? super E> comp, boolean ascend){
		if(ascend)
			return ordered(list, comp);
		else{
			for (int i = 0; i < list.length-1; i++){
				if(comp.compare(list[i],list[i+1]) < 0)
					return false;		
			}
			return true;
		}
	}
	
	
	private static <E> boolean ordered(E[] list, Comparator<? super E> comp){
		for (int i = 0; i < list.length-1; i++){
			if(comp.compare(list[i],list[i+1]) > 0)
				return false;		
		}
		return true;
	}
}
