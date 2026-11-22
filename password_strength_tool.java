import javax.swing.*;
import java.awt.*;
import java.security.SecureRandom;

public class password_strength_tool extends JFrame {
    private JTextField passField, lenField;
    private JLabel resultLabel;
    private final SecureRandom secureRandom = new SecureRandom();

    public password_strength_tool() {
        setTitle("Smart Password Strength & Generator");
        setSize(430, 340);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("🔑 SMART PASSWORD TOOL", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; add(createLabel("Password:"), gbc);
        passField = new JTextField("DevPass@2026", 18);
        passField.setFont(new Font("Courier New", Font.BOLD, 11));
        gbc.gridx = 1; add(passField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(createLabel("Length (6-32):"), gbc);
        lenField = new JTextField("14", 8);
        lenField.setFont(new Font("Courier New", Font.BOLD, 11));
        gbc.gridx = 1; add(lenField, gbc);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        btnPanel.setBackground(new Color(30, 30, 30));

        JButton checkBtn = createButton("Check Strength", new Color(33, 150, 243));
        JButton genBtn = createButton("Generate Strong", new Color(76, 175, 80));

        checkBtn.addActionListener(e -> checkStrength());
        genBtn.addActionListener(e -> generatePassword());

        btnPanel.add(checkBtn);
        btnPanel.add(genBtn);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(btnPanel, gbc);

        resultLabel = new JLabel("Strength: -- | Score: --/4", JLabel.CENTER);
        resultLabel.setForeground(new Color(0, 255, 204));
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

    private JButton createButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 10));
        btn.setFocusPainted(false);
        return btn;
    }

    private void checkStrength() {
        String pwd = passField.getText();
        if (pwd.isEmpty()) {
            resultLabel.setText("Error: Enter or generate a password!");
            return;
        }

        int score = 0;
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        if (pwd.length() >= 8) {
            score++;
        }

        for (int i = 0; i < pwd.length(); i++) {
            char ch = pwd.charAt(i);
            if (Character.isUpperCase(ch)) {
                hasUpper = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }

        if (hasUpper) score++;
        if (hasDigit) score++;
        if (hasSpecial) score++;

        String rating;
        if (score <= 1) {
            rating = "Weak";
        } else if (score <= 3) {
            rating = "Medium";
        } else {
            rating = "Strong";
        }

        resultLabel.setText(String.format("<html>Strength: %s<br>Complexity Score: %d/4</html>", rating, score));
    }

    private void generatePassword() {
        try {
            int length = Integer.parseInt(lenField.getText().trim());
            if (length < 6 || length > 32) {
                resultLabel.setText("Error: Length must be between 6 and 32!");
                return;
            }

            String pool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*";
            StringBuilder sb = new StringBuilder(length);

            for (int i = 0; i < length; i++) {
                int idx = secureRandom.nextInt(pool.length());
                sb.append(pool.charAt(idx));
            }

            passField.setText(sb.toString());
            checkStrength();
        } catch (NumberFormatException ex) {
            resultLabel.setText("Error: Invalid length number!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(password_strength_tool::new);
    }
}