package server.model;
import server.util.WordGenerator;

/**
 * This is the Game class that handles all the game logic for the Hangman game
 */
public class Game {

    private String word;
    private int failedAttempts;
    private char theWordSoFar[];
    private int totalScore;

    /**
     * creates an instance
     */
    public Game() {
    }

    public void newGame(){
        pickAWord();
    }
    public void pickAWord() {
        this.word = WordGenerator.getWord();
        this.failedAttempts = word.length();
    }

    public void guessWithLetter(char guess) {
        boolean isWrongGuess = true;

        for (int i = 0; i <= this.word.length(); i++) {
            if(guess == word.charAt(i)) {
               theWordSoFar[i] = guess;
               isWrongGuess = false;
            }
        }
        if (isWrongGuess) {
            failedAttempts--;
        }
    }

    public void guessWithWord(String guess) {

        if(this.word.equals(guess)) {
            //you guessed right
        }
        else {
            failedAttempts--;
        }
    }

}
