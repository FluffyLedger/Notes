import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class LoginWindow extends JFrame {
    private final JPanel panel;
    private final JLabel loginLabel;
    private final JLabel passwordLabel;
    private final JTextField loginField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private JButton registerButton;

    static final String Accounts = "accounts.txt";
    static final String NoteDir = "notes";

    public LoginWindow() {
        super("Log in");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        loginLabel = new JLabel("Login:");
        passwordLabel = new JLabel("Password:");
        loginField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Log in");
        registerButton = new JButton("Register");

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginWindow.this.dispose();
                RegisterWindow registerWindow = new RegisterWindow();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText();
                String password = new String(passwordField.getPassword());

                if (isLoginValid(login, password)) {
                    showNoteEditorWindow(login);
                    LoginWindow.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(panel, "Incorrect login or password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
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

    private boolean isLoginValid(String login, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(Accounts))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] accountData = line.split(",");
                String savedLogin = accountData[0];
                String savedPassword = accountData[1];

                if (login.equals(savedLogin) && password.equals(savedPassword)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void showNoteEditorWindow(String login) {
        NoteEditorWindow noteEditorWindow = new NoteEditorWindow(login);
    }




}
