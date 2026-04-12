import javax.swing.JButton;

public class ScoreButtonHandler extends Thread {
    ScoreKeeper scoreKeeper;
    JButton button;
    int judge;

    ScoreButtonHandler(ScoreKeeper scoreKeeper, JButton button, int judge) {
        this.scoreKeeper = scoreKeeper;
        this.button = button;
        this.judge = judge;
    }

    public void run() {
        try {
            scoreKeeper.toggleJudge(judge);
            scoreKeeper.buttonLabelSetText(judge);
            this.button.setEnabled(false);
            this.scoreKeeper.scoreWatcher.updateScore();

            Thread.sleep(500);

            scoreKeeper.toggleJudge(judge);
            scoreKeeper.buttonLabelSetText(judge);
            this.button.setEnabled(true);
            this.scoreKeeper.scoreWatcher.updateScore();
        } catch (Exception exception) {
            System.out.println("Something went wrong: " + exception);
        }
    }
}
