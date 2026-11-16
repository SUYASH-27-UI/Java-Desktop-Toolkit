import javax.swing.*;
import java.awt.*;

public class bmi_calculator_app extends JFrame {
    private JTextField weightField, heightField;
    private JLabel resultLabel;

    public bmi_calculator_app() {
        setTitle("Smart BMI Calculator");
        setSize(380, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("⚖️ SMART BMI CALCULATOR", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; add(createLabel("Weight (kg):"), gbc);
        weightField = new JTextField("70", 10);
        gbc.gridx = 1; add(weightField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(createLabel("Height (cm):"), gbc);
        heightField = new JTextField("175", 10);
        gbc.gridx = 1; add(heightField, gbc);

        JButton calcBtn = new JButton("Calculate BMI");
        calcBtn.setBackground(new Color(76, 175, 80));
        calcBtn.setForeground(Color.WHITE);
        calcBtn.setFont(new Font("Arial", Font.BOLD, 10));
        calcBtn.setFocusPainted(false);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        calcBtn.addActionListener(e -> calculateBMI());
        add(calcBtn, gbc);

        resultLabel = new JLabel("BMI: -- | Category: --", JLabel.CENTER);
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

    private void calculateBMI() {
        try {
            double weight = Double.parseDouble(weightField.getText().trim());
            double heightCm = Double.parseDouble(heightField.getText().trim());

            if (weight <= 0 || heightCm <= 0) {
                resultLabel.setText("Invalid weight or height!");
                return;
            }

            double heightM = heightCm / 100.0;
            double bmi = weight / (heightM * heightM);

            String category;
            if (bmi < 18.5) {
                category = "Underweight";
            } else if (bmi < 24.9) {
                category = "Normal Weight";
            } else if (bmi < 29.9) {
                category = "Overweight";
            } else {
                category = "Obese";
            }

            resultLabel.setText(String.format("<html>BMI: %.2f<br>Category: %s</html>", bmi, category));
        } catch (Exception ex) {
            resultLabel.setText("Invalid input format!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(bmi_calculator_app::new);
    }
}