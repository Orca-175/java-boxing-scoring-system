package Server;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket;

        try {
            serverSocket = new ServerSocket(1337);
            System.out.println("Server started!");

            while (true) {
                socket = serverSocket.accept();
                System.out.println("Socket connected!");
                new ClientHandler(socket).start();
            }

        } catch (Exception exception) {
            System.out.println("Something went wrong ScoreServer: " + exception);
        }
    }
}
