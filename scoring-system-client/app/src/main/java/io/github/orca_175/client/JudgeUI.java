package io.github.orca_175.client;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import io.github.orca_175.fighters.Fighters;

/**
 * The graphical interface of the judges. Includes two buttons that represent each fighter. When clicked, each button
 * will send a request to the server to register a point for their respective fighter.
 */
public class JudgeUI extends JFrame {
    /**
     * A class which contains the names of the participating fighters. Used to label the buttons of the UI and also 
     * passed ito JudgeClient.
     */
    private Fighters fighters = new Fighters();

    /**
     * An instance of JudgeClient which handles communications with the server.
     */
    JudgeClient judgeClient = new JudgeClient(fighters);

    JudgeUI() {
        this.setSize(new Dimension(400, 300));
        this.setLayout(new GridBagLayout());
        this.setTitle("Judge UI");
        this.setLocationRelativeTo(null);

        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new FlowLayout());

        // First button
        JButton judgeButton = new JButton(this.fighters.ONE);
        judgeButton.addActionListener((event) -> {
            judgeClient.buttonDown(this.fighters.ONE);
        });
        rootPanel.add(judgeButton);

        // Second button
        judgeButton = new JButton(this.fighters.TWO);
        judgeButton.addActionListener((event) -> {
            judgeClient.buttonDown(this.fighters.TWO);
        });
        rootPanel.add(judgeButton);

        this.add(rootPanel);
        this.setVisible(true);
    }
}
