package Server;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Scoreboard extends JFrame {
    JLabel[] fighterScores = {new JLabel("0"), new JLabel("0")};

    public Scoreboard() {
        this.setSize(new Dimension(400, 300));
        this.setLayout(new GridBagLayout());
        this.setTitle("Scoreboard");
        this.setLocationRelativeTo(null);

        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new FlowLayout());

        // Fighter 1 column panel
        JPanel fighterPanel = new JPanel();
        fighterPanel.setLayout(new GridLayout(0, 1));
        JLabel fighterLabel = new JLabel(Fighters.ONE);
        fighterPanel.add(fighterLabel);
        fighterPanel.add(fighterScores[0]);
        rootPanel.add(fighterPanel);

        // Fighter 2 column panel
        fighterPanel = new JPanel();
        fighterPanel.setLayout(new GridLayout(0, 1));
        fighterLabel = new JLabel(Fighters.TWO);
        fighterPanel.add(fighterLabel);
        fighterPanel.add(fighterScores[1]);
        rootPanel.add(fighterPanel);

        this.add(rootPanel);
        this.setVisible(true);
    }

    public void setScores(HashMap<String, Integer> fighterPoints) {
        fighterScores[0].setText(fighterPoints.getOrDefault(Fighters.ONE, 0).toString());
        fighterScores[1].setText(fighterPoints.getOrDefault(Fighters.TWO, 0).toString());
    }
}
