package client.view;

import client.net.IGameObserver;
import common.Response;

public class GameView implements IGameObserver {

    @Override
    public void gameChanges(Response response) {
        int attemptsLeft = response.getAttemptsLeft();
        char[] theWordSoFar = response.getTheWordSoFar();
        int score = response.getTotalScore();

        String formatedWordSoFar = formatWordSoFar(theWordSoFar);
        String outputAttemptsLeft = String.format("You have %d attempts left ",attemptsLeft);
        String outputScore = String.format("Your score is %d",score);
        System.out.println();
        System.out.println(formatedWordSoFar);
        System.out.println(outputAttemptsLeft);
        System.out.println(outputScore);
        System.out.println("Guess: ");
    }

    private String formatWordSoFar(char[] wordSoFar){
        StringBuilder stringBuilder = new StringBuilder();
        for(char letter:wordSoFar){
            if(letter==0)
                stringBuilder.append("_");
            else
                stringBuilder.append(letter);
        }
        return stringBuilder.toString();
    }
}
