package server.net;

import server.controller.Controller;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Establishes connection between client and server.
 */
public class GameServer {

    int portNr = 1010;
    Controller controller;

    public GameServer(Controller controller) {
        this.controller = controller;
    }

    public void serve() {
        try {
            ServerSocket listeningSocket = new ServerSocket(portNr);
            while (true) {
                Socket clientSocket = listeningSocket.accept();
                startClientHandler(clientSocket);
            }
        }
        catch(IOException ex) {
            System.err.println("Server fail");
        }
    }

    /**
     * Is responsible to create a new clientHandler for a specific client.
     * @param clientSocket is the socket the new client will communicate through.
     */
    public void startClientHandler(Socket clientSocket){
        ClientHandler client = new ClientHandler(clientSocket);
        client.receive();

    }


}
