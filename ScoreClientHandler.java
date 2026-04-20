import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ScoreClientHandler extends Thread {
    private static ArrayList<Boolean> judgeStates = new ArrayList<Boolean>();

    int judgeIndex;
    Socket socket;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;

    ScoreClientHandler(Socket socket) {
        this.socket = socket;
        judgeStates.add(false);
        judgeIndex = judgeStates.size() - 1;
    }

    public void run() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));

            while (true) {
                bufferedReader.readLine();
                judgeStates.set(judgeIndex, true);
                System.out.println("judge " + judgeIndex + ": "  + ScoreWatcher.shouldScoreRegister());
                System.out.println("shouldScoreRegister: " + judgeStates.get(judgeIndex));
                Thread.sleep(500);
                judgeStates.set(judgeIndex, false);
            }

        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    public static ArrayList<Boolean> getJudgeStates() {
        return judgeStates;
    }
}
