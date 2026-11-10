import javax.swing.*;
import java.awt.*;

public class Discount_Calculator extends JFrame {
    private JTextField priceField, discountField;
    private JLabel resultLabel;

    public Discount_Calculator() {
        setTitle("Smart Discount Calculator");
        setSize(380, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("🏷️ SMART DISCOUNT CALCULATOR", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; add(createLabel("Original Price ($):"), gbc);
        priceField = new JTextField("200.0", 10);
        gbc.gridx = 1; add(priceField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(createLabel("Discount (%):"), gbc);
        discountField = new JTextField("20", 10);
        gbc.gridx = 1; add(discountField, gbc);

        JButton calcBtn = new JButton("Calculate Discount");
        calcBtn.setBackground(new Color(76, 175, 80));
        calcBtn.setForeground(Color.WHITE);
        calcBtn.setFont(new Font("Arial", Font.BOLD, 10));
        calcBtn.setFocusPainted(false);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        calcBtn.addActionListener(e -> calculateDiscount());
        add(calcBtn, gbc);

        resultLabel = new JLabel("Savings: $0.00 | Final: $0.00", JLabel.CENTER);
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setFont(new Font("Courier New", Font.BOLD, 11));
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

    private void calculateDiscount() {
        try {
            double price = Double.parseDouble(priceField.getText().trim());
            double discPct = Double.parseDouble(discountField.getText().trim());

            if (price < 0 || discPct < 0 || discPct > 100) {
                resultLabel.setText("Invalid price or discount!");
                return;
            }

            double savings = price * (discPct / 100.0);
            double finalPrice = price - savings;

            resultLabel.setText(String.format("<html>You Save: $%.2f<br>Final Price: $%.2f</html>", savings, finalPrice));
        } catch (Exception ex) {
            resultLabel.setText("Invalid input format!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Discount_Calculator::new);
    }
}