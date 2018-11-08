package server.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Handles all communication with one specific game client, makes sure to serve the client with what it requests.
 */
public class ClientHandler {

    private ObjectOutputStream toTheClient;
    private ObjectInputStream fromTheClient;
    private boolean isConnected;
    private Socket clientSocket;
    private int totalScore;

    public ClientHandler(Socket clientSocket) {
        isConnected = true;
        this.clientSocket = clientSocket;
        totalScore = 0;
    }

    public void receive(){
        try {
        fromTheClient = new ObjectInputStream(clientSocket.getInputStream());
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        while(isConnected){
            try {
                Object data = fromTheClient.readObject();

            }
            catch (ClassNotFoundException ex){
                ex.printStackTrace();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Responsible to send data to the client through the socket.
     */
    public void sendToClient() {
        try {
            toTheClient = new ObjectOutputStream(clientSocket.getOutputStream());
            toTheClient.writeObject("LÄGG IN OBJEKTET HÄR");
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
