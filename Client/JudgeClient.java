package Client;
import java.io.BufferedWriter;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import ConnectionInfo.ConnectionInfo;

public class JudgeClient {
    Socket socket;
    ObjectInputStream objectInputStream;
    BufferedWriter bufferedWriter;

    ConnectionInfo connectionInfo = new ConnectionInfo();

    JudgeClient() {
        new ConnectionInfoUI(connectionInfo);

        try {
            this.socket = new Socket(this.connectionInfo.hostname, this.connectionInfo.port);
        } catch (Exception exception) {
            System.out.println("Something went wrong in JudgeClient while connecting to server: " + exception);
        }

        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            String[] fighterNames = (String[]) objectInputStream.readObject();

            Fighters.ONE = fighterNames[0];
            Fighters.TWO = fighterNames[1];

        } catch (Exception exception) {
            System.out.println("Something went wrong in JudgeClient while reading fighter names from server: " 
                + exception);
        }

        try {
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
