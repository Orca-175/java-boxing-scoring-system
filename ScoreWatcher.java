public class ScoreWatcher {
    public static boolean shouldScoreRegister() {
        int buttonDownCount = 0;
        for (boolean judgeState : ScoreClientHandler.getJudgeStates()) {
            buttonDownCount = judgeState ? buttonDownCount + 1 : buttonDownCount;
        }

        return buttonDownCount >= 2 ? true : false;
    }
}
