package server.net;

import common.Request;
import common.Response;
import server.model.Game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Handles all communication with one specific game client, makes sure to serve the client with what it requests.
 */
public class ClientHandler implements Runnable {

    private ObjectOutputStream toTheClient;
    private ObjectInputStream fromTheClient;
    private Game game = new Game();
    private boolean isConnected;
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        isConnected = true;
        this.clientSocket = clientSocket;

    }
    @Override
    public void run(){
        Response response;
        try {
            fromTheClient = new ObjectInputStream(clientSocket.getInputStream());
            toTheClient = new ObjectOutputStream(clientSocket.getOutputStream());
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        while(isConnected){
            try {
                Request request = (Request)fromTheClient.readObject();
                switch (request.getRequestType()){
                    case QUIT:
                        clientDisconnect();
                        break;
                    case NEW_GAME:
                        response = game.newGame();
                        sendToClient(response);
                        break;
                    case GUESSLETTER:
                        response = game.guessWithLetter(request.getLetterToGuess());
                        sendToClient(response);
                        break;
                    case GUESSWORD:
                        response = game.guessWithWord(request.getWordToGuess());
                        sendToClient(response);
                        break;
                    default :
                        System.out.println("no ");
                }
            }
            catch (ClassNotFoundException ex){
                ex.printStackTrace();
            }
            catch (IOException ex) {
                clientDisconnect();
            }
        }
    }

    /**
     * Responsible to send data to the client through the socket.
     */
    public void sendToClient(Response response) {
        try {
            toTheClient.writeObject(response);
            toTheClient.flush();
            toTheClient.reset();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void clientDisconnect(){
        try {
            clientSocket.close();
            isConnected = false;
        }
        catch(IOException ex){
            System.err.println("Failed to disconnect client");
        }
    }
}
