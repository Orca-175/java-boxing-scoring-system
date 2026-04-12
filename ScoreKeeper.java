import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreKeeper {
    boolean isScoreRegistered = false;
    JLabel isScoreRegisteredLabel = new JLabel(String.valueOf(isScoreRegistered));

    // Watches button states and determines if a score should be registered
    ScoreWatcher scoreWatcher = new ScoreWatcher(this); 

    // Button states
    boolean firstJudge = false;
    boolean secondJudge = false;

    JLabel firstButtonLabel = new JLabel(String.valueOf(firstJudge));
    JButton firstButton = new JButton("Trigger");

    JLabel secondButtonLabel = new JLabel(String.valueOf(secondJudge));
    JButton secondButton = new JButton("Trigger");

    ScoreKeeper() {
        // Frame setup
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setSize(new Dimension(400, 300));

        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayout(1, 2));

        // Button 1 and label
        JPanel firstButtonPanel = new JPanel();
        firstButtonPanel.setLayout(new GridLayout(2, 1));
        firstButtonPanel.add(this.firstButtonLabel);

        this.firstButton.addActionListener(e -> {
            ScoreButtonHandler scoreButtonHandler = new ScoreButtonHandler(this, this.firstButton, 1);
            scoreButtonHandler.start();
        });
        firstButtonPanel.add(firstButton);
        rootPanel.add(firstButtonPanel);

        // Button 2 and label
        JPanel secondButtonPanel = new JPanel();
        secondButtonPanel.setLayout(new GridLayout(2, 1));
        secondButtonPanel.add(this.secondButtonLabel);

        this.secondButton.addActionListener(e -> {
            ScoreButtonHandler scoreButtonHandler = new ScoreButtonHandler(this, this.secondButton, 2);
            scoreButtonHandler.start();
        });
        secondButtonPanel.add(secondButton);
        rootPanel.add(secondButtonPanel);

        // isScoreRecorded label
        rootPanel.add(isScoreRegisteredLabel);

        frame.add(rootPanel);
        frame.setVisible(true);
    }

    void toggleJudge(int judge) {
        if (judge == 1) {
            this.firstJudge = this.firstJudge ? false : true;
        } else if (judge == 2) {
            this.secondJudge = this.secondJudge ? false : true;
        }
    }

    void buttonLabelSetText(int judge) {
        if (judge == 1) {
            this.firstButtonLabel.setText(String.valueOf(this.firstJudge));
        } else if (judge == 2) {
            this.secondButtonLabel.setText(String.valueOf(this.secondJudge));
        }
    }

    void isScoreRegisteredLabelSetText() {
        this.isScoreRegisteredLabel.setText(String.valueOf(this.isScoreRegistered));
    }
}
