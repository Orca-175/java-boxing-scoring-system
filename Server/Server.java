package Server;
import java.net.ServerSocket;
import java.net.Socket;

import Fighters.Fighters;
import ConnectionInfo.ConnectionInfo;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket;

        ConnectionInfo connectionInfo = new ConnectionInfo();
        new ConnectionInfoForm(connectionInfo);

        Fighters fighters = new Fighters();
        new FighterNamesForm(fighters);

        ScoreHandler scoreHandler = new ScoreHandler(fighters);

        try {
            serverSocket = new ServerSocket(connectionInfo.port);
            System.out.println("Server started!");

            while (true) {
                socket = serverSocket.accept();
                System.out.println("Socket connected!");
                new ClientHandler(socket, fighters, scoreHandler).start();
            }

        } catch (Exception exception) {
            System.out.println("Something went wrong ScoreServer: " + exception);
        }
    }
}
