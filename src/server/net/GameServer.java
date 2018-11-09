package server.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Establishes connection between client and server.
 */
public class GameServer {

    int portNr = 1337;

    public void serve() {
        try {
            ServerSocket listeningSocket = new ServerSocket(portNr);
            while (true) {
                Socket clientSocket = listeningSocket.accept();
                startClientHandler(clientSocket);
            }
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Is responsible to create a new clientHandler for a specific client.
     * @param clientSocket is the socket the new client will communicate through.
     */
    public void startClientHandler(Socket clientSocket){
        ClientHandler client = new ClientHandler(clientSocket);
        new Thread(client).start();

    }


}
