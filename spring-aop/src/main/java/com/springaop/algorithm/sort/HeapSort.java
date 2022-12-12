package com.springaop.algorithm.sort;

public class HeapSort {

//		4	12	11	13	5	6	7
	
//					4
//				
//			12				11	
//		13		5		6		7
	
//	get parent node	: (i - 1)/2
//	get left child	: 2*i + 1
//	get right child	: 2*i + 2
	
	public static void main(String args[]) {
        int arr[] = {4, 12, 11, 13, 5, 2, 17};
 
        Heap.sort(arr);
    }
	
	public static class Heap {
		private static void printArr(int arr[]) {
			for (int i : arr) {
				System.out.print(i + "  ");
			}
			System.out.print("\n");
		}
		
		private static void sort(int arr[]) {
	        int n = arr.length;
	 
	        printArr(arr);
	        // Build heap (rearrange array)
	        /*
	         * As last non leaf node will be at (arr.length-1)/2 
	         * so we will start from this location for heapifying the elements
	         * */
	        for (int i = n/2 - 1; i >= 0; i--) {
	        	heapify(arr, n, i);
	        }
	        
	        printArr(arr);
	        
	        // One by one extract an element from heap
	        for (int i = n-1; i >= 0; i--) {
	            // Move current root to end
	            int temp = arr[0];
	            arr[0] = arr[i];
	            arr[i] = temp;
	 
	            // call max heapify on the reduced heap
	            heapify(arr, i, 0);
	        }
	        printArr(arr);
	    }
		
		private static void heapify(int arr[], int n, int i) {
	        int largest = i;  // Initialize largest as root
	        int l = 2*i + 1;  // left = 2*i + 1
	        int r = 2*i + 2;  // right = 2*i + 2
	 
	        // If left child is larger than root
	        if (l < n && arr[l] > arr[largest]) {
	        	largest = l;
	        }
	 
	        // If right child is larger than largest so far
	        if (r < n && arr[r] > arr[largest]) {
	        	largest = r;
	        }
	 
	        // If largest is not root
	        if (largest != i) {
	            int swap = arr[i];
	            arr[i] = arr[largest];
	            arr[largest] = swap;
	 
	            // Recursively heapify the affected sub-tree
	            heapify(arr, n, largest);
	        }
	    }
		
	}
}
