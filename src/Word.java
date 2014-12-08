/************************************************************************************************************************************************************
 This class is an object that stores only 2 fields: a string for a word from a file, and an integer for the number of times
 that word appears in the file.  This class is used in the program so that both of these fields can be stored in a single
 array slot in the main class.
 ************************************************************************************************************************************************************/


public class Word {
	// Fields
	public String word;
	public int count;
	
	// Constructor
	public Word(String inWord) {
		word = inWord;
		count = 1;
	}
	
	// Methods
	public void writeWord(String inWord) {
		word = inWord;
	}
	
	public void addToCount() {
		count++;
	}
	
	public String getWord() {
		return word;
	}
	
	public int getCount() {
		return count;
	}
}