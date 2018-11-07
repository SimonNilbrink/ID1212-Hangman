package client.net;


import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Class that handle the connection from client to server.
 */
public class ConnectionHandler {

    private Socket socket;
    private Reader fromServer;
    private OutputStreamWriter toServer;


    public void connect(String host, int port) throws IOException {
        socket = new Socket();
        socket.connect(new InetSocketAddress(host,port));
        fromServer = new InputStreamReader(socket.getInputStream());
        toServer = new OutputStreamWriter(socket.getOutputStream());

        new Thread(new Listener()).start();
    }


    /**
     * Calls the function sendGuess with a guess formated after the decided protocol
     * @param letterToGuess
     */
    public void sendLetterToGuess(char letterToGuess){


    }

    /**
     * Calls the function sendGuess with a guess formated after the decided protocol
     * @param wordToGuess
     */
    public void sendWordToGuess(String wordToGuess){

    }


    //Will take the decided protocol as a parameter and send it to the server.
    private void sendGuess(){

    }


    /**
     * Inner class that are listening for communication from the server
     */
    private class Listener implements Runnable{
        @Override
        public void run() {

        }
    }

}
