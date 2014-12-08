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

public class AS3 {
	// Instance Fields
	private static Scanner inConsole; // Reads user input from the console
	private static Word[] sortedWordSet; // TODO: add comment
	private static boolean sorted = false; // Keeps track of whether the above array is sorted so that the program does not try to sort an already-sorted array
	private static long startTime, endTime; // Long numbers to track the execution time of the sort and search methods
	private static int comparisonCount, swapCount; // Integers to keep track of the number of comparisons and swaps done in sorts and searches
	private static boolean printProgressionLines; // Determines whether or not progression lines should be printed during a sort method.  Determined by which file the user wants to read in.
	
	public static void main(String[] args) throws FileNotFoundException {
		// Program introduction for the user
		System.out.println("Welcome to AS3!");
		System.out.println("This program will read in a specified file, counting the number of times each word occurs in the file.");
		System.out.println("You will then be able to see a comparison of sorts or searches on the array, or choose an individual sort or search.");
		System.out.println("---------------------------------------------");
		// End program introduction
		
		// Set up the proper file, as chosen by the user		
		Scanner inFile = null;
		inConsole = new Scanner(System.in);
		int fileSelection;
		do {
			System.out.println("Please choose which file you want to read in.");
			System.out.println("1. As3Small.txt");
			System.out.println("2. As3Large.txt");
			System.out.print("Your selection: ");
			fileSelection = inConsole.nextInt();
			switch (fileSelection) {
				case 1:
					File AS3small = new File("As3Small.txt");
					inFile = new Scanner(AS3small);
					printProgressionLines = true;
				break;
				case 2:
					File AS3large = new File("As3Large.txt");
					inFile = new Scanner(AS3large);
					printProgressionLines = false;
				break;
			}
			System.out.println();
		} while (fileSelection < 1 || fileSelection > 2);
		
		Word[] wordSet; // Create the array
		// Define the size of the array based on the file being read in
		if (fileSelection == 1) {
			wordSet = new Word[50];
		} else {
			wordSet = new Word[100000];
		}
		
		// Read in the file
		wordSet[0] = new Word(inFile.next()); // Read in the very first word from the file, because it cannot be a duplicate
		int uniqueWordCount = 1; // Keeps track of the number of unique (NOT total) words read in from the file
		while (inFile.hasNext()) {
			String word = inFile.next();
			word = word.replaceAll("(?!-)\\p{Punct}", "").toLowerCase(); // Remove any punctuation (except single dashes) surrounding or in the string, then change all letters to lower case.
			// The following if statement serves to separate and handle words joined by double dashes (long dashes)
			if (word.contains("--")) {		
				int firstDashIndex = word.indexOf("-");		
				if (!word.endsWith("--")) { // If the string contains, but does not end with, "--", do the following...	
					String doubleDashedWord = word.substring(firstDashIndex + 2); // Make a substring of the second word		
					word = word.substring(0, firstDashIndex); // Make a substring that is the first word without the dashes		
							
					int linearSearchResult = linearSearch_fillingArray(wordSet, doubleDashedWord, uniqueWordCount);	// Search the array for the second word		
					if (linearSearchResult >= 0) { // If the second word is found, increment its count
						wordSet[linearSearchResult].addToCount();		
					} else { // If the second word is not found, add a corresponding new Word object to the array
						wordSet[uniqueWordCount] = new Word(doubleDashedWord);		
						uniqueWordCount++;		
					}		
				} else { // If the word ends with "--"...		
					word = word.substring(0, word.length() - 2); // ... remove the ending dashes		
				}		
			}
			int linearSearchResult = linearSearch_fillingArray(wordSet, word, uniqueWordCount); // Search the array for the first word
			if (linearSearchResult >= 0) {
				wordSet[linearSearchResult].addToCount();
			} else {
				wordSet[uniqueWordCount] = new Word(word);
				uniqueWordCount++;
			}
		}
		// End reading in the file
		
		sortedWordSet = new Word[uniqueWordCount]; // Create a new array that is exactly the length of the unique word count, for efficiency
		for (int i = 0; i < uniqueWordCount; i++) {
			sortedWordSet[i] = wordSet[i];
		}
		/*for (int i = 0; i < uniqueWordCount; i++) {
			System.out.println(sortedWordSet[i].getWord() + " -count: " + sortedWordSet[i].getCount());
		}*/
		
		// System.out.println();
		// System.out.println("uniqueWordCount: " + uniqueWordCount);
		
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
			System.out.println();
			switch (menuSelection) {
				case 1:
					printProgressionLines = false;
					System.out.println("----------Sort Method Results----------");
					
					// Start selection sort
					System.out.println("SELECTION SORT");
					sortProcedure("selection", wordSet);
					// End selection sort

					// Start insertion sort
					System.out.println("INSERTION SORT");
					sortProcedure("insertion", wordSet);
					// End insertion sort
					
					// Start bubble sort
					System.out.println("BUBBLE SORT");
					sortProcedure("bubble", wordSet);
					// End bubble sort
					
					// Start merge sort
					System.out.println("MERGE SORT");
					sortProcedure("merge", wordSet);
					// End merge sort
					
					// Start quick sort
					System.out.println("QUICK SORT");
					sortProcedure("quick", wordSet);
					// End quick sort
					
					if (fileSelection == 1) {
						printProgressionLines = true;
					}
				break;
				case 2:
					quickSort(sortedWordSet, 0, sortedWordSet.length - 1);
					
					System.out.println("---------Search Method Results---------");
					
					System.out.println("What string would you like to search for?");
					System.out.println("Your entry: ");
					String toFind = inConsole.next();
					
					// Start linear search
					System.out.println("LINEAR SEARCH");
					searchProcedure("linear", toFind);
					// End linear search
					
					// Start binary search
					System.out.println("BINARY SEARCH");
					searchProcedure("binary", toFind);
					// End binary search
					
					// Start quadratic search
					System.out.println("QUADRATIC SEARCH");
					searchProcedure("quadratic", toFind);
					// End quadratic search
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
					System.out.println();
					switch (sortSelection) {
						case 1:	
							System.out.println("SELECTION SORT");
							sortProcedure("selection", wordSet);
						break;
						case 2:
							System.out.println("INSERTION SORT");
							sortProcedure("insertion", wordSet);
						break;
						case 3:
							System.out.println("BUBBLE SORT");
							sortProcedure("bubble", wordSet);
						break;
						case 4:
							System.out.println("MERGE SORT");
							sortProcedure("merge", wordSet);
						break;
						case 5:
							System.out.println("QUICK SORT");
							sortProcedure("quick", wordSet);
						break;
						case 6: break;
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
							System.out.println("What string would you like to search for?");
							System.out.println("Your entry: ");
							toFind = inConsole.next();
							System.out.println("LINEAR SEARCH");
							searchProcedure("linear", toFind);
						break;
						case 2:
							if (sorted) {
								System.out.println("What string would you like to search for?");
								System.out.println("Your entry: ");
								toFind = inConsole.next();
								System.out.println("BINARY SEARCH");
								searchProcedure("binary", toFind);
							} else {
								System.out.println("Please go back and sort the array before running a binary search.");
							}
						break;
						case 3:
							if (sorted) {
								System.out.println("What string would you like to search for?");
								System.out.println("Your entry: ");
								toFind = inConsole.next();
								System.out.println("QUADRATIC SEARCH");
								searchProcedure("quadratic", toFind);
							} else {
								System.out.println("Please go back and sort the array before running a binary search.");
							}
						break;
						case 4: break;
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
		int minIndex = 0; // Represents the index of the alphabetically least word in the array
		for (int i = 0; i < sortedWordSet.length - 1; i++) {
			minIndex = i;
			// This for loop goes through the array to find the "minimum" word
			for (int j = i + 1; j < sortedWordSet.length; j++) {
				String toCompareJ = sortedWordSet[j].getWord();
				comparisonCount++;
				if (toCompareJ.compareTo(sortedWordSet[minIndex].getWord()) < 0) {
					minIndex = j;
				}
			}

			comparisonCount++;
			if (sortedWordSet[minIndex].getWord().compareTo(sortedWordSet[i].getWord()) < 0) { // Compares the "minimum" word to the word at the current index
				// Swap the two words
				Word temp = sortedWordSet[i];
				sortedWordSet[i] = sortedWordSet[minIndex];
				sortedWordSet[minIndex] = temp;
				swapCount++;
			} else {
				break;
			}
			if (printProgressionLines) {
				printSortedWordSet();
			}
		}
		sorted = true;
	}
	
	// Method for Insertion Sort
	public static void insertionSort() {	
		for (int i = 1; i < sortedWordSet.length; i++) {
			String valueToSort = sortedWordSet[i].getWord();
			int j = i;
			while (j > 0 && sortedWordSet[j - 1].getWord().compareTo(valueToSort) > 0) {
				comparisonCount++;
				// Swap the two words
				Word temp = sortedWordSet[j];
				sortedWordSet[j] = sortedWordSet[j - 1];
				sortedWordSet[j - 1] = temp;
				swapCount++;
				j--;
			}
			comparisonCount++;
			if (printProgressionLines) {
				printSortedWordSet();
			}
		}
		sorted = true;
	}
	
	// Method for bubble sort
	public static void bubbleSort() {
		boolean swapped;
		for (int i = sortedWordSet.length - 1; i >= 0; i--) {
			swapped = false;
			for (int j = 0; j < i; j++) {
				comparisonCount++;
				if (sortedWordSet[j].getWord().compareTo(sortedWordSet[j + 1].getWord()) > 0) { // Compare the word at index j to the word directly following it
					// Swap the two words
					Word temp = sortedWordSet[j];
					sortedWordSet[j] = sortedWordSet[j + 1];
					sortedWordSet[j + 1] = temp;
					swapCount++;
					swapped = true;
				}
			}
			if (swapped == false) { // If nothing for this run through the loop has been swapped, then the array is already in order, so the loop can end.
				break;
			}
			if (printProgressionLines) {
				printSortedWordSet();
			}
		}
		sorted = true;
	}
	
	// Main method for merge sort
	public static void mergeSort(Word[] inSet) {
		// This method is based off of: https://www.youtube.com/watch?v=TzeBrDU-JaY
		
		int n = inSet.length;
		// Base case
		if (n < 2) {
			return;
		}
		int mid = n / 2; // Defines the index of the midpoint of the parent array
		// Create the child arrays
		Word[] leftArray = new Word[mid];
		Word[] rightArray = new Word[n - mid];
		// Fill the child arrays
		for (int i = 0; i < mid; i++) {
			leftArray[i] = inSet[i];
		}
		for (int i = mid; i < n; i++) {
			rightArray[i - mid] = inSet[i];
		}
		
		// Recursive calls
		mergeSort(leftArray);
		mergeSort(rightArray);
		
		merge(leftArray, rightArray, inSet);
		sorted = true;
	}
	
	// Helper method for merge sort
	public static void merge(Word[] inLeftArray, Word[] inRightArray, Word[] inSet) {
		// This method is based off of: https://www.youtube.com/watch?v=TzeBrDU-JaY
		
		int nLeft = inLeftArray.length;
		int nRight = inRightArray.length;
		int l = 0; // Represents the current index location in the left array
		int r = 0; // Represents the current index location in the right array
		int i = 0; // Represents the current index location in the 'inSet' array
		while (l < nLeft && r < nRight) {
			comparisonCount++;
			if (inLeftArray[l].getWord().compareTo(inRightArray[r].getWord()) < 0) { // The current word from the left array is alphabetically less than the current word from the right array
				comparisonCount++;
				inSet[i] = inLeftArray[l];
				l++; // Move to the next spot in the left array
			} else if (inLeftArray[l].getWord().equals(inRightArray[r].getWord())) { // Both words are alphabetically equal
				comparisonCount++;
				inSet[i] = inLeftArray[l];
				i++;
				l++;
				inSet[i] = inRightArray[r];
				r++;
			} else { // The current word from the right array is alphabetically less than the current word from the right array
				inSet[i] = inRightArray[r];
				swapCount++;
				r++; // Move to the next spot in the right array;
			}
			i++; // Move to the next spot in the complete array
		}
		// Fill in any remaining numbers from the left and/or right arrays
		while (l < nLeft) {
			inSet[i] = inLeftArray[l];
			i++;
			l++;
		}
		while (r < nRight) {
			inSet[i] = inRightArray[r];
			i++;
			r++;
		}
		
		// Print progression lines
		if (printProgressionLines) {
			for (int z = 0; z < inSet.length; z++) {
				System.out.print(inSet[z].getWord() + " ");
			}
			System.out.println();
		}
	}
	
	// Main method for quick sort 
	public static void quickSort(Word[] inSet, int start, int end) {
		// This method is based off of: https://www.youtube.com/watch?v=COk73cpQbFQ
		if (start < end) {
			int partitionIndex = quickPartition(inSet, start, end);
			quickSort(inSet, start, partitionIndex - 1);
			quickSort(inSet, partitionIndex + 1, end);
		} else {
			sorted = true;
		}
	}
	
	// Helper method for quick sort
	public static int quickPartition(Word[] inSet, int start, int end) {
		// This method is based off of: https://www.youtube.com/watch?v=COk73cpQbFQ
		String pivot = inSet[end].getWord();
		int partitionIndex = start;
		for (int i = start; i < end; i++) {
			comparisonCount++;
			if (inSet[i].getWord().compareTo(pivot) < 0){
				// Swap the two words
				Word temp = inSet[partitionIndex];
				inSet[partitionIndex] = inSet[i];
				inSet[i] = temp;
				swapCount++;
				partitionIndex++;
			}
		}
		// Swap the two words
		Word temp = inSet[partitionIndex];
		inSet[partitionIndex] = inSet[end];
		inSet[end] = temp;
		swapCount++;
		
		// Print progression lines
		if (printProgressionLines) {
			for (int i = 0; i < inSet.length; i++) {
				System.out.print(inSet[i].getWord() + " ");
			}
			System.out.println();
		}
		
		return partitionIndex;
	}
	
	// Method for Linear Search
	public static int linearSearch(String wordToFind) {
		boolean found = false;
		int foundAtIndex = 0;
		
		// Search each slot in the array by comparing the number to find against the current number in the array.
		for (int i = 0; i < sortedWordSet.length; i++) {
			comparisonCount++;
			if (wordToFind.equals(sortedWordSet[i].getWord())) {
				found = true;
				foundAtIndex = i;
				break;
			}
		}
		
		if (found == true) {
			return foundAtIndex;
		} else {
			return -1;
		}
	}
	
	// Method for Binary Search
	public static int binarySearch(String wordToFind) {
		boolean found = false;
		int foundAtIndex = 0;
		int low = 0;
		int high = sortedWordSet.length - 1;
		// Keep narrowing the range the method searches by halves
		while (low <= high) {
			int mid = (low + high) / 2;
			int difference = sortedWordSet[mid].getWord().compareTo(wordToFind);
			comparisonCount++;
			if (difference == 0) {
				found = true;
				foundAtIndex = mid;
				break;
			} else if (difference < 0) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		if (found == true) {
			return foundAtIndex;
		} else {
			return -1;
		}
	}

	// Method for Quadratic Search
	public static int quadraticSearch(String wordToFind) {
		boolean found = false;
		int foundAtIndex = 0;

		int range = (int) Math.sqrt(sortedWordSet.length);
		int high = range;
		
		// Keep narrowing the range the method searches by the square root of the array's length
		while (wordToFind.compareTo(sortedWordSet[high].getWord()) > 0) {
			comparisonCount++;
			if (high + range > sortedWordSet.length - 1) {
				high = sortedWordSet.length - 1;
			} else {
				high = high + range;
			}
		}
		comparisonCount++;
		
		//Linear Search within the range
		for (int i = (high - range); i <= high; i++) {
			comparisonCount++;
			if (wordToFind.equals(sortedWordSet[i].getWord())) {
				found = true;
				foundAtIndex = i;
				break;
			}
		}

		if (found == true) {
			return foundAtIndex;
		} else {
			return -1;
		}
	}
	
	// A specialty method used only while reading the file in to the wordSet array
	public static int linearSearch_fillingArray(Word[] inSet, String wordToFind, int filledLength) {
		boolean found = false;
		int foundAtIndex = 0;

		for (int i = 0; i < filledLength; i++) {
			String wordToTest = inSet[i].getWord();
			if (wordToFind.equals(wordToTest)) {
				found = true;
				foundAtIndex = i;
				break;
			}
		}
		
		if (found) {
			return foundAtIndex;
		} else {
			return -1;
		}
	}
	
	// A method that simply prints out the sortedWordSet array
	public static void printSortedWordSet() {
		for (int i = 0; i < sortedWordSet.length; i++) {
			System.out.print(sortedWordSet[i].getWord() + " ");
		}
		System.out.println();
	}
	
	// A wrapper method for all of the tasks necessary before and after calling a sort
	// I chose to make this a method on its own in order avoid redundant code
	public static void sortProcedure(String inSortName, Word[] inSet) {
		if (sorted) {
			// Copy the original word set into a new array that will be sorted so that the original set's order is preserved
			for (int i = 0; i < sortedWordSet.length; i++) {
				sortedWordSet[i] = inSet[i];
			}
		}
		
		// Reset sort statistics
		comparisonCount = 0;
		swapCount = 0;
		startTime = 0;
		endTime = 0;
		
		// Call the proper sort method, depending on the parameters entered in sortProcedure
		if (inSortName.equals("selection")) {
			startTime = System.nanoTime();
			selectionSort();
			endTime = System.nanoTime();
		} else if (inSortName.equals("insertion")) {
			startTime = System.nanoTime();
			insertionSort();
			endTime = System.nanoTime();
		} else if (inSortName.equals("bubble")) {
			startTime = System.nanoTime();
			bubbleSort();
			endTime = System.nanoTime();
		} else if (inSortName.equals("merge")) {
			startTime = System.nanoTime();
			mergeSort(sortedWordSet);
			endTime = System.nanoTime();
		} else if (inSortName.equals("quick")) {
			startTime = System.nanoTime();
			quickSort(sortedWordSet, 0, sortedWordSet.length - 1);
			endTime = System.nanoTime();
		}
		
		long duration = endTime - startTime; // Calculates the total time the selected method ran for
		
		// Print sort statistics
		System.out.println("Comparisons: " + comparisonCount);
		System.out.println("      Swaps: " + swapCount);
		System.out.println("   Duration: " + duration + " nanoseconds");
		System.out.println();
	}
	
	// A wrapper method for all of the tasks necessary before and after calling a search
	// I chose to make this a method on its own in order avoid redundant code
	public static void searchProcedure(String inSearchName, String inToFind) {
		// Reset search statistics
		comparisonCount = 0;
		startTime = 0;
		endTime = 0;
		
		// Call the proper search
		int searchResult = 0;
		if (inSearchName.equals("linear")) {
			startTime = System.nanoTime();
			searchResult = linearSearch(inToFind);
			endTime = System.nanoTime();
		} else if (inSearchName.equals("binary")) {
			startTime = System.nanoTime();
			searchResult = binarySearch(inToFind);
			endTime = System.nanoTime();
		} else if (inSearchName.equals("quadratic")) {
			startTime = System.nanoTime();
			searchResult = quadraticSearch(inToFind);
			endTime = System.nanoTime();
		}
		
		long duration = endTime - startTime;
		
		// Print out the results of the search
		if (searchResult > -1) {
			System.out.println("Index Location: " + searchResult);
			System.out.println("   Occurrences: " + sortedWordSet[searchResult].getCount());
		} else {
			System.out.println("Sorry, that string is not in the array.");
		}
		
		// Print search statistics
		System.out.println("   Comparisons: " + comparisonCount);
		System.out.println("      Duration: " + duration + " nanoseconds");
		System.out.println();
	}
}