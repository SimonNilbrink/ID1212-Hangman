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

    public void connect(String host, int port) throws IOException{
        socket = new Socket();
        socket.connect(new InetSocketAddress(host,port),30000);
        toServer = new ObjectOutputStream(socket.getOutputStream());
        fromServer = new ObjectInputStream(socket.getInputStream());
        new Thread(new Listener()).start();
    }


    /**
     * Request the server to set up a knew game
     */
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
     *Send an Request with the type QUIT to tell the server to close its connection to client
     */
    public void quitGame(){
        try {
            sendGuess(new Request(QUIT));
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     *
     * Takes the Reequest created in the public functions and sends it to the server.
     * @param request the protocol used for requests
     */
    private void sendGuess(Request request){
        try {
            toServer.writeObject(request);
            toServer.flush();
            toServer.reset();
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
        boolean run = true;
        @Override
        public void run() {
            while(run) {
                try {
                    Response response = (Response) fromServer.readObject();
                    gameObserver.gameChanges(response);
                } catch (IOException e) {
                    gameObserver.connectionLost();
                    run = false;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

