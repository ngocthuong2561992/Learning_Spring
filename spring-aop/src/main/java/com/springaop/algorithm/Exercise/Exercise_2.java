package com.springaop.algorithm.Exercise;

import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Exercise_2 {
	
	private static void dauLoang(int[][] arr, int x,int y,int key) {
		if(arr[x][y] == 1) arr[x][y] = key;
		if(arr[x+1][y] == 1) dauLoang(arr, x+1, y, key);
		if(arr[x][y+1] == 1) dauLoang(arr, x, y+1, key);
		if(arr[x-1][y] == 1) dauLoang(arr, x-1, y, key);
		if(arr[x][y-1] == 1) dauLoang(arr, x, y-1, key);
		if(arr[x+1][y+1] == 1) dauLoang(arr, x+1, y+1, key);
		if(arr[x-1][y-1] == 1) dauLoang(arr, x-1, y-1, key);
		if(arr[x+1][y-1] == 1) dauLoang(arr, x+1, y-1, key);
		if(arr[x-1][y+1] == 1) dauLoang(arr, x-1, y+1, key);
	}
	
	private static int getLines(Scanner scanner) {
			
		int line = 0;
		boolean check = true;
		do {
			try {
				check = true;
				line = Integer.parseInt(scanner.nextLine());
				
			} catch (NumberFormatException e) {
				System.out.println("Just integer number, please try again!");
				check = false;
				continue;
			}
			if (line < 1 || line > 500) {
				System.out.println("1 <= nummber <= 500, please try again!");
				check = false;
			}
			
		} while (!check);
		
		return line;
	}
	
	private static int countByteWarrior(Scanner scanner, int N) {
		boolean check = true;
		int count = 2;
		StringBuilder totalInput = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			String input;
			do {
				try {
					check = true;
					input = scanner.next("[01]{" + N + "}");
					
					totalInput.append(input.trim()); 
					
				} catch (InputMismatchException e) {
					input = scanner.nextLine();
					check = false;
					System.out.println("Wrong binary string(0 or 1) and must be " + N + " digit");
				}
			} while (!check);
		}
		
		int[][] arr = new int[N+2][N+2];
		for(int i = 0; i<N+2;i++) {
			for(int j=0;j<N+2;j++) {
				if(i==0 || i==N+1)
					arr[i][j] = 0;
				else
					if(j == 0 || j == N+1)
						arr[i][j] = 0;
					else {
						arr[i][j] = totalInput.charAt((i-1)*N + (j-1)) - '0';
					}
			}
		}
		
		for(int i = 0; i < N+2; i++) {
			for(int j = 0; j < N+2; j++) {
				if(arr[i][j] == 1) {
					dauLoang(arr, i, j, count);
					count++;
				}
			}
		}
		
		return count-2;
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		System.out.println("Enter the number of lines!");
		int N = getLines(scanner);
		
		System.out.println("Start!");
		int count = countByteWarrior(scanner, N);
		System.out.println(count);
		System.out.println("Complete!");
		scanner.close();
	}
}

