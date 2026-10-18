import javax.swing.*;
import java.awt.*;

public class BmiCalculatorApp extends JFrame {
    private JTextField weightField, heightField;
    private JLabel resultLabel;

    public BmiCalculatorApp() {
        setTitle("Smart BMI Calculator");
        setSize(350, 320);
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
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        calcBtn.addActionListener(e -> calculateBmi());
        add(calcBtn, gbc);

        resultLabel = new JLabel("Enter your metrics above", JLabel.CENTER);
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 11));
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
            double w = Double.parseDouble(weightField.getText());
            double h = Double.parseDouble(heightField.getText()) / 100.0;
            double bmi = w / (h * h);
            String status = bmi < 18.5 ? "Underweight" : bmi < 25 ? "Normal Weight" : "Overweight";
            resultLabel.setText(String.format("BMI: %.2f (%s)", bmi, status));
        } catch (Exception ex) {
            resultLabel.setText("Invalid input numbers!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BmiCalculatorApp::new);
    }
}