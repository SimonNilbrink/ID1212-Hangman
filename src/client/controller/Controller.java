package client.controller;

import client.net.ConnectionHandler;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class Controller {

    private final ConnectionHandler connectionHandler;

    public Controller(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    public void connect(String ip, int port){
        connectionHandler.connect(ip,port);
    }


    public void guessLetter(char guessedLetter){
        CompletableFuture.runAsync(() -> {
            connectionHandler.sendLetterToGuess(guessedLetter);
           });

    }

    public void guessWord(String wordToGuess){
        CompletableFuture.runAsync(() -> {
                connectionHandler.sendWordToGuess(wordToGuess);
            });

    }


}
