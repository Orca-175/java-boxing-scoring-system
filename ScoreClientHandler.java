import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ScoreClientHandler extends Thread {
    private static ArrayList<String> judgeStates = new ArrayList<String>();
    int judgeIndex;

    Socket socket;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;

    ScoreClientHandler(Socket socket) {
        this.socket = socket;
        judgeStates.add("");
        judgeIndex = judgeStates.size() - 1;
    }

    public void run() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));

            while (true) {
                String request = bufferedReader.readLine();

                if (request.equalsIgnoreCase("disconnect")) {
                    judgeStates.remove(judgeIndex);
                    System.out.println("judge " + judgeIndex + " has disconnected from the server.");
                    break;
                }

                judgeStates.set(judgeIndex, request);
                System.out.println("judge " + judgeIndex + ": "  + judgeStates.get(judgeIndex));
                System.out.println("Score registered for: " + ScoreHandler.scoreFor());
                ScoreHandler.printScores();
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
