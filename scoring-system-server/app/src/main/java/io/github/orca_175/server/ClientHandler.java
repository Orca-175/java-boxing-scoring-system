package io.github.orca_175.server;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import io.github.orca_175.fighters.Fighters;

public class ClientHandler extends Thread {
    private static ArrayList<String> judgeStates = new ArrayList<String>();
    int judgeIndex;
    Fighters fighters;

    ScoreHandler scoreHandler;

    Socket socket;
    BufferedReader bufferedReader;
    ObjectOutputStream objectOutputStream;

    ClientHandler(Socket socket, Fighters fighters, ScoreHandler scoreHandler) {
        this.socket = socket;
        this.fighters = fighters;
        this.scoreHandler = scoreHandler;

        judgeStates.add("");
        judgeIndex = judgeStates.size() - 1;
    }

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

    public static ArrayList<String> getJudgeStates() {
        return judgeStates;
    }
}
