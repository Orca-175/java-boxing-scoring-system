package io.github.orca_175.client;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

/**
 * The main class of the program which creates an instance of JudgeUI.
 */
public class Judge {
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
