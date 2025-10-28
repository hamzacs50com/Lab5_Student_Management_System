package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel {
    private JPanel loginRootPanel;
    private JTextField userText;
    private JPasswordField passwordText;
    private JButton loginButton;

    private MainFrame mainFrame;

    public LoginPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        // Pre-fill for easy testing
        userText.setText("admin");
        passwordText.setText("admin");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());

                if ("admin".equals(username) && "admin".equals(password)) {
                    // Tell the MainFrame to switch panels
                    mainFrame.showPanel("Dashboard");
                } else {
                    JOptionPane.showMessageDialog(loginRootPanel, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // This method lets the MainFrame get the panel.
    public JPanel getRootPanel() {
        return loginRootPanel;
    }
}
