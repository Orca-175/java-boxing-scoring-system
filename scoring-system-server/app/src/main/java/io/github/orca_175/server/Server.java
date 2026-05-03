package io.github.orca_175.server;
import java.net.ServerSocket;
import java.net.Socket;

import io.github.orca_175.connectioninfo.ConnectionInfo;
import io.github.orca_175.fighters.Fighters;

/**
 * The main class of the program that waits for connections from clients and delegates them to instances 
 * of ClientHandler.
 */
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
