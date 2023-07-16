import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class LoginWindow extends JFrame {
    private final JPanel panel;
    private final JLabel loginLabel;
    private final JLabel passwordLabel;
    private final JTextField loginField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private JButton registerButton;

    static final String Accounts = "accounts.txt";

    public LoginWindow() {
        super("Logowanie");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        loginLabel = new JLabel("Login:");
        passwordLabel = new JLabel("Hasło:");
        loginField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Zaloguj");
        registerButton = new JButton("Rejestracja");

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginWindow.this.dispose();
                RegisterWindow registerWindow = new RegisterWindow();
            }
        });



        panel.add(loginLabel);
        panel.add(loginField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);
        add(panel);
        setVisible(true);
    }



}
