package Server;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FighterNamesForm extends JDialog {
    FighterNamesForm() {
        this.setSize(new Dimension(400, 300));
        this.setLayout(new GridBagLayout());
        this.setTitle("Fighter Names");
        this.setLocationRelativeTo(null);
        this.setModal(true);

        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayout(0, 1));

        // Fighter 1 text field
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        formPanel.add(new JLabel("Fighter 1"));
        JTextField fighterOneTextField = new JTextField(10);
        fighterOneTextField.setSize(new Dimension(30, 10));
        formPanel.add(fighterOneTextField);
        rootPanel.add(formPanel);

        // Fighter 2 text field
        formPanel = new JPanel();
        formPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        formPanel.add(new JLabel("Fighter 2"));
        JTextField fighterTwoTextField = new JTextField(10);
        fighterTwoTextField.setSize(new Dimension(30, 10));
        formPanel.add(fighterTwoTextField);
        rootPanel.add(formPanel);

        // Submit button
        formPanel = new JPanel();
        formPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton submit = new JButton("Submit");
        submit.addActionListener((_) -> {
            Fighters.ONE = fighterOneTextField.getText();
            Fighters.TWO = fighterTwoTextField.getText();
            dispose();
        });
        formPanel.add(submit);
        rootPanel.add(formPanel);

        this.add(rootPanel);
        this.setVisible(true);
    }
}
