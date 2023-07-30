import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class NoteEditorWindow extends JFrame {

    private String login;

    private JPanel panel;
    private JLabel titleLabel;
    private JTextField titleField;
    private JTextArea bodyArea;
    private JButton saveButton;

    private JButton converterButton;

    public NoteEditorWindow(String login) {
        super("Note writer");
        this.login = login;
        KonwerterTemperatur konwerterTemperatur = new KonwerterTemperatur(this);

        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel = new JPanel();
        titleLabel = new JLabel("Note title:");
        titleField = new JTextField(20);
        bodyArea = new JTextArea(10, 30);
        saveButton = new JButton("Save");
        converterButton = new JButton("Temperature conventer");
        converterButton.setBounds(10, 200, 130, 30);
        converterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KonwerterTemperatur konwerterTemperatur = new KonwerterTemperatur(NoteEditorWindow.this);
                NoteEditorWindow.this.setVisible(false);
                konwerterTemperatur.setVisible(true);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String body = bodyArea.getText();

                if (title.isEmpty() || body.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "The title and content of the note must not be blank.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String fileName = JOptionPane.showInputDialog(panel, "Provide a file name for the note:");
                    String filePath = fileName + ".txt";

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                        writer.write("Author: " + login);
                        writer.newLine();
                        writer.write("Title: " + title);
                        writer.newLine();
                        writer.write("Body: " + body);
                        writer.newLine();

                        JOptionPane.showMessageDialog(panel, "The note was recorded.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        NoteEditorWindow.this.dispose();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(panel, "An error occurred while saving the note.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(new JScrollPane(bodyArea));
        panel.add(saveButton);
        panel.add(converterButton);

        add(panel);
        setVisible(true);
    }

}
