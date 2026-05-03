package io.github.orca_175.client;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import io.github.orca_175.fighters.Fighters;

public class JudgeUI extends JFrame {
    Fighters fighters = new Fighters();
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
