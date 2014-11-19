public class RecurringWord {
	// Instance Fields
	private static String word;
	private static int count;
	
	// Constructor
	public RecurringWord(String inWord, int inCount) {
		word = inWord;
		count = inCount;
	}
	
	// Methods
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