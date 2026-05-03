package io.github.orca_175.server;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import io.github.orca_175.fighters.Fighters;

/**
 * The graphical scoreboard that displays the current points of each fighter in the match.
 */
public class Scoreboard extends JFrame {
    private JLabel[] fighterScores = {new JLabel("0"), new JLabel("0")};
    private Fighters fighters;

    Scoreboard(Fighters fighters) {
        this.fighters = fighters;

        this.setSize(new Dimension(400, 300));
        this.setLayout(new GridBagLayout());
        this.setTitle("Scoreboard");
        this.setLocationRelativeTo(null);

        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new FlowLayout());

        // Fighter 1 column panel
        JPanel fighterPanel = new JPanel();
        fighterPanel.setLayout(new GridLayout(0, 1));
        JLabel fighterLabel = new JLabel(this.fighters.ONE);
        fighterPanel.add(fighterLabel);
        fighterPanel.add(fighterScores[0]);
        rootPanel.add(fighterPanel);

        // Fighter 2 column panel
        fighterPanel = new JPanel();
        fighterPanel.setLayout(new GridLayout(0, 1));
        fighterLabel = new JLabel(this.fighters.TWO);
        fighterPanel.add(fighterLabel);
        fighterPanel.add(fighterScores[1]);
        rootPanel.add(fighterPanel);

        this.add(rootPanel);
        this.setVisible(true);
    }

    /**
     * Updates the scoreboard to reflect the current scores in fighterPoints.
     * @param fighterPoints A HashMap where the keys are the names of the fighters and the values are their scores.
     * Stores the points held by each fighter.
     */
    public void setScores(HashMap<String, Integer> fighterPoints) {
        fighterScores[0].setText(fighterPoints.getOrDefault(this.fighters.ONE, 0).toString());
        fighterScores[1].setText(fighterPoints.getOrDefault(this.fighters.TWO, 0).toString());
    }
}
