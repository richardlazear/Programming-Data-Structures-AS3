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

		// Create the array
		Word[] wordSet = new Word[inFile.nextInt()];
		
		// Read in the file
		for (int i = 0; i < wordSet.length; i++) {
			String word = inFile.next();
			if (linearSearch(wordSet, word) >= 0) {
				
				// TODO: increment this word object's count in the other array
			} else {
				// TODO: add this word (with a count of 1) to the other array
			}
		}
		// End reading in the file
		
		System.out.println("--------------Menu--------------");
		System.out.println("1. Comparison Table of Sorts");
		System.out.println("2. Comparison Table of Searches");
		System.out.println("3. Run a single sort");
		System.out.println("4. Run a single search");
		System.out.println("5. Quit");
		System.out.print("Your selection: ");
		int menuSelection = inConsole.nextInt();
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
						
					break;
					case 2:
						
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
		
		inConsole.close();
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
				String temp = inSet[partitionIndex].getWord();
				inSet[partitionIndex].writeWord(inSet[i].getWord());
				inSet[i].writeWord(temp);
				partitionIndex++;
			}
		}
		String temp = inSet[partitionIndex].getWord();
		inSet[partitionIndex] = inSet[end];
		inSet[end].writeWord(temp);
		
		return partitionIndex;
	}
	
	// Method for Linear Search
	public static int linearSearch(Word[] inSet, String wordToFind) {
		boolean found = false;
		int foundAtIndex = 0;
		
		// Search each slot in the array by comparing the number to find against the current number in the array.
		for (int i = 0; i < inSet.length; i++) {
			if (wordToFind == inSet[i].getWord()) {
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
}