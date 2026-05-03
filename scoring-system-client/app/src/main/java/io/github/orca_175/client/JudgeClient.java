package io.github.orca_175.client;
import java.io.BufferedWriter;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import io.github.orca_175.connectioninfo.ConnectionInfo;
import io.github.orca_175.fighters.Fighters;

/**
 * The part of each JudgeUI that handles communications with the server.
 */
public class JudgeClient {
    private Socket socket;
    private ObjectInputStream objectInputStream;
    private BufferedWriter bufferedWriter;

    /**
     * The object that contains the fighter names passed from the JudgeUI.
     */
    private Fighters fighters;

    /**
     * An instance of ConnectionInfo which contains the hostname and port required to connect to the server.
     */
    private ConnectionInfo connectionInfo = new ConnectionInfo();

    /**
     * Connects to the server. After connecting, the JudgeClient will receive the fighter names set on the server which
     * is used to set the labels on the buttons of the UI.
     * @param fighters
     */
    JudgeClient(Fighters fighters) {
        this.fighters = fighters;

        new ConnectionInfoForm(connectionInfo);

        try {
            this.socket = new Socket(this.connectionInfo.hostname, this.connectionInfo.port);
        } catch (Exception exception) {
            System.out.println("Something went wrong in JudgeClient while connecting to server: " + exception);
        }

        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            String[] fighterNames = (String[]) objectInputStream.readObject();

            this.fighters.ONE = fighterNames[0];
            this.fighters.TWO = fighterNames[1];

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

    /**
     * Registers a button down action on one of the fighters. When fired, the method will send the fighter name of the 
     * button that was pressed to the server.
     * @param fighterName The fighter name that will be sent to the server.
     */
    public void buttonDown(String fighterName) {
        try {
            this.bufferedWriter.write(fighterName);
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();

        } catch (Exception exception) {
            System.out.println("Something went wrong with JudgeClient.buttonDown(): " + exception);
        }
    }

    /**
     * Disconnects a client from the server.
     */
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
