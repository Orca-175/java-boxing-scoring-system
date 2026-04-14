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
    boolean[] judgeStates = {false, false, false};

    JLabel[] buttonLabels = {
        new JLabel(String.valueOf(judgeStates[0])), 
        new JLabel(String.valueOf(judgeStates[1])),
        new JLabel(String.valueOf(judgeStates[2])),
    };
    JButton[] judgeButtons = {
        new JButton("Trigger"), 
        new JButton("Trigger"),
        new JButton("Trigger"),
    };

    ScoreKeeper() {
        // Frame setup
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setSize(new Dimension(400, 300));

        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayout(1, 2));

        // Button panels
        JPanel[] buttonPanels = {
            new JPanel(), 
            new JPanel(),
            new JPanel(),
        };
        buttonPanels[0].setLayout(new GridLayout(2, 1));
        buttonPanels[0].add(this.buttonLabels[0]);
        buttonPanels[1].setLayout(new GridLayout(2, 1));
        buttonPanels[1].add(this.buttonLabels[1]);
        buttonPanels[2].setLayout(new GridLayout(2, 1));
        buttonPanels[2].add(this.buttonLabels[2]);

        // Button 1
        this.judgeButtons[0].addActionListener(e -> {
            ScoreButtonHandler scoreButtonHandler = new ScoreButtonHandler(
                this, 
                this.judgeButtons[0], 
                0
            );
            scoreButtonHandler.start();
        });
        buttonPanels[0].add(judgeButtons[0]);
        rootPanel.add(buttonPanels[0]);

        // Button 2
        this.judgeButtons[1].addActionListener(e -> {
            ScoreButtonHandler scoreButtonHandler = new ScoreButtonHandler(
                this, 
                this.judgeButtons[1], 
                1
            );
            scoreButtonHandler.start();
        });
        buttonPanels[1].add(judgeButtons[1]);
        rootPanel.add(buttonPanels[1]);

        // Button 3
        this.judgeButtons[2].addActionListener(e -> {
            ScoreButtonHandler scoreButtonHandler = new ScoreButtonHandler(
                this, 
                this.judgeButtons[2], 
                2
            );
            scoreButtonHandler.start();
        });
        buttonPanels[2].add(judgeButtons[2]);
        rootPanel.add(buttonPanels[2]);

        // isScoreRecorded label
        rootPanel.add(isScoreRegisteredLabel);

        frame.add(rootPanel);
        frame.setVisible(true);
    }

    void toggleJudge(int judge) {
        this.judgeStates[judge] = this.judgeStates[judge] ? false : true;
    }

    void buttonLabelSetText(int judge) {
        this.buttonLabels[judge].setText(String.valueOf(this.judgeStates[judge]));
    }

    void isScoreRegisteredLabelSetText() {
        this.isScoreRegisteredLabel.setText(String.valueOf(this.isScoreRegistered));
    }
}
