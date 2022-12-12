package com.springaop.algorithm.sort;

public class Quicksort {

	public static void main(String[] args) {
		int[] input = {10, 2, 15, 27, 20, 8, 6, 10, 29, 30}; 
		Quick.sort(input);
		for (int i : input) {
			System.out.print(i + "  ");
		}
	}
	
	public static class Quick {
		private static void swap(int i, int j, int[] arr) {
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
		
		public static void sort(int[] arr) {
			quickSortImp(0, arr.length - 1, arr);
		}
		
		private static void quickSortImp(int left, int right, int[] arr) {
			int i = left,
				j = right,
				pivot = arr[(right - left)/2 + left];
			while(i <= j) {
				while(arr[i] < pivot) {
					i++;
				}
				while(arr[j] > pivot) {
					j--;
				}
				if(i <= j) {
					swap(i, j, arr);
					i++;
					j--;
				}
			}
			if(i <= right) {
				quickSortImp(i, right, arr);
			}
			if(j >= left) {
				quickSortImp(left, j, arr);
			}
		}
		
	}
}

