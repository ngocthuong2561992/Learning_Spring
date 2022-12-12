package com.multidb.common;


public class SortSample {
    public static void main(String[] args) {
        int[] arr = {1,5,3,8,6,1};
//        bubbleSort(arr, 6);
//        interchangeSort(arr, 6);
        selectionSort(arr, 6);
        for (int i : arr) {
            System.out.printf(String.valueOf(i));
        }
    }
    static void interchangeSort(int[] arr, int n) {
        System.out.printf("interchangeSort\n");
        for (int i = 0; i < n-1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    swap(i, j, arr);
                }
            }
        }
    }
    static void selectionSort(int[] arr, int n) {
        System.out.printf("selectionSort\n");
        int min;
        for (int i = 0; i < n-1; i++) {
            min = i;
            for (int j = i+1; j<n; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            if(i != min) {
                swap(i, min, arr);
            }
        }
    }
    static void bubbleSort(int[] arr, int n) {
        System.out.printf("bubbleSort\n");
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(j, j - 1, arr);
                }
            }
        }
    }
    static void swap(int i, int j, int[] arr) {
        int temp;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
