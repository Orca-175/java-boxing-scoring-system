package Server;
import java.util.ArrayList;
import java.util.HashMap;

import Fighters.Fighters;

public class ScoreHandler {
    private HashMap<String, Integer> fighterPoints = new HashMap<>();
    private Fighters fighters;
    private Scoreboard scoreboardUI;

    ScoreHandler(Fighters fighters) {
        this.fighters = fighters;
        this.scoreboardUI = new Scoreboard(fighters);
    }

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
        System.out.println(this.fighters.ONE + ": " + fighterPoints.getOrDefault(this.fighters.ONE, 0));
        System.out.println(this.fighters.TWO + ": " + fighterPoints.getOrDefault(this.fighters.TWO, 0));
    }
}
