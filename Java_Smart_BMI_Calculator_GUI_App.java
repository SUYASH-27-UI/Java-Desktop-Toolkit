import javax.swing.*;
import java.awt.*;

public class BmiCalculatorApp extends JFrame {
    private JTextField weightField, heightField;
    private JLabel resultLabel;

    public BmiCalculatorApp() {
        setTitle("Smart BMI Calculator");
        setSize(360, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("⚖️ SMART BMI CALCULATOR", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 12));
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
        calcBtn.addActionListener(e -> calculateBmi());
        add(calcBtn, gbc);

        resultLabel = new JLabel("BMI: --", JLabel.CENTER);
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setFont(new Font("Courier New", Font.BOLD, 12));
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

    private void calculateBmi() {
        try {
            double weight = Double.parseDouble(weightField.getText().trim());
            double heightCm = Double.parseDouble(heightField.getText().trim());

            if (heightCm <= 0 || weight <= 0) {
                resultLabel.setText("Values must be greater than zero");
                return;
            }

            double heightM = heightCm / 100.0;
            double bmi = weight / (heightM * heightM);

            String status = "";
            if (bmi < 18.5) status = "Underweight";
            else if (bmi < 25.0) status = "Normal Weight";
            else if (bmi < 30.0) status = "Overweight";
            else status = "Obese";

            resultLabel.setText(String.format("BMI: %.2f (%s)", bmi, status));
        } catch (Exception ex) {
            resultLabel.setText("Invalid input numbers!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BmiCalculatorApp::new);
    }
}