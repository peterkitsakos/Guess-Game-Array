package guessme;

/**
 * An Array-based implementation of the Guess-A-Number game
 */
public class ArrayGame {

	// stores the next number to guess
	private int guess;
	private boolean gameOver = false;
	private int numberGuesses = 0;
	private int[] priorGuessesArr;
	private boolean[] eliminated = new boolean[10000];
	
	// TODO: declare additional data members, such as arrays that store
	// prior guesses, eliminated candidates etc.

	// NOTE: only primitive type arrays are allowed, such as int[], boolean[] etc.
	// You MAY NOT use any Collection type (such as ArrayList) provided by Java.
	
	/********************************************************
	 * NOTE: you are allowed to add new methods if necessary,
	 * but DO NOT remove any provided method, otherwise your
	 * code will fail the JUnit tests!
	 * Also DO NOT create any new Java files, as they will
	 * be ignored by the autograder!
	 *******************************************************/
	
	// ArrayGame constructor method
	public ArrayGame() {
		guess = 1000;
		for(int i = 0; i<999;i++) {
			eliminated[i] = true;
		}
	}
	
	// Resets data members and game state so we can play again
	public void reset() {
		numberGuesses = 0;
		guess = 1000;
		gameOver = false;
		for(int i=0;i<eliminated.length;i++) {
			eliminated[i] = false;
		}
		priorGuessesArr = null;
	}
	
	// Returns true if n is a prior guess; false otherwise.
	public boolean isPriorGuess(int n) {
		for(int i = 0;i<priorGuesses().length;i++) {
			if(n == priorGuesses()[i])
				return true;
		}
		
		return false;
	}
	
	// Returns the number of guesses so far.
	public int numGuesses() {
		return numberGuesses;
	}
	
	/**
	 * Returns the number of matches between integers a and b.
	 * You can assume that both are 4-digits long (i.e. between 1000 and 9999).
	 * The return value must be between 0 and 4.
	 * 
	 * A match is the same digit at the same location. For example:
	 *   1234 and 4321 have 0 match;
	 *   1234 and 1114 have 2 matches (1 and 4);
	 *   1000 and 9000 have 3 matches (three 0's).
	 */
	public static int numMatches(int a, int b) { // DO NOT remove the static qualifier
		int matches = 0;
		if((a % 10) == (b % 10)) {
			matches += 1;
		}
		if(((a % 100)/10) == ((b % 100)/10)) {
			matches += 1;
		}
		if(((a % 1000)/100) == ((b % 1000)/100)) {
			matches += 1;
		}
		if((a/1000) == (b/1000)){
			matches += 1;
		}
		return matches;
	}
	
	/**
	 * Returns true if the game is over; false otherwise.
	 * The game is over if the number has been correctly guessed
	 * or if all candidates have been eliminated.
	 */
	public boolean isOver() {
		return gameOver;
	}
	
	// Returns the guess number and adds it to the list of prior guesses.
	public int getGuess() {
		//Add guess to prior guesses
		numberGuesses++;
		int[] tempPriors = new int[numberGuesses];
		if(priorGuessesArr != null) {
			for(int i = 0;i<priorGuessesArr.length;i++) {
				tempPriors[i] = priorGuessesArr[i];
				System.out.println(priorGuessesArr[i]);
			}
			tempPriors[tempPriors.length-1] = guess;
			priorGuessesArr = tempPriors;
		}else {
			priorGuessesArr = new int[1];
			priorGuessesArr[0] = guess;
		}
		
		eliminated[guess] = true;
		
		return guess;
	}
	
	/**
	 * Updates guess based on the number of matches of the previous guess.
	 * If nmatches is 4, the previous guess is correct and the game is over.
	 * Check project description for implementation details.
	 * 
	 * Returns true if the update has no error; false if all candidates
	 * have been eliminated (indicating a state of error);
	 */
	public boolean updateGuess(int nmatches) {
		if(nmatches == 4) {
			gameOver = true;
			return true;
		}
		for(int i = 0;i<eliminated.length;i++) {
			if(numMatches(guess,i) != nmatches) {
				eliminated[i] = true;
			}
		}
		
		for(int i = guess+1;i<eliminated.length;i++) {
			if(!eliminated[i]) {
				guess = i;
				return true;
			}
		}
		
		return false;
	}
	
	// Returns the list of guesses so far as an integer array.
	// The size of the array must be the number of prior guesses.
	// Returns null if there has been no prior guess
	public int[] priorGuesses() {
		return priorGuessesArr;
	}
}
