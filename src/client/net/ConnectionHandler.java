package client.net;


import common.Request;
import common.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

import static common.RequestType.*;

/**
 * Class that handle the connection from client to server.
 */
public class ConnectionHandler {

    private Socket socket;
    private ObjectInputStream fromServer;
    private ObjectOutputStream toServer;
    private IGameObserver gameObserver;

    public ConnectionHandler(IGameObserver gameObserver) {
        this.gameObserver = gameObserver;
    }

    public void connect(String host, int port){
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(host, port));
            fromServer = new ObjectInputStream(socket.getInputStream());
            toServer = new ObjectOutputStream(socket.getOutputStream());
            new Thread(new Listener()).start();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void newGame(){
        sendGuess(new Request(NEW_GAME));
    }

    /**
     * Calls the function sendGuess with a guess formated after the decided protocol
     * @param letterToGuess
     */
    public void sendLetterToGuess(char letterToGuess){
        Request request = new Request(GUESSLETTER);
        request.setLetterToGuess(letterToGuess);
        sendGuess(request);
    }

    /**
     * Calls the function sendGuess with a guess formated after the decided protocol
     * @param wordToGuess
     */
    public void sendWordToGuess(String wordToGuess){
        Request request = new Request(GUESSWORD);
        request.setWordToGuess(wordToGuess);
        sendGuess(request);
    }

    /**
     *
     */
    public void quitGame(){
        sendGuess(new Request(QUIT));
    }


    /**
     *
     * Takes the Reequest created in the public functions and sends it to the server.
     * @param request the protocol used for requests
     */
    private void sendGuess(Request request){
        try {
            toServer.writeObject(request);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     *
     * This function calculate the size of the Request object and prepend it to an byte array
     * that contains the object itself.
     * @param request the object to be sent
     * @return an array with the object and the length to be sent
     */
    private byte[] caluclateAndPrependSizeOfObjectToBeSent(Request request){
        //TODO
        return null;
    }


    /**
     * Inner class that are listening for communication from the server
     */
    private class Listener implements Runnable{
        @Override
        public void run() {
            while(true){
                try {
                    Response response = (Response) fromServer.readObject();
                    gameObserver.gameChanges(response);
                }catch (IOException e){
                    e.printStackTrace();
                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                }

            }
        }
    }

}
