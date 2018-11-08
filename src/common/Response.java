package common;

public class Response {


    private boolean isDone = false;
    private char theWordSoFar[];
    private int totalScore;
    private int attemptsLeft;
    private String word;

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public char[] getTheWordSoFar() {
        return theWordSoFar;
    }

    public void setTheWordSoFar(char[] theWordSoFar) {
        this.theWordSoFar = theWordSoFar;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public void setAttemptsLeft(int attemptsLeft) {
        this.attemptsLeft = attemptsLeft;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
