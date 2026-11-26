import javax.swing.*;
import java.awt.*;

public class tip_bill_splitter extends JFrame {
    private JTextField billField, tipField, peopleField;
    private JLabel resultLabel;

    public tip_bill_splitter() {
        setTitle("Smart Tip & Bill Splitter");
        setSize(420, 340);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("🧾 SMART TIP & BILL SPLITTER", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; add(createLabel("Bill Amount ($):"), gbc);
        billField = new JTextField("120.00", 10);
        billField.setFont(new Font("Courier New", Font.BOLD, 11));
        gbc.gridx = 1; add(billField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(createLabel("Tip (%):"), gbc);
        tipField = new JTextField("15", 10);
        tipField.setFont(new Font("Courier New", Font.BOLD, 11));
        gbc.gridx = 1; add(tipField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; add(createLabel("Split Between:"), gbc);
        peopleField = new JTextField("4", 10);
        peopleField.setFont(new Font("Courier New", Font.BOLD, 11));
        gbc.gridx = 1; add(peopleField, gbc);

        JButton calcBtn = new JButton("Calculate Split");
        calcBtn.setBackground(new Color(76, 175, 80));
        calcBtn.setForeground(Color.WHITE);
        calcBtn.setFont(new Font("Arial", Font.BOLD, 10));
        calcBtn.setFocusPainted(false);
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        calcBtn.addActionListener(e -> calculateSplit());
        add(calcBtn, gbc);

        resultLabel = new JLabel("Tip: $0.00 | Total: $0.00 | Per Person: $0.00", JLabel.CENTER);
        resultLabel.setForeground(new Color(0, 255, 204));
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
                resultLabel.setText("Error: Invalid amounts or people count!");
                return;
            }

            double tipAmount = bill * (tipPct / 100.0);
            double totalBill = bill + tipAmount;
            double perPerson = totalBill / people;

            resultLabel.setText(String.format(
                "<html>Tip: $%.2f | Total: $%.2f<br>Per Person (%d): $%.2f</html>",
                tipAmount, totalBill, people, perPerson
            ));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Error: Enter valid numbers!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(tip_bill_splitter::new);
    }
}