package Server;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket;
        ScoreHandler scoreHandler = new ScoreHandler();

        try {
            serverSocket = new ServerSocket(1337);
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
