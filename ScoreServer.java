import java.net.ServerSocket;
import java.net.Socket;

public class ScoreServer {
    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket;

        try {
            serverSocket = new ServerSocket(1337);

            while (true) {
                socket = serverSocket.accept();
                new ScoreClientHandler(socket);
            }

        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }
}
