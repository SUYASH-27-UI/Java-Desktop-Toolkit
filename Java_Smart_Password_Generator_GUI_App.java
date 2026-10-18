import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PasswordGeneratorApp extends JFrame {
    private JTextField passField;
    private JCheckBox upperBox, numBox, symBox;

    public PasswordGeneratorApp() {
        setTitle("Smart Password Generator");
        setSize(380, 340);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("🔑 SMART PASSWORD GENERATOR", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        upperBox = createCheckBox("Include Uppercase (A-Z)", true);
        numBox = createCheckBox("Include Numbers (0-9)", true);
        symBox = createCheckBox("Include Symbols (!@#$)", true);

        gbc.gridwidth = 2;
        gbc.gridy = 1; add(upperBox, gbc);
        gbc.gridy = 2; add(numBox, gbc);
        gbc.gridy = 3; add(symBox, gbc);

        JButton genBtn = new JButton("Generate Password");
        genBtn.setBackground(new Color(76, 175, 80));
        genBtn.setForeground(Color.WHITE);
        gbc.gridy = 4;
        genBtn.addActionListener(e -> generatePassword());
        add(genBtn, gbc);

        passField = new JTextField(16);
        passField.setFont(new Font("Courier New", Font.BOLD, 13));
        passField.setBackground(new Color(45, 45, 45));
        passField.setForeground(new Color(0, 255, 204));
        passField.setHorizontalAlignment(JTextField.CENTER);
        gbc.gridy = 5;
        add(passField, gbc);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JCheckBox createCheckBox(String text, boolean selected) {
        JCheckBox cb = new JCheckBox(text, selected);
        cb.setBackground(new Color(30, 30, 30));
        cb.setForeground(Color.WHITE);
        cb.setFocusPainted(false);
        return cb;
    }

    private void generatePassword() {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        if (upperBox.isSelected()) chars += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (numBox.isSelected()) chars += "0123456789";
        if (symBox.isSelected()) chars += "!@#$%^&*()_+";

        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        for (int i = 0; i < 12; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        passField.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PasswordGeneratorApp::new);
    }
}