package client.view;

import client.controller.ConnectionErrorException;
import client.controller.Controller;

import javax.swing.*;

public class UserInterface implements Runnable{

    private Controller controller;



    public UserInterface(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        java.util.Scanner input = new java.util.Scanner(System.in);
        String guess;
        connect();
        System.out.println("'*quit' to exit game, '*new game' to starta new game.\n" +
                "If you start a new game under a running game, you loose a point. ");
        while(true){
            System.out.print("");
            guess = input.nextLine();

            if(guess.charAt(0)=='*') {
                switch (guess) {
                    case "*quit":
                        controller.quitGame();
                        break;
                    case "*new game":
                        controller.newGame();
                        break;
                    default:
                        System.out.println("Possible command is *quit and *new game");
                        break;
                }
            }
            else if(guess.length()==1)
                controller.guessLetter(guess.charAt(0));
            else
                controller.guessWord(guess);
        }
    }


    /**
     * Open an simple option panel to insert IP and PORT number to the Game server.
     * Rest of the game is in console.
     */
    private void connect(){
        boolean notConnected = true;
        while(notConnected) {
            JTextField ip = new JTextField();
            JTextField port = new JTextField();
            Object[] fields = {
                    "IP", ip,
                    "PORT", port,
            };
            JOptionPane.showConfirmDialog(null, fields, "Connect to Server", JOptionPane.OK_CANCEL_OPTION);
            try {
                controller.connect(ip.getText(), Integer.parseInt(port.getText()));
                notConnected = false;
                System.out.println("Connection done");
            }catch (ConnectionErrorException connectionError){
                System.out.println("No connection could be established");
            }
        }
    }
}
