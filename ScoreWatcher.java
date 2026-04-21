import java.util.ArrayList;

public class ScoreWatcher {
    public static String scoreFor() {
        ArrayList<String> judgeStates = ScoreClientHandler.getJudgeStates();

        for (String judgeState : judgeStates) {
            for (String secondJudgeState : judgeStates.subList(1, judgeStates.size())) {
                if (judgeState.equalsIgnoreCase(secondJudgeState)) {
                    return judgeState;
                }
            }
        }

        return "";
    }
}
