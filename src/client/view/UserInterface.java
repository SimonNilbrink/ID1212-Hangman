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
        connect();
        while(true){

        }


    }


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
