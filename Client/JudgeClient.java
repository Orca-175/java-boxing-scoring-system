package Client;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import ConnectionInfo.ConnectionInfo;
import ConnectionInfo.JudgeConnectionInfoUI;

public class JudgeClient {
    Socket socket;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;

    ConnectionInfo connectionInfo = new ConnectionInfo();

    JudgeClient() {
        new JudgeConnectionInfoUI(connectionInfo);

        try {
            this.socket = new Socket(this.connectionInfo.hostname, this.connectionInfo.port);
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        } catch (Exception exception) {
            System.out.println("Something went wrong initializing JudgeClient: " + exception);
        }
    }

    public void buttonDown(String fighterName) {
        try {
            this.bufferedWriter.write(fighterName);
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
