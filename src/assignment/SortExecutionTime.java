/** Michael Womack
 *  CS 3401
 *  Assignment 6
 *  3/15/2015*/
package assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SortExecutionTime {
	
	public static void main(String[] args)  {
			
		System.out.println("Array Size  |  Selection Sort  |  Bubble Sort |  Merge Sort  |  "
					+ "Quick Sort  |  Heap Sort  |  Radix Sort ");
		System.out.println("----------------------------------------------------------"
				+ "-----------------------------------------------");
		long start = System.currentTimeMillis(), time;
		for(int i = 50000; i < 300000; i += 50000){
			int[] test = createIntArray(i);
			selectionSort(test);
			time = System.currentTimeMillis() - start; 
			System.out.print(i + "\t\t" + time);
			
			
			shuffleArray(test);
			start = System.currentTimeMillis();
			bubbleSort(test);
			time = System.currentTimeMillis() - start;
			System.out.print("\t\t" + time);
			
			
			shuffleArray(test);
			start = System.currentTimeMillis();
			mergeSort(test);
			time = System.currentTimeMillis() - start;
			System.out.print("\t\t" + time);
			
			
			shuffleArray(test);
			start = System.currentTimeMillis();
			quickSort(test);
			time = System.currentTimeMillis() - start;
			System.out.print("\t\t" + time);
			

			shuffleArray(test);
			start = System.currentTimeMillis();
			heapSort(test);
			time = System.currentTimeMillis() - start;
			System.out.print("\t\t" + time);
			
			
			shuffleArray(test);
			start = System.currentTimeMillis();
			radixSort(test);
			time = System.currentTimeMillis() - start;
			System.out.print("\t\t" + time);
			System.out.println();
			
		}
		
		
			
		
	}
	public static void shuffleArray(int[] a){
		
		int temp, randIndex;
		for(int i = 0; i < a.length; i++){
			randIndex = (int)(Math.random() * a.length);
			temp = a[i];
			a[i] = a[randIndex];
			a[randIndex] = temp;
		}
	}
	
	public static int[] createIntArray(int n){
		int[] a = new int[n];
		for(int i = 0; i < n; i++){
			a[i] = (int)(Math.random() * 100);
		}
		return a;
	}
	
	/**Method sorts an array using the selection sort algorithm */
	public static void selectionSort(int[] list){
		int i, j, minIndex;
		
		for(i = 0; i < list.length-1; i++){
			minIndex = i;
			for(j = i; j < list.length; j++){
				if(list[j] < list[minIndex])
					minIndex = j;
			}
			if(i != minIndex){
				int temp = list[i];
				list[i] = list[minIndex];
				list[minIndex] = temp;
			}
		}
	}
	
	/**Method sorts an array using the bubble sort algorithm */
	public static void bubbleSort(int[] list){
		boolean needPass = true;
		
		for(int i = 1; i < list.length && needPass; i++){
			needPass = false;
			for(int j = 0; j < list.length - i; j++){
				if(list[j] > list[j+1]){
					int temp = list[j];
					list[j] = list[j+1];
					list[j+1] = temp;
					needPass = true;
				}
			}
		}
	}
	
	/**Method sorts an array using the merge sort algorithm */
	public static void mergeSort(int[] list){
		if(list.length > 1){
			//first half
			int[] first = new int[list.length/2];
			System.arraycopy(list, 0, first, 0, list.length/2);
			mergeSort(first);
			
			//second half
			int secondLength = list.length - list.length/2;
			int[] second = new int[secondLength];
			System.arraycopy(list, list.length/2, second, 0, secondLength);
			mergeSort(second);
			
			//Merge
			merge(first, second, list);
		}
	}

	private static void merge(int[] first, int[] second, int[] list) {
		int index1 = 0;
		int index2 = 0;
		int index3 = 0;
		
		//compare the two arrays and add elements until one runs out
		while(index1 < first.length && index2 < second.length){
			if(first[index1] < second[index2])
				list[index3++] = first[index1++];
			else
				list[index3++] = second[index2++];
		}
		
		//empty the rest of the remaining array into the list
		while(index1 < first.length)
			list[index3++] = first[index1++];
		
		while(index2 < second.length)
			list[index3++] = second[index2++];
	}

	/**Calls overloaded quicksort helper method */
	public static void quickSort(int[] list){
		quickSort(list, 0, list.length-1);
		
	}
	
	/** Sorts array according to quicksort algorithm*/
	private static void quickSort(int[] list, int start, int end){
		if(end > start){
			int pivot = partition(list, start, end);
			quickSort(list, start, pivot -1);
			quickSort(list, pivot + 1, end);
		}
	}
	
	private static int partition(int[] list, int start, int end){
		int pivot = list[start];
		int low = start + 1;
		int high = end;
		
		while(high > low){
			while(low <= high && list[low] <= pivot)
				low++;
			while(low<= high && list[high] > pivot)
				high--;
			
			if(high > low){
				int temp = list[high];
				list[high] = list[low];
				list[low] = temp;
			}		
		}
		while(high > start && list[high] >= pivot)
			high--;
		
		if(pivot > list[high]){
			list[start] = list[high];
			list[high] = pivot;
			return high;
		}
		else
			return start;
	}
	/** Sorts array according to heap sort algorithm*/
	public static void heapSort(int[] list){
		
		Heap<Integer> heap = new Heap<Integer>();
		
		//add elements to the heap
		for(int i = 0; i < list.length; i++)
			heap.add(list[i]);
		
		//remove elements from heap
		for(int i = list.length -1; i >= 0; i--)
			list[i] = heap.remove();
	}
	
	
	/** Sorts array according to radixSirt algorithm*/
	public static void radixSort(int[] list){
		int maxDigits = getMaxDigits(list);
		List[] bucket =  new ArrayList[10]; 
		int mod = 10, div = 1;
		
		for(int i = 0; i < bucket.length; i++)
			bucket[i] = new ArrayList<Integer>();
		
		for(int i = 0; i < maxDigits; i++, mod *= 10, div *= 10){
			for(int j = 0; j < list.length; j++){
				
					int key = (list[j] % mod)/div;
					bucket[key].add(list[j]);
				
			}
		
			int pos = 0;
			for(int k = 0; k < bucket.length; k++){
				if(bucket[k] != null){
					for(int j = 0; j < bucket[k].size(); j++)
						list[pos++] = (int)bucket[k].get(j);
				 bucket[k].clear();
				}
	    	}
		}
	}
	
	private static int getMaxDigits(int[] list){
		int max = list[0];
		for(int i = 1; i < list.length; i++)
			if(list[i] > max)
				max = list[i];
		return String.valueOf(max).length();
	}
}
