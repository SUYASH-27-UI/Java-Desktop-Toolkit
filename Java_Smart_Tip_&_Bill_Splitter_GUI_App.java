import javax.swing.*;
import java.awt.*;

public class TipCalculatorApp extends JFrame {
    private JTextField billField, tipField, splitField;
    private JLabel resultLabel;

    public TipCalculatorApp() {
        setTitle("Smart Bill & Tip Splitter");
        setSize(360, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("💰 BILL & TIP SPLITTER", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; add(createLabel("Bill Amount ($):"), gbc);
        billField = new JTextField("100.0", 10);
        gbc.gridx = 1; add(billField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(createLabel("Tip Percentage (%):"), gbc);
        tipField = new JTextField("15", 10);
        gbc.gridx = 1; add(tipField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; add(createLabel("Split Between:"), gbc);
        splitField = new JTextField("4", 10);
        gbc.gridx = 1; add(splitField, gbc);

        JButton calcBtn = new JButton("Calculate Split");
        calcBtn.setBackground(new Color(76, 175, 80));
        calcBtn.setForeground(Color.WHITE);
        calcBtn.setFont(new Font("Arial", Font.BOLD, 10));
        calcBtn.setFocusPainted(false);
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        calcBtn.addActionListener(e -> calculateSplit());
        add(calcBtn, gbc);

        resultLabel = new JLabel("Per Person: $0.00", JLabel.CENTER);
        resultLabel.setForeground(Color.WHITE);
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

    private void calculateSplit() {
        try {
            double bill = Double.parseDouble(billField.getText());
            double tipPercent = Double.parseDouble(tipField.getText());
            int people = Integer.parseInt(splitField.getText());

            if (people <= 0) {
                resultLabel.setText("People count must be > 0");
                return;
            }

            double totalTip = bill * (tipPercent / 100.0);
            double totalBill = bill + totalTip;
            double perPerson = totalBill / people;

            resultLabel.setText(String.format("Per Person: $\%.2f (Total:$%.2f)", perPerson, totalBill));
        } catch (Exception ex) {
            resultLabel.setText("Invalid input numbers!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TipCalculatorApp::new);
    }
}