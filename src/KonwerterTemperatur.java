import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KonwerterTemperatur extends JFrame {

    private NoteEditorWindow noteEditorWindow;

    private JPanel panel;
    private JTextField wpiszTempCel;
    private JTextField wpiszTempFar;
    private JButton zamien1;
    private JButton zamien2;
    private JLabel celcjusze;
    private JLabel farenheity;
    private JLabel farenheitywyn;
    private JLabel celcjuszewyn;

    private JButton backButton;

    public KonwerterTemperatur(NoteEditorWindow noteEditorWindow) {

        super("Temperature converter");
        setSize(200, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);

        wpiszTempCel = new JTextField();
        wpiszTempFar = new JTextField();
        zamien1 = new JButton("C");
        zamien2 = new JButton("F");
        celcjusze = new JLabel("Celcjusze:");
        farenheity = new JLabel("Farenheity:");
        farenheitywyn = new JLabel();
        celcjuszewyn = new JLabel();
        backButton = new JButton("Back");

        wpiszTempCel.setBounds(10, 40, 50, 40);
        wpiszTempFar.setBounds(70, 40, 50, 40);
        zamien1.setBounds(10, 150, 60, 50);
        zamien2.setBounds(80, 150, 60, 50);
        celcjusze.setBounds(10, 10, 60, 40);
        farenheity.setBounds(80, 10, 65, 40);
        farenheitywyn.setBounds(80, 40, 50, 40);
        celcjuszewyn.setBounds(10, 40, 50, 40);
        backButton.setBounds(10, 240, 130, 30);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KonwerterTemperatur.this.dispose();
                noteEditorWindow.setVisible(true);
                KonwerterTemperatur.this.dispose();
            }
        });

        zamien1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double celsius = Double.parseDouble(wpiszTempCel.getText());
                    double fahrenheit = celsiusToFahrenheit(celsius);
                    wpiszTempFar.setText(String.valueOf(fahrenheit));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Enter the correct number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        zamien2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double fahrenheit = Double.parseDouble(wpiszTempFar.getText());
                    double celsius = fahrenheitToCelsius(fahrenheit);
                    wpiszTempCel.setText(String.valueOf(celsius));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Enter the correct number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(wpiszTempCel);
        panel.add(wpiszTempFar);
        panel.add(zamien1);
        panel.add(zamien2);
        panel.add(celcjusze);
        panel.add(farenheity);
        panel.add(farenheitywyn);
        panel.add(celcjuszewyn);
        panel.add(backButton);

        setVisible(true);
        add(panel);
    }

    private double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

}