package Server;
import java.util.ArrayList;
import java.util.HashMap;

public class ScoreHandler {
    private HashMap<String, Integer> fighterPoints = new HashMap<>();
    private Scoreboard scoreboardUI = new Scoreboard();

    public String registerScore() {
        ArrayList<String> judgeStates = ClientHandler.getJudgeStates();
        int innerLoopStartIndex = 1;

        for (String judgeState : judgeStates) {
            for (String secondJudgeState : judgeStates.subList(innerLoopStartIndex, judgeStates.size())) {
                if (judgeState.equalsIgnoreCase(secondJudgeState)) {
                    fighterPoints.put(judgeState, fighterPoints.getOrDefault(judgeState, 0) + 1);
                    return judgeState;
                }
            }

            innerLoopStartIndex++;
        }

        return "";
    }

    public void refreshScores() {
        scoreboardUI.setScores(fighterPoints);
    }

    // Debug
    public void printScores() {
        System.out.println(Fighters.ONE + ": " + fighterPoints.getOrDefault(Fighters.ONE, 0));
        System.out.println(Fighters.TWO + ": " + fighterPoints.getOrDefault(Fighters.TWO, 0));
    }
}
