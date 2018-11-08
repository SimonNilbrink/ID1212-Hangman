package server.startup;
import server.net.GameServer;
import server.controller.Controller;

public class Startup {

    public static void main(String [] args){
        Controller controller = new Controller();
        GameServer gameServer = new GameServer(controller);
        gameServer.serve();
    }
}
