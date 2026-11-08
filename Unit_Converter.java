import javax.swing.*;
import java.awt.*;

public class Unit_Converter extends JFrame {
    private JTextField inputField;
    private JLabel resultLabel;
    private JComboBox<String> fromDropdown, toDropdown;

    public Unit_Converter() {
        setTitle("Smart Unit Converter");
        setSize(380, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("📏 SMART LENGTH UNIT CONVERTER", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        String[] units = {"Meters", "Kilometers", "Feet", "Inches", "Centimeters"};

        gbc.gridwidth = 1;
        gbc.gridy = 1; add(createLabel("Value:"), gbc);
        inputField = new JTextField("1", 10);
        gbc.gridx = 1; add(inputField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(createLabel("From:"), gbc);
        fromDropdown = new JComboBox<>(units);
        gbc.gridx = 1; add(fromDropdown, gbc);

        gbc.gridx = 0; gbc.gridy = 3; add(createLabel("To:"), gbc);
        toDropdown = new JComboBox<>(units);
        toDropdown.setSelectedIndex(2); // Feet
        gbc.gridx = 1; add(toDropdown, gbc);

        JButton convertBtn = new JButton("Convert Units");
        convertBtn.setBackground(new Color(76, 175, 80));
        convertBtn.setForeground(Color.WHITE);
        convertBtn.setFont(new Font("Arial", Font.BOLD, 10));
        convertBtn.setFocusPainted(false);
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        convertBtn.addActionListener(e -> convertUnits());
        add(convertBtn, gbc);

        resultLabel = new JLabel("Result: --", JLabel.CENTER);
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setFont(new Font("Courier New", Font.BOLD, 11));
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

    private void convertUnits() {
        try {
            double val = Double.parseDouble(inputField.getText().trim());
            String from = (String) fromDropdown.getSelectedItem();
            String to = (String) toDropdown.getSelectedItem();

            double valInMeters = switch (from) {
                case "Kilometers" -> val * 1000.0;
                case "Feet" -> val * 0.3048;
                case "Inches" -> val * 0.0254;
                case "Centimeters" -> val * 0.01;
                default -> val;
            };

            double result = switch (to) {
                case "Kilometers" -> valInMeters / 1000.0;
                case "Feet" -> valInMeters / 0.3048;
                case "Inches" -> valInMeters / 0.0254;
                case "Centimeters" -> valInMeters / 0.01;
                default -> valInMeters;
            };

            resultLabel.setText(String.format("Result: %.4f %s", result, to));
        } catch (Exception ex) {
            resultLabel.setText("Invalid input!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Unit_Converter::new);
    }
}