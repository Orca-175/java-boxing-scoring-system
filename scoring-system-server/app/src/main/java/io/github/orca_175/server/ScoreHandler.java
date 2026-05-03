package io.github.orca_175.server;
import java.util.ArrayList;
import java.util.HashMap;

import io.github.orca_175.fighters.Fighters;

/**
 * Watches and updates the scores of the participating fighters.
 */
public class ScoreHandler {
    /**
     * A HashMap where the keys are the names of the fighters and the values are their scores. Stores the points held
     * by each fighter.
     */
    private HashMap<String, Integer> fighterPoints = new HashMap<>();
    private Scoreboard scoreboard;

    /**
     * Creates an instance of ScoreBoard which displays the scores of the match.
     * @param fighters An instance of Fighters, which contains the names of the participating fighters.
     */
    ScoreHandler(Fighters fighters) {
        this.scoreboard = new Scoreboard(fighters);
    }

    /**
     * Checks the judgeStates list and gives a point to the fighter in fighterPoints if a score should be registered.
     */
    public void registerScore() {
        ArrayList<String> judgeStates = ClientHandler.getJudgeStates();
        int innerLoopStartIndex = 1;

        for (String judgeState : judgeStates) {
            for (String secondJudgeState : judgeStates.subList(innerLoopStartIndex, judgeStates.size())) {
                if (judgeState.equalsIgnoreCase(secondJudgeState)) {
                    fighterPoints.put(judgeState, fighterPoints.getOrDefault(judgeState, 0) + 1);
                }
            }

            innerLoopStartIndex++;
        }
    }

    /**
     * Calls scoreboard.setScores() to refresh the scoreboard to reflect the current points of the fighters.
     */
    public void refreshScores() {
        scoreboard.setScores(fighterPoints);
    }
}
