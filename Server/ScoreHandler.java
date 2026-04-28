package Server;
import java.util.ArrayList;
import java.util.HashMap;

import Constants.Fighters;

public class ScoreHandler {
    private static HashMap<String, Integer> fighterPoints = new HashMap<>();

    public static String scoreFor() {
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

    public static void printScores() {
        System.out.println("Fighter 1: " + fighterPoints.getOrDefault(Fighters.ONE, 0));
        System.out.println("Fighter 2: " + fighterPoints.getOrDefault(Fighters.TWO, 0));
    }
}
