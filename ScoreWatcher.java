public class ScoreWatcher {
    ScoreKeeper scoreKeeper;

    ScoreWatcher(ScoreKeeper scoreKeeper) {
        this.scoreKeeper = scoreKeeper;
    }

    public void updateScore() {
        int buttonDownCount = 0;
        for (boolean judge : this.scoreKeeper.judgeStates) {
            buttonDownCount = judge ? buttonDownCount + 1 : buttonDownCount;
        }

        this.scoreKeeper.isScoreRegistered = buttonDownCount > 1 ? true : false;
        scoreKeeper.isScoreRegisteredLabelSetText();
    }
}
