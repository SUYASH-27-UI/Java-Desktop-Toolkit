import javax.swing.*;
import java.awt.*;

public class Loan_Emi_Calculator extends JFrame {
    private JTextField principalField, rateField, tenureField;
    private JLabel resultLabel;

    public Loan_Emi_Calculator() {
        setTitle("Smart Loan EMI Calculator");
        setSize(380, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("🏦 SMART LOAN EMI CALCULATOR", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; add(createLabel("Loan Amount ($):"), gbc);
        principalField = new JTextField("100000", 10);
        gbc.gridx = 1; add(principalField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(createLabel("Annual Rate (%):"), gbc);
        rateField = new JTextField("8.5", 10);
        gbc.gridx = 1; add(rateField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; add(createLabel("Tenure (Years):"), gbc);
        tenureField = new JTextField("5", 10);
        gbc.gridx = 1; add(tenureField, gbc);

        JButton calcBtn = new JButton("Calculate EMI");
        calcBtn.setBackground(new Color(76, 175, 80));
        calcBtn.setForeground(Color.WHITE);
        calcBtn.setFont(new Font("Arial", Font.BOLD, 10));
        calcBtn.setFocusPainted(false);
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        calcBtn.addActionListener(e -> calculateEMI());
        add(calcBtn, gbc);

        resultLabel = new JLabel("EMI: $0.00 | Total: $0.00", JLabel.CENTER);
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

    private void calculateEMI() {
        try {
            double p = Double.parseDouble(principalField.getText().trim());
            double annualRate = Double.parseDouble(rateField.getText().trim());
            double years = Double.parseDouble(tenureField.getText().trim());

            if (p <= 0 || annualRate < 0 || years <= 0) {
                resultLabel.setText("Invalid input values!");
                return;
            }

            double r = annualRate / (12 * 100);
            double n = years * 12;

            double emi = (p * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
            double totalPayment = emi * n;
            double totalInterest = totalPayment - p;

            resultLabel.setText(String.format("<html>EMI: $%.2f<br>Interest: $%.2f</html>", emi, totalInterest));
        } catch (Exception ex) {
            resultLabel.setText("Invalid number format!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Loan_Emi_Calculator::new);
    }
}