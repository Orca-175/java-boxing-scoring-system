package ConnectionInfo;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ServerConnectionInfoUI extends JDialog {
    ConnectionInfo connectionInfo;

    public ServerConnectionInfoUI(ConnectionInfo connectionInfo) {
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
        formPanel.add(new JLabel("Port"));
        JTextField portTextField = new JTextField(10);
        portTextField.setSize(new Dimension(30, 10));
        formPanel.add(portTextField);
        rootPanel.add(formPanel);

        // Submit button
        JButton submit = new JButton("Submit");
        submit.addActionListener((_) -> {
            connectionInfo.port = Integer.parseInt(portTextField.getText());
            dispose();
        });
        rootPanel.add(submit);

        this.add(rootPanel);
        this.setVisible(true);
    }
}
