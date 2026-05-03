package io.github.orca_175.server;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import io.github.orca_175.fighters.Fighters;

/**
 * A subclass of Thread where each instance receives and processes requests from their respective client.
 */
public class ClientHandler extends Thread {
    /**
     * A static list of Strings, where each index represents a single judge, while their values represent which fighter 
     * each judge has chosen to give a score.
     */
    private static ArrayList<String> judgeStates = new ArrayList<String>();
    private int judgeIndex;

    /**
     * An instance of Fighters, which contains the names of the participating fighters.
     */
    private Fighters fighters;

    /**
     * An instance of ScoreHandler, which is responsible for updating the scores of each fighter.
     */
    private ScoreHandler scoreHandler;

    /**
     * The connecting client's socket. 
     */
    private Socket socket;
    private BufferedReader bufferedReader;
    private ObjectOutputStream objectOutputStream;

    ClientHandler(Socket socket, Fighters fighters, ScoreHandler scoreHandler) {
        this.socket = socket;
        this.fighters = fighters;
        this.scoreHandler = scoreHandler;

        judgeStates.add("");
        judgeIndex = judgeStates.size() - 1;
    }

    /**
     * The run method of each instance waits for their respective client to send a request, which 
     * is a String that contains the fighter name selected by the judge. After receiving a fighter name, it adds it
     * to judgeStates and calls scoreHandler.registerScore() immediately after. After a short interval, it resets the
     * value of the judge's index in judgeStates.
     */
    public void run() {
        try {
            String[] fighterNames = {this.fighters.ONE, this.fighters.TWO};

            objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
            objectOutputStream.writeObject(fighterNames);
            objectOutputStream.flush();
        } catch (Exception exception) {
            System.out.println("Something went wrong while sending fighter names to client: " + exception);
        }

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            while (true) {
                String request = bufferedReader.readLine();

                if (request.equalsIgnoreCase("disconnect")) {
                    judgeStates.remove(judgeIndex);
                    System.out.println("judge " + judgeIndex + " has disconnected from the server.");
                    break;
                }

                judgeStates.set(judgeIndex, request);
                scoreHandler.registerScore();
                scoreHandler.refreshScores();
                Thread.sleep(500);
                judgeStates.set(judgeIndex, "");
            }

        } catch (Exception exception) {
            System.out.println("Something went wrong with ScoreClientHandler: " + exception);
        }
    }

    /**
     * A static method that returns the static judgeStates list for ScoreHandler.
     * @return A static list of Strings which is checked to determine which fighter should receive a score at any 
     * moment.
     */
    public static ArrayList<String> getJudgeStates() {
        return judgeStates;
    }
}
