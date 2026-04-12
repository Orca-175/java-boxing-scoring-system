public class ScoreWatcher {
    ScoreKeeper scoreKeeper;

    ScoreWatcher(ScoreKeeper scoreKeeper) {
        this.scoreKeeper = scoreKeeper;
    }

    public void updateScore() {
        if (this.scoreKeeper.firstJudge == true && this.scoreKeeper.secondJudge == true) {
            this.scoreKeeper.isScoreRegistered = true;
        } else {
            this.scoreKeeper.isScoreRegistered = false;
        }
        scoreKeeper.isScoreRegisteredLabelSetText();
    }
}
