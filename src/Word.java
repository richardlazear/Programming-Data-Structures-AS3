public class Word {
	// Instance Fields
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