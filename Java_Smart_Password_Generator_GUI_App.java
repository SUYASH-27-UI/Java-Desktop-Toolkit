import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PasswordGeneratorApp extends JFrame {
    private JTextField lengthField, outputField;
    private JCheckBox upperBox, numBox, symBox;

    public PasswordGeneratorApp() {
        setTitle("Smart Password Generator");
        setSize(400, 340);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("🔑 SMART PASSWORD GENERATOR", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; add(createLabel("Password Length:"), gbc);
        lengthField = new JTextField("12", 10);
        gbc.gridx = 1; add(lengthField, gbc);

        upperBox = createCheckBox("Include Uppercase (A-Z)", true);
        numBox = createCheckBox("Include Numbers (0-9)", true);
        symBox = createCheckBox("Include Symbols (!@#$)", true);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        add(upperBox, gbc);
        gbc.gridy = 3; add(numBox, gbc);
        gbc.gridy = 4; add(symBox, gbc);

        JButton genBtn = new JButton("Generate Password");
        genBtn.setBackground(new Color(76, 175, 80));
        genBtn.setForeground(Color.WHITE);
        genBtn.setFont(new Font("Arial", Font.BOLD, 10));
        genBtn.setFocusPainted(false);
        gbc.gridy = 5;
        genBtn.addActionListener(e -> generatePassword());
        add(genBtn, gbc);

        outputField = new JTextField(20);
        outputField.setFont(new Font("Courier New", Font.BOLD, 12));
        outputField.setHorizontalAlignment(JTextField.CENTER);
        outputField.setEditable(false);
        outputField.setBackground(new Color(45, 45, 45));
        outputField.setForeground(Color.WHITE);
        gbc.gridy = 6;
        add(outputField, gbc);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(Color.WHITE);
        return lbl;
    }

    private JCheckBox createCheckBox(String text, boolean selected) {
        JCheckBox cb = new JCheckBox(text, selected);
        cb.setBackground(new Color(30, 30, 30));
        cb.setForeground(Color.WHITE);
        cb.setFocusPainted(false);
        return cb;
    }

    private void generatePassword() {
        try {
            int len = Integer.parseInt(lengthField.getText().trim());
            String chars = "abcdefghijklmnopqrstuvwxyz";
            if (upperBox.isSelected()) chars += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            if (numBox.isSelected()) chars += "0123456789";
            if (symBox.isSelected()) chars += "!@#$%^&*()_+";

            Random rnd = new Random();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) {
                sb.append(chars.charAt(rnd.nextInt(chars.length())));
            }
            outputField.setText(sb.toString());
        } catch (Exception ex) {
            outputField.setText("Invalid length!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PasswordGeneratorApp::new);
    }
}