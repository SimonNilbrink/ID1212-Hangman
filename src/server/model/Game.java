package server.model;
import server.util.WordGenerator;

/**
 * This is the Game class that handles all the game logic for the Hangman game
 */
public class Game {

    private String word;
    private int failedAttempts;
    private String theWordSoFar[];
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
    }
}
