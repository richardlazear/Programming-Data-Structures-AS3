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
	private static long startTime, endTime, duration;
	private static int comparisonCount, swapCount;
	
	public static void main(String[] args)  throws FileNotFoundException {
		// Program introduction for the user
		System.out.println("Welcome to AS3!");
		// TODO: add some more information for the user here
		System.out.println("---------------------------------------------");
		// End program introduction
		
		// Set up the proper file, as chosen by the user		
		Scanner inFile = null;
		Scanner inConsole = new Scanner(System.in);
		// TODO: bounds check this for int
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
				break;
				case 2:
					File AS3large = new File("As3Large.txt");
					inFile = new Scanner(AS3large);
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
		wordSet[0] = new Word(inFile.next());
		int numOfWords = 1;
		while (inFile.hasNext()) {
			String word = inFile.next();
			// TODO: handle dashes in sentences: "...smiled-no..."
			// Remove any punctuation (except single dashes) surrounding or in the string, then changes all letters to lower case.
			word = word.replaceAll("(?!-)\\p{Punct}", "").toLowerCase();
			if (word.contains("--")) {
				int firstDashIndex = word.indexOf("-");
				String doubleDashedWord = word.substring(firstDashIndex + 2);
				word = word.substring(0, firstDashIndex);
				System.out.println("first: " + word);
				System.out.println("second: " + doubleDashedWord);
				
				int linearSearchResult = linearSearch_fillingArray(wordSet, doubleDashedWord, numOfWords);
				if (linearSearchResult >= 0) {
					wordSet[linearSearchResult].addToCount();
				} else {
					wordSet[numOfWords] = new Word(doubleDashedWord);
					numOfWords++;
				}
			}
			int linearSearchResult = linearSearch_fillingArray(wordSet, word, numOfWords);
			if (linearSearchResult >= 0) {
				wordSet[linearSearchResult].addToCount();
			} else {
				wordSet[numOfWords] = new Word(word);
				numOfWords++;
			}
		}
		// End reading in the file
		
		sortedWordSet = new Word[numOfWords];
		for (int i = 0; i < numOfWords; i++) {
			sortedWordSet[i] = wordSet[i];
		}
		for (int i = 0; i < numOfWords; i++) {
			System.out.println(sortedWordSet[i].getWord() + " -count: " + sortedWordSet[i].getCount());
		}
		
		System.out.println();
		System.out.println("numOfWords: " + numOfWords);
		
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
					System.out.println("----------Sort Method Results----------");
					
					// Start selection sort
					if (sorted) {
						// Copy the original word set into a new array that will be sorted so that the original set's order is preserved
						for (int i = 0; i < numOfWords; i++) {
							sortedWordSet[i] = wordSet[i];
						}
					}
					System.out.println("SELECTION SORT");
					resetSortStatistics();
					startTime = System.nanoTime();
					selectionSort();
					endTime = System.nanoTime();
					duration = endTime - startTime;
					printSortStatistics();
					// End selection sort
					
					// Start insertion sort
					if (sorted) {
						// Copy the original word set into a new array that will be sorted so that the original set's order is preserved
						for (int i = 0; i < numOfWords; i++) {
							sortedWordSet[i] = wordSet[i];
						}
					}
					System.out.println("INSERTION SORT");
					resetSortStatistics();
					startTime = System.nanoTime();
					insertionSort();
					endTime = System.nanoTime();
					duration = endTime - startTime;
					printSortStatistics();
					// End insertion sort
					
					// Start bubble sort
					if (sorted) {
						// Copy the original word set into a new array that will be sorted so that the original set's order is preserved
						for (int i = 0; i < numOfWords; i++) {
							sortedWordSet[i] = wordSet[i];
						}
					}
					System.out.println("BUBBLE SORT");
					resetSortStatistics();
					startTime = System.nanoTime();
					bubbleSort();
					endTime = System.nanoTime();
					duration = endTime - startTime;
					printSortStatistics();
					// End bubble sort
					
					// Start merge sort
					if (sorted) {
						// Copy the original word set into a new array that will be sorted so that the original set's order is preserved
						for (int i = 0; i < numOfWords; i++) {
							sortedWordSet[i] = wordSet[i];
						}
					}
					System.out.println("MERGE SORT");
					resetSortStatistics();
					startTime = System.nanoTime();
					mergeSort(sortedWordSet);
					endTime = System.nanoTime();
					duration = (endTime - startTime);
					printSortStatistics();
					// End merge sort
					
					// Start quick sort
					if (sorted) {
						// Copy the original word set into a new array that will be sorted so that the original set's order is preserved
						for (int i = 0; i < numOfWords; i++) {
							sortedWordSet[i] = wordSet[i];
						}
					}
					System.out.println("QUICK SORT");
					resetSortStatistics();
					startTime = System.nanoTime();
					quickSort(sortedWordSet, 0, sortedWordSet.length - 1);
					endTime = System.nanoTime();
					duration = endTime - startTime;
					printSortStatistics();
					// End quick sort
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
							if (sorted) {
								// Copy the original word set into a new array that will be sorted so that the original set's order is preserved
								for (int i = 0; i < numOfWords; i++) {
									sortedWordSet[i] = wordSet[i];
								}
							}
							System.out.println("SELECTION SORT");
							resetSortStatistics();
							startTime = System.nanoTime();
							selectionSort();
							endTime = System.nanoTime();
							duration = endTime - startTime;
							printSortStatistics();
							
							for (int i = 0; i < numOfWords; i++) {
								System.out.println(sortedWordSet[i].getWord());
							}
						break;
						case 2:
							if (sorted) {
								// Copy the original word set into a new array that will be sorted so that the original set's order is preserved
								for (int i = 0; i < numOfWords; i++) {
									sortedWordSet[i] = wordSet[i];
								}
							}
							System.out.println("INSERTION SORT");
							resetSortStatistics();
							startTime = System.nanoTime();
							insertionSort();
							endTime = System.nanoTime();
							duration = endTime - startTime;
							printSortStatistics();
							
							for (int i = 0; i < numOfWords; i++) {
								System.out.println(sortedWordSet[i].getWord());
							}
						break;
						case 3:
							if (sorted) {
								// Copy the original word set into a new array that will be sorted so that the original set's order is preserved
								for (int i = 0; i < numOfWords; i++) {
									sortedWordSet[i] = wordSet[i];
								}
							}
							System.out.println("BUBBLE SORT");
							resetSortStatistics();
							startTime = System.nanoTime();
							bubbleSort();
							endTime = System.nanoTime();
							duration = endTime - startTime;
							printSortStatistics();
							
							for (int i = 0; i < numOfWords; i++) {
								System.out.println(sortedWordSet[i].getWord());
							}
						break;
						case 4:
							if (sorted) {
								// Copy the original word set into a new array that will be sorted so that the original set's order is preserved
								for (int i = 0; i < numOfWords; i++) {
									sortedWordSet[i] = wordSet[i];
								}
							}
							System.out.println("MERGE SORT");
							resetSortStatistics();
							startTime = System.nanoTime();
							mergeSort(sortedWordSet);
							endTime = System.nanoTime();
							duration = (endTime - startTime);
							printSortStatistics();
							
							for (int i = 0; i < numOfWords; i++) {
								System.out.println(sortedWordSet[i].getWord());
							}
						break;
						case 5:
							if (sorted) {
								// Copy the original word set into a new array that will be sorted so that the original set's order is preserved
								for (int i = 0; i < numOfWords; i++) {
									sortedWordSet[i] = wordSet[i];
								}
							}
							System.out.println("QUICK SORT");
							resetSortStatistics();
							startTime = System.nanoTime();
							quickSort(sortedWordSet, 0, sortedWordSet.length - 1);
							endTime = System.nanoTime();
							duration = endTime - startTime;
							printSortStatistics();
							
							for (int i = 0; i < numOfWords; i++) {
								System.out.println(sortedWordSet[i].getWord());
							}
						break;
						case 6:

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
							System.out.println("What string would you like to search for?");
							System.out.println("Your entry: ");
							String toFindLinear = inConsole.next();
							int linearResult = linearSearch(toFindLinear);
							if (linearResult > -1) {
								System.out.println("The string was found in the array at index " + linearResult + ".  It appeared in the file " + sortedWordSet[linearResult].getCount() + " times.");
							} else {
								System.out.println("Sorry, that string is not in the array.");
							}
						break;
						case 2:
							if (sorted) {
								System.out.println("What string would you like to search for?");
								System.out.println("Your entry: ");
								String toFindBinary = inConsole.next();
								int binaryResult = binarySearch(toFindBinary);
								if (binaryResult > -1) {
									System.out.println("The string was found in the array at index " + binaryResult + ".  It appeared in the file " + sortedWordSet[binaryResult].getCount() + " times.");
								} else {
									System.out.println("Sorry, that string is not in the array.");
								}
							} else {
								System.out.println("Please go back and sort the array before running a binary search.");
							}
						break;
						case 3:
							if (sorted) {
								System.out.println("What string would you like to search for?");
								System.out.println("Your entry: ");
								String toFindQuadratic = inConsole.next();
								int quadraticResult = quadraticSearch(toFindQuadratic);
								if (quadraticResult > -1) {
									System.out.println("The string was found in the array at index " + quadraticResult + ".  It appeared in the file " + sortedWordSet[quadraticResult].getCount() + " times.");
								} else {
									System.out.println("Sorry, that string is not in the array.");
								}
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
		int minIndex = 0;
		for (int i = 0; i < sortedWordSet.length - 1; i++) {
			minIndex = i;
			for (int j = i + 1; j < sortedWordSet.length; j++) {
				String toCompareJ = sortedWordSet[j].getWord();
				comparisonCount++;
				if (toCompareJ.compareTo(sortedWordSet[minIndex].getWord()) < 0) {
					minIndex = j;
				}
			}

			comparisonCount++;
			if (sortedWordSet[minIndex].getWord().compareTo(sortedWordSet[i].getWord()) < 0) {
				Word temp = sortedWordSet[i];
				sortedWordSet[i] = sortedWordSet[minIndex];
				sortedWordSet[minIndex] = temp;
				swapCount++;
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
			int j = i;
			while (j > 0 && sortedWordSet[j - 1].getWord().compareTo(valueToSort) > 0) {
				comparisonCount++; // TODO: check if you need to add 1 or 2 comparisons outside of the while loop
				Word temp = sortedWordSet[j];
				sortedWordSet[j] = sortedWordSet[j - 1];
				sortedWordSet[j - 1] = temp;
				swapCount++;
				j--;
			}
			comparisonCount++; // TODO: add comment
		}
		sorted = true;
	}
	
	public static void bubbleSort() {
		boolean swapped;
		for (int i = sortedWordSet.length - 1; i >= 0; i--) {
			swapped = false;
			for (int j = 0; j < i; j++) {
				comparisonCount++;
				if (sortedWordSet[j].getWord().compareTo(sortedWordSet[j + 1].getWord()) > 0) {
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
		}
		sorted = true;
	}
	
	public static void mergeSort(Word[] inSet) {
		// https://www.youtube.com/watch?v=TzeBrDU-JaY
		
		int n = inSet.length;
		// Base case
		if (n < 2) {
			return;
		}
		int mid = n / 2; // Defines the index of the midpoint of the parent array
		// Create and fill the child arrays
		Word[] leftArray = new Word[mid];
		Word[] rightArray = new Word[n - mid];
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
	
	public static void merge(Word[] inLeftArray, Word[] inRightArray, Word[] inSet) {
		// https://www.youtube.com/watch?v=TzeBrDU-JaY
		
		int nLeft = inLeftArray.length;
		int nRight = inRightArray.length;
		int l = 0; // Represents the current index location in the left array
		int r = 0; // Represents the current index location in the right array
		int i = 0; // Represents the current index location in the 'inSet' array
		while (l < nLeft && r < nRight) {
			comparisonCount++;
			if (inLeftArray[l].getWord().compareTo(inRightArray[r].getWord()) < 0) {
				comparisonCount++;
				inSet[i] = inLeftArray[l];
				l++; // Move to the next spot in the left array
			} else if (inLeftArray[l].getWord().equals(inRightArray[r].getWord())) {
				comparisonCount++;
				inSet[i] = inLeftArray[l];
				i++;
				l++;
				inSet[i] = inRightArray[r];
				r++;
			} else {
				inSet[i] = inRightArray[r];
				swapCount++; // TODO: check if this is correct
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
		for (int i = start; i < end; i++) {
			comparisonCount++;
			if (inSet[i].getWord().compareTo(pivot) < 0){
				Word temp = inSet[partitionIndex];
				inSet[partitionIndex] = inSet[i];
				inSet[i] = temp;
				swapCount++; // TODO: check if this is correct
				partitionIndex++;
			}
		}
		Word temp = inSet[partitionIndex];
		inSet[partitionIndex] = inSet[end];
		inSet[end] = temp;
		swapCount++; // TODO: check if this is correct
		
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
		
		while (wordToFind.compareTo(sortedWordSet[high].getWord()) > 0) {
			comparisonCount++;
			/* System.out.println("high old is < sortedNumberSet.length; high = " + high); */
			if (high + range > sortedWordSet.length - 1) {
				high = sortedWordSet.length - 1;
			} else {
				high = high + range;
			}
			/* System.out.println("high new is < sortedNumberSet.length; high = " + high); */
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
	
	public static int linearSearch_fillingArray(Word[] inSet, String wordToFind, int filledLength) {
		boolean found = false;
		int foundAtIndex = 0;

		for (int i = 0; i < filledLength-1; i++) {
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
	
	public static void resetSortStatistics() {
		comparisonCount = 0;
		swapCount = 0;
		startTime = 0;
		endTime = 0;
	}
	
	public static void printSortStatistics() {
		System.out.println("Comparisons: " + comparisonCount);
		System.out.println("Swaps: " + swapCount);
		System.out.println("Duration: " + duration + " nanoseconds");
		System.out.println();
	}
	
	public static void resetSearchStatistics() {
		comparisonCount = 0;
		startTime = 0;
		endTime = 0;
	}
}