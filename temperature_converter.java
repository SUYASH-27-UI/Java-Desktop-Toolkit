import javax.swing.*;
import java.awt.*;

public class temperature_converter extends JFrame {
    private JTextField tempField;
    private JComboBox<String> fromBox, toBox;
    private JLabel resultLabel;

    public temperature_converter() {
        setTitle("Smart Temperature Converter");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("🌡️ SMART TEMPERATURE CONVERTER", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        String[] scales = {"Celsius", "Fahrenheit", "Kelvin"};

        gbc.gridwidth = 1;
        gbc.gridy = 1; add(createLabel("Temperature:"), gbc);
        tempField = new JTextField("25", 10);
        gbc.gridx = 1; add(tempField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(createLabel("From Scale:"), gbc);
        fromBox = new JComboBox<>(scales);
        gbc.gridx = 1; add(fromBox, gbc);

        gbc.gridx = 0; gbc.gridy = 3; add(createLabel("To Scale:"), gbc);
        toBox = new JComboBox<>(scales);
        toBox.setSelectedIndex(1); // Fahrenheit default
        gbc.gridx = 1; add(toBox, gbc);

        JButton convBtn = new JButton("Convert Temperature");
        convBtn.setBackground(new Color(76, 175, 80));
        convBtn.setForeground(Color.WHITE);
        convBtn.setFont(new Font("Arial", Font.BOLD, 10));
        convBtn.setFocusPainted(false);
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        convBtn.addActionListener(e -> convertTemp());
        add(convBtn, gbc);

        resultLabel = new JLabel("Converted: --", JLabel.CENTER);
        resultLabel.setForeground(new Color(0, 255, 204));
        resultLabel.setFont(new Font("Courier New", Font.BOLD, 12));
        gbc.gridy = 5;
        add(resultLabel, gbc);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(Color.WHITE);
        return lbl;
    }

    private void convertTemp() {
        try {
            double val = Double.parseDouble(tempField.getText().trim());
            String from = (String) fromBox.getSelectedItem();
            String to = (String) toBox.getSelectedItem();

            double celsius;
            if ("Fahrenheit".equals(from)) {
                celsius = (val - 32.0) * (5.0 / 9.0);
            } else if ("Kelvin".equals(from)) {
                celsius = val - 273.15;
            } else {
                celsius = val;
            }

            if (celsius < -273.15) {
                resultLabel.setText("Error: Below absolute zero!");
                return;
            }

            double converted;
            String unit;
            if ("Fahrenheit".equals(to)) {
                converted = (celsius * (9.0 / 5.0)) + 32.0;
                unit = "°F";
            } else if ("Kelvin".equals(to)) {
                converted = celsius + 273.15;
                unit = "K";
            } else {
                converted = celsius;
                unit = "°C";
            }

            resultLabel.setText(String.format("Converted: %.2f %s", converted, unit));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Error: Invalid number!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(temperature_converter::new);
    }
}