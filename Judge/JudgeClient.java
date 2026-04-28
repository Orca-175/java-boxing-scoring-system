package Judge;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class JudgeClient {
    Socket socket;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;

    JudgeClient() {
        try {
            this.socket = new Socket("localhost", 1337);
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        } catch (Exception e) {
            System.out.println("Something went wrong initializing JudgeClient: " + e);
        }
    }

    public void buttonDown(String fighter) {
        try {
            this.bufferedWriter.write(fighter);
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();

        } catch (Exception exception) {
            System.out.println("Something went wrong with JudgeClient.buttonDown(): " + exception);
        }
    }

    public void disconnect() {
        try {
            this.bufferedWriter.write("disconnect");
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
        } catch (Exception exception) {
            System.out.println("Something went wrong with JudgeClient.buttonDown(): " + exception);
        }
    }
}
