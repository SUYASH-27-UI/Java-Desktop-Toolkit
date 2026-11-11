import javax.swing.*;
import java.awt.*;

public class Compound_Interest_Calculator extends JFrame {
    private JTextField principalField, rateField, timeField;
    private JLabel resultLabel;

    public Compound_Interest_Calculator() {
        setTitle("Smart Compound Interest Calculator");
        setSize(380, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("📈 COMPOUND INTEREST CALCULATOR", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; add(createLabel("Principal ($):"), gbc);
        principalField = new JTextField("5000", 10);
        gbc.gridx = 1; add(principalField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(createLabel("Annual Rate (%):"), gbc);
        rateField = new JTextField("7.5", 10);
        gbc.gridx = 1; add(rateField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; add(createLabel("Years:"), gbc);
        timeField = new JTextField("5", 10);
        gbc.gridx = 1; add(timeField, gbc);

        JButton calcBtn = new JButton("Calculate Interest");
        calcBtn.setBackground(new Color(76, 175, 80));
        calcBtn.setForeground(Color.WHITE);
        calcBtn.setFont(new Font("Arial", Font.BOLD, 10));
        calcBtn.setFocusPainted(false);
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        calcBtn.addActionListener(e -> calculateInterest());
        add(calcBtn, gbc);

        resultLabel = new JLabel("Total: $0.00 | Interest: $0.00", JLabel.CENTER);
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

    private void calculateInterest() {
        try {
            double p = Double.parseDouble(principalField.getText().trim());
            double r = Double.parseDouble(rateField.getText().trim()) / 100.0;
            double t = Double.parseDouble(timeField.getText().trim());

            if (p < 0 || r < 0 || t < 0) {
                resultLabel.setText("Values cannot be negative!");
                return;
            }

            double amount = p * Math.pow((1 + r), t);
            double interest = amount - p;

            resultLabel.setText(String.format("<html>Total: $%.2f<br>Interest: $%.2f</html>", amount, interest));
        } catch (Exception ex) {
            resultLabel.setText("Invalid input format!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Compound_Interest_Calculator::new);
    }
}