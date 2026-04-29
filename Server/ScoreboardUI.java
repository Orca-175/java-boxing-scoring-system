package Server;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Constants.Fighters;

public class ScoreboardUI extends JFrame {
    JLabel[] fighterScores = {new JLabel("0"), new JLabel("0")};

    public ScoreboardUI() {
        this.setSize(new Dimension(400, 300));
        this.setLayout(new GridBagLayout());
        this.setTitle("Scoreboard");

        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new FlowLayout());

        // Fighter 1 panel
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(0, 1));
        JLabel fighterLabel = new JLabel(Fighters.ONE);
        scorePanel.add(fighterLabel);
        scorePanel.add(fighterScores[0]);
        rootPanel.add(scorePanel);

        // Fighter 2 panel
        scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(0, 1));
        fighterLabel = new JLabel(Fighters.TWO);
        scorePanel.add(fighterLabel);
        scorePanel.add(fighterScores[1]);
        rootPanel.add(scorePanel);

        this.add(rootPanel);
        this.setVisible(true);
    }

    public void setScores(HashMap<String, Integer> fighterPoints) {
        fighterScores[0].setText(fighterPoints.getOrDefault(Fighters.ONE, 0).toString());
        fighterScores[1].setText(fighterPoints.getOrDefault(Fighters.TWO, 0).toString());
    }
}
