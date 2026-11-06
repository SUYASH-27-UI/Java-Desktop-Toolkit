import javax.swing.*;
import java.awt.*;

public class TipCalculatorApp extends JFrame {
    private JTextField billField, tipField, peopleField;
    private JLabel resultLabel;

    public TipCalculatorApp() {
        setTitle("Smart Tip & Bill Splitter");
        setSize(380, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("💵 SMART TIP & BILL SPLITTER", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; add(createLabel("Bill Amount ($):"), gbc);
        billField = new JTextField("100.0", 10);
        gbc.gridx = 1; add(billField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(createLabel("Tip Percentage (%):"), gbc);
        tipField = new JTextField("15", 10);
        gbc.gridx = 1; add(tipField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; add(createLabel("Number of People:"), gbc);
        peopleField = new JTextField("2", 10);
        gbc.gridx = 1; add(peopleField, gbc);

        JButton calcBtn = new JButton("Calculate Split");
        calcBtn.setBackground(new Color(76, 175, 80));
        calcBtn.setForeground(Color.WHITE);
        calcBtn.setFont(new Font("Arial", Font.BOLD, 10));
        calcBtn.setFocusPainted(false);
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        calcBtn.addActionListener(e -> calculateSplit());
        add(calcBtn, gbc);

        resultLabel = new JLabel("Tip: $0.00 | Total: $0.00", JLabel.CENTER);
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

    private void calculateSplit() {
        try {
            double bill = Double.parseDouble(billField.getText().trim());
            double tipPct = Double.parseDouble(tipField.getText().trim());
            int people = Integer.parseInt(peopleField.getText().trim());

            if (bill < 0 || tipPct < 0 || people <= 0) {
                resultLabel.setText("Invalid input values!");
                return;
            }

            double tipAmt = bill * (tipPct / 100.0);
            double totalBill = bill + tipAmt;
            double perPerson = totalBill / people;

            resultLabel.setText(String.format("<html>Tip: $%.2f | Total: $%.2f<br>Per Person: $%.2f</html>", tipAmt, totalBill, perPerson));
        } catch (Exception ex) {
            resultLabel.setText("Invalid number format!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TipCalculatorApp::new);
    }
}