package Client;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Constants.Fighters;

public class JudgeUI extends JFrame {
    JudgeClient judgeClient = new JudgeClient();

    JudgeUI() {
        this.setSize(new Dimension(400, 300));
        this.setLayout(new GridBagLayout());
        this.setTitle("Judge UI");
        this.setLocationRelativeTo(null);

        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new FlowLayout());

        // First button
        JButton judgeButton = new JButton(Fighters.ONE);
        judgeButton.addActionListener((_) -> {
            judgeClient.buttonDown(Fighters.ONE);
        });
        rootPanel.add(judgeButton);

        // Second button
        judgeButton = new JButton(Fighters.TWO);
        judgeButton.addActionListener((_) -> {
            judgeClient.buttonDown(Fighters.TWO);
        });
        rootPanel.add(judgeButton);

        this.add(rootPanel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        JudgeUI judgeUI = new JudgeUI();
        judgeUI.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent event) {
                judgeUI.judgeClient.disconnect();
                ((JFrame)(event.getComponent())).dispose();
            }
        });
    }
}
