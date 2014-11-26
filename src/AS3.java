/************************************************************************************************************************************************************
 Richie Lazear
 AS3
 Due Date
 Mr. Ritter
 
 TODO: description of program goes here
 ************************************************************************************************************************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Timer;

// artamenes.org

public class AS3 {
	// Instance Fields
	private static Word[] sortedWordSet;
	private static boolean sorted = false;
	
	public static void main(String[] args)  throws FileNotFoundException {
		// Program introduction for the user
		System.out.println("Welcome to AS3!");
		// TODO: add some more information for the user here
		System.out.println("---------------------------------------------");
		// End program introduction
		
		// Set up the proper file, as chosen by the user		
		Scanner inFile = null;
		System.out.println("Please choose which file you want to read in.");
		System.out.println("1. As3Small.txt");
		System.out.println("2. As3Large.txt");
		System.out.print("Your selection: ");
		Scanner inConsole = new Scanner(System.in);
		// TODO: bounds check this for int
		int fileSelection = inConsole.nextInt();
		switch (fileSelection) {
			case 1:
				File AS3small = new File("As3Small.txt");
				inFile = new Scanner(AS3small);
			break;
			case 2:
				File AS3large = new File("As3Large.txt");
				inFile = new Scanner(AS3large);
			break;
		}
		
		Word[] wordSet = new Word[inFile.nextInt()];
		sortedWordSet = new Word[wordSet.length];
		for (int i = 0; i < wordSet.length; i++) {
			wordSet[i] = new Word(inFile.next());
		}
		
		for (int i = 0; i < wordSet.length; i++) {
			System.out.println(wordSet[i].getWord());
		}
		
		// Read in the file
		/*wordSet[0] = new Word(inFile.next());
		for (int i = 1; i < 9; i++) {
			String word = inFile.next();
			if (linearSearch(wordSet, word) >= 0) {
				wordSet[i].addToCount();
			} else {
				wordSet[i] = new Word(word);
				// System.out.println(wordSet[i].getWord());
			}
		}*/
		// End reading in the file
		
		int menuSelection = 0;
		do {
			System.out.println();
			System.out.println("--------------Menu--------------");
			System.out.println("1. Comparison Table of Sorts");
			System.out.println("2. Comparison Table of Searches");
			System.out.println("3. Run a single sort");
			System.out.println("4. Run a single search");
			System.out.println("5. Quit");
			System.out.print("Your selection: ");
			
			menuSelection = inConsole.nextInt();
			switch (menuSelection) {
				case 1:
					
				break;
				case 2:
					
				break;
				case 3:
					System.out.println("-------Choose a Sort-------");
					System.out.println("1. Selection");
					System.out.println("2. Insertion");
					System.out.println("3. Bubble");
					System.out.println("4. Merge");
					System.out.println("5. Quick");
					System.out.println("6. Go back");
					System.out.print("Your selection: ");
					int sortSelection = inConsole.nextInt();
					switch (sortSelection) {
						case 1:				
							// Copy the original word set into a new array that will be sorted so that the original set's order is preserved
							for (int i = 0; i < wordSet.length; i++) {
								sortedWordSet[i] = wordSet[i];
							}
							selectionSort();
							
							for (int i = 0; i < wordSet.length; i++) {
								System.out.println(sortedWordSet[i].getWord());
							}
						break;
						case 2:
							// Copy the original word set into a new array that will be sorted so that the original set's order is preserved
							for (int i = 0; i < wordSet.length; i++) {
								sortedWordSet[i] = wordSet[i];
							}
							insertionSort();
							
							for (int i = 0; i < wordSet.length; i++) {
								System.out.println(sortedWordSet[i].getWord());
							}
						break;
						case 3:
							
						break;
						case 4:
							
						break;
						case 5:
							
						break;
						case 6:
							// TODO: add functionality to return to the parent menu
						break;
					}
				break;
				case 4:
					System.out.println("-------Choose a Search-------");
					System.out.println("1. Linear");
					System.out.println("2. Binary");
					System.out.println("3. Quadratic");
					System.out.println("4. Go back");
					System.out.print("Your selection: ");
					int searchSelection = inConsole.nextInt();
					switch (searchSelection) {
						case 1:
							
						break;
						case 2:
							
						break;
						case 3:
							
						break;
						case 4:
							// TODO: add functionality to return to the parent menu
						break;
					}
				break;
				case 5:
					System.out.println("Ending program.");
					System.exit(0);
				break;
			}
		} while (menuSelection != 5);
		
		inConsole.close();
	}
	
	// Method for Selection Sort
	public static void selectionSort() {	
		int minIndex = 0;
		for (int i = 0; i < sortedWordSet.length - 1; i++) {
			minIndex = i;
			for (int j = i + 1; j < sortedWordSet.length; j++) {
				String toCompareJ = sortedWordSet[j].getWord();
				if (toCompareJ.compareTo(sortedWordSet[minIndex].getWord()) < 0) {
					minIndex = j;
				}
			}

			if (sortedWordSet[minIndex].getWord().compareTo(sortedWordSet[i].getWord()) < 0) {
				Word temp = sortedWordSet[i];
				sortedWordSet[i] = sortedWordSet[minIndex];
				sortedWordSet[minIndex] = temp;
			} else {
				break;
			}
		}
		sorted = true;
	}
	
	// Method for Insertion Sort
	public static void insertionSort() {	
		for (int i = 1; i < sortedWordSet.length; i++) {
			String valueToSort = sortedWordSet[i].getWord();
			System.out.println("valueToSort: " + valueToSort);
			int j = i;
			while (j > 0 && sortedWordSet[j - 1].getWord().compareTo(valueToSort) > 0) {
				Word temp = sortedWordSet[j];
				sortedWordSet[j] = sortedWordSet[j - 1];
				sortedWordSet[j - 1] = temp;
				j--;
			}
		}
		sorted = true;
	}
	
	public static void quickSort(Word[] inSet, int start, int end) {
		// https://www.youtube.com/watch?v=COk73cpQbFQ
		if (start < end) {
			int partitionIndex = quickPartition(inSet, start, end);
			quickSort(inSet, start, partitionIndex - 1);
			quickSort(inSet, partitionIndex + 1, end);
		}
	}
	
	public static int quickPartition(Word[] inSet, int start, int end) {
		// https://www.youtube.com/watch?v=COk73cpQbFQ
		String pivot = inSet[end].getWord();
		int partitionIndex = start;
		for (int i = start; i <= end - 1; i++) {
			if (inSet[i].getWord().compareTo(pivot) < 0){
				Word temp = inSet[partitionIndex];
				inSet[partitionIndex] = inSet[i];
				inSet[i] = temp;
				partitionIndex++;
			}
		}
		Word temp = inSet[partitionIndex];
		inSet[partitionIndex] = inSet[end];
		inSet[end] = temp;
		
		return partitionIndex;
	}
	
	// Method for Linear Search
	public static int linearSearch(Word[] inSet, String wordToFind) {
		boolean found = false;
		int foundAtIndex = 0;
		
		/*for (int i = 0; i < inSet.length; i++) {
			System.out.println(inSet[i]);
		}*/
		
		// Search each slot in the array by comparing the number to find against the current number in the array.
		int filledLength = 1;
		for (int i = 0; i < filledLength; i++) {
			System.out.println("i: " + i);
			System.out.println("filledLength: " + filledLength);
			String wordToTest = inSet[i].getWord();
			System.out.println(wordToTest);
			if (wordToFind.equals(wordToTest)) {
				found = true;
				foundAtIndex = i;
				break;
			} else {
				filledLength++;
			}
			
		}
		System.out.println();
		
		if (found) {
			return foundAtIndex;
		} else {
			return -1;
		}
	}
}