package io.github.orca_175.server;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import io.github.orca_175.connectioninfo.ConnectionInfo;

/**
 * A form that prompts the user for the port from which the server will listen for connections from clients.
 */
public class ConnectionInfoForm extends JDialog {
    ConnectionInfo connectionInfo;

    public ConnectionInfoForm(ConnectionInfo connectionInfo) {
        this.connectionInfo = connectionInfo;

        this.setSize(new Dimension(400, 300));
        this.setLayout(new GridBagLayout());
        this.setTitle("Connection Info");
        this.setLocationRelativeTo(null);
        this.setModal(true);

        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayout(0, 1));

        // Port text field
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        formPanel.add(new JLabel("Port"));
        JTextField portTextField = new JTextField(10);
        portTextField.setSize(new Dimension(30, 10));
        formPanel.add(portTextField);
        rootPanel.add(formPanel);

        // Submit button
        formPanel = new JPanel();
        formPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton submit = new JButton("Submit");
        submit.addActionListener((event) -> {
            this.connectionInfo.port = Integer.parseInt(portTextField.getText());
            dispose();
        });
        formPanel.add(submit);
        rootPanel.add(formPanel);

        this.add(rootPanel);
        this.setVisible(true);
    }
}
