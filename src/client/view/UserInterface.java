package client.view;

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
        while(true){
            System.out.print("Guess: ");
            guess = input.nextLine();
            if(guess.length()==1)
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
        JTextField ip = new JTextField();
        JTextField port = new JTextField();
        Object[] fields = {
                "IP",ip,
                "PORT",port,
        };
        JOptionPane.showConfirmDialog(null,fields,"Connect to Server",JOptionPane.OK_CANCEL_OPTION);

        controller.connect(ip.getText(),Integer.parseInt(port.getText()));
    }
    private void guessLetter(){


    }
}
