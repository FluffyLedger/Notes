import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class RegisterWindow extends JFrame {


    private JPanel panel;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;

    static String Accounts = "accounts.txt";

    public RegisterWindow() {
        super("Registration");
        setSize(300, 250);

        panel = new JPanel();
        loginLabel = new JLabel("Provide login");
        passwordLabel = new JLabel("Provide password");
        confirmPasswordLabel = new JLabel("Repeat password");
        loginField = new JTextField(20);
        passwordField = new JPasswordField(20);
        confirmPasswordField = new JPasswordField(20);
        registerButton = new JButton("Register");

        panel.add(loginLabel);
        panel.add(loginField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);
        panel.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (!isLoginAvailable(login)) {
                    JOptionPane.showMessageDialog(panel, "The login provided is already taken.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(panel, "The passwords given do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!isPasswordSecure(password)) {
                    JOptionPane.showMessageDialog(panel, "Password must contain at least one uppercase letter, one lowercase letter and one special character.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                        registerAccount(login, password);
                    JOptionPane.showMessageDialog(panel, "The account has been created.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    RegisterWindow.this.dispose();
                    LoginWindow loginWindow = new LoginWindow();
                }
            }
        });

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private boolean isLoginAvailable(String login) {
        try (BufferedReader reader = new BufferedReader(new FileReader(Accounts))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String savedLogin = line.split(",")[0];
                if (login.equals(savedLogin)) {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean isPasswordSecure(String password) {
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }

            if (hasUppercase && hasLowercase && hasSpecialChar) {
                return true;
            }
        }

        return false;
    }

    private void registerAccount(String login, String password) {

        String accountData = login + "," + password;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Accounts, true))) {
            writer.write(accountData);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
