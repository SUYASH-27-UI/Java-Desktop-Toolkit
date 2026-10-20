import javax.swing.*;
import java.awt.*;

public class TemperatureConverterApp extends JFrame {
    private JTextField inputField;
    private JLabel resultLabel;
    private JComboBox<String> unitDropdown;

    public TemperatureConverterApp() {
        setTitle("Smart Temperature Converter");
        setSize(360, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("🌡️ TEMPERATURE CONVERTER", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; add(createLabel("Temperature Value:"), gbc);
        inputField = new JTextField("25", 10);
        gbc.gridx = 1; add(inputField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(createLabel("Convert From:"), gbc);
        String[] units = {"Celsius to Fahrenheit", "Fahrenheit to Celsius", "Celsius to Kelvin"};
        unitDropdown = new JComboBox<>(units);
        gbc.gridx = 1; add(unitDropdown, gbc);

        JButton convertBtn = new JButton("Convert");
        convertBtn.setBackground(new Color(76, 175, 80));
        convertBtn.setForeground(Color.WHITE);
        convertBtn.setFont(new Font("Arial", Font.BOLD, 10));
        convertBtn.setFocusPainted(false);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        convertBtn.addActionListener(e -> convertTemperature());
        add(convertBtn, gbc);

        resultLabel = new JLabel("Result: --", JLabel.CENTER);
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setFont(new Font("Courier New", Font.BOLD, 12));
        gbc.gridy = 4;
        add(resultLabel, gbc);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(Color.WHITE);
        return lbl;
    }

    private void convertTemperature() {
        try {
            double val = Double.parseDouble(inputField.getText());
            int choice = unitDropdown.getSelectedIndex();
            double res = 0;
            String unit = "";

            if (choice == 0) {
                res = (val * 9.0 / 5.0) + 32;
                unit = "°F";
            } else if (choice == 1) {
                res = (val - 32) * 5.0 / 9.0;
                unit = "°C";
            } else if (choice == 2) {
                res = val + 273.15;
                unit = "K";
            }

            resultLabel.setText(String.format("Result: %.2f %s", res, unit));
        } catch (Exception ex) {
            resultLabel.setText("Invalid input number!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TemperatureConverterApp::new);
    }
}