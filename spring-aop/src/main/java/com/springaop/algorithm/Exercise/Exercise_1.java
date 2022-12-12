package com.springaop.algorithm.Exercise;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Exercise_1 {
	
	private static int getLines(Scanner scanner) {
		
		int lineNumber = 0;
		boolean check = true;
		do {
			try {
				lineNumber = Integer.parseInt(scanner.nextLine());
				
			} catch (NumberFormatException e) {
				System.out.println("Just integer number, please try again!");
				check = false;
				continue;
			}
			if (lineNumber <= 0 || lineNumber > 10) {
				System.out.println("Wrong nummber (1 -> 10), please try again!");
				check = false;
			}else
				check = true;
			
		} while (!check);
		
		return lineNumber;
	}
	
	private static boolean checkInput(Scanner scanner) {
		
		boolean check = true;
		String input = "";
		do{
			try {
				check = true;
				input = scanner.next("[a-zA-Z]{1,100}");
				
			} catch (InputMismatchException e) {
				input = scanner.nextLine();
				check = false;
				System.out.println("The length of the string is at max 100 and contains only the characters a to z, please try again!");
			}
		}while(!check);
		
		char[] charsInput = input.toCharArray();
        Map<Character, Character> uniqueChars = new HashMap<Character, Character>();
        for(char c : charsInput) {
            if(!Character.isLetter(c)) {
                return false;
            }
        	uniqueChars.put(c, c);
        	if(uniqueChars.size() == 26)
        		return true;
        }
        if(uniqueChars.size() < 26) 
        	return false;
        
        return true;
	}

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		
		System.out.println("Enter the number of lines!");
		int N = getLines(scanner);
		
		System.out.println("Start!");
		for (int i = 0; i < N; i++) {
			boolean check = checkInput(scanner);
			if(check)
				System.out.println("YES");
			else 
				System.out.println("NO");
		}
		
		System.out.println("Complete!");
		scanner.close();
	}

}
