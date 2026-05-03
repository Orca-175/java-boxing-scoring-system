package Server;
import java.net.ServerSocket;
import java.net.Socket;

import ConnectionInfo.ConnectionInfo;
import ConnectionInfo.ServerConnectionInfoUI;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket;

        ConnectionInfo connectionInfo = new ConnectionInfo();
        new ServerConnectionInfoUI(connectionInfo);

        ScoreHandler scoreHandler = new ScoreHandler();

        try {
            serverSocket = new ServerSocket(connectionInfo.port);
            System.out.println("Server started!");

            while (true) {
                socket = serverSocket.accept();
                System.out.println("Socket connected!");
                new JudgeClientHandler(socket, scoreHandler).start();
            }

        } catch (Exception exception) {
            System.out.println("Something went wrong ScoreServer: " + exception);
        }
    }
}
