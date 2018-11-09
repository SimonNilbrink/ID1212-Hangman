package client.view;

import client.net.IGameObserver;
import common.Response;

public class GameView implements IGameObserver {

    @Override
    public void gameChanges(Response response) {
        int attemptsLeft = response.getAttemptsLeft();
        String theWordSoFar = formatWordSoFar(response.getTheWordSoFar());
        int score = response.getTotalScore();

        System.out.println();
        if (!response.isDone()) {
            String outputAttemptsLeft = String.format("You have %d attempts left ",attemptsLeft);
            String outputScore = String.format("Your score is %d",score);
            System.out.println(theWordSoFar);
            System.out.println(outputAttemptsLeft);
            System.out.println(outputScore);
            System.out.print("Guess: ");
        }else{
            if(attemptsLeft<0){
                System.out.println("Congratulation, you won\n" +
                        "The right word was "+theWordSoFar+"\n"+
                        "Your score is "+score+"\n"+
                        "*new game for new game, *quit to exit");
            }else{
                System.out.println("You loose\n" +
                        "Your score is "+score+"\n"+
                        "'*new game' for new game, '*quit' to exit");
            }
            System.out.print("");
        }
    }

    @Override
    public void connectionLost() {
        System.out.println("Connection lost");
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
