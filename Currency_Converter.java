import javax.swing.*;
import java.awt.*;

public class Currency_Converter extends JFrame {
    private JTextField amountField;
    private JLabel resultLabel;
    private JComboBox<String> fromDropdown, toDropdown;

    public Currency_Converter() {
        setTitle("Smart Currency Converter");
        setSize(380, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("💱 SMART CURRENCY CONVERTER", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        String[] currencies = {"USD", "INR", "EUR", "GBP", "CAD"};

        gbc.gridwidth = 1;
        gbc.gridy = 1; add(createLabel("Amount:"), gbc);
        amountField = new JTextField("100", 10);
        gbc.gridx = 1; add(amountField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(createLabel("From:"), gbc);
        fromDropdown = new JComboBox<>(currencies);
        gbc.gridx = 1; add(fromDropdown, gbc);

        gbc.gridx = 0; gbc.gridy = 3; add(createLabel("To:"), gbc);
        toDropdown = new JComboBox<>(currencies);
        toDropdown.setSelectedIndex(1); // INR default
        gbc.gridx = 1; add(toDropdown, gbc);

        JButton convertBtn = new JButton("Convert Currency");
        convertBtn.setBackground(new Color(76, 175, 80));
        convertBtn.setForeground(Color.WHITE);
        convertBtn.setFont(new Font("Arial", Font.BOLD, 10));
        convertBtn.setFocusPainted(false);
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        convertBtn.addActionListener(e -> convertCurrency());
        add(convertBtn, gbc);

        resultLabel = new JLabel("Converted: --", JLabel.CENTER);
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

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountField.getText().trim());
            String from = (String) fromDropdown.getSelectedItem();
            String to = (String) toDropdown.getSelectedItem();

            // Base exchange rates relative to USD
            double fromRate = getRate(from);
            double toRate = getRate(to);

            double amountInUsd = amount / fromRate;
            double converted = amountInUsd * toRate;

            resultLabel.setText(String.format("Converted: %.2f %s", converted, to));
        } catch (Exception ex) {
            resultLabel.setText("Invalid amount input!");
        }
    }

    private double getRate(String curr) {
        switch (curr) {
            case "INR": return 83.0;
            case "EUR": return 0.92;
            case "GBP": return 0.79;
            case "CAD": return 1.35;
            default: return 1.0; // USD
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Currency_Converter::new);
    }
}