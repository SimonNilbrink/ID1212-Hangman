package client.controller;

import client.net.ConnectionHandler;

public class Controller {

    private final ConnectionHandler connectionHandler;

    public Controller(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    public void connect(String ip, int port){
        connectionHandler.connect(ip,port);
    }


    public void guessLetter(char guessedLetter){
        connectionHandler.sendLetterToGuess(guessedLetter);

    }


}
