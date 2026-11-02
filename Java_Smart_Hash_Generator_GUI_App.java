import javax.swing.*;
import java.awt.*;
import java.security.MessageDigest;

public class HashGeneratorApp extends JFrame {
    private JTextField inputField;
    private JTextArea resultArea;

    public HashGeneratorApp() {
        setTitle("Smart Hash Generator");
        setSize(480, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("🔐 SMART HASH GENERATOR (MD5 / SHA-256)", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; add(createLabel("Input Text:"), gbc);
        inputField = new JTextField("Hello World", 22);
        gbc.gridx = 1; add(inputField, gbc);

        JButton genBtn = new JButton("Generate Hashes");
        genBtn.setBackground(new Color(76, 175, 80));
        genBtn.setForeground(Color.WHITE);
        genBtn.setFont(new Font("Arial", Font.BOLD, 10));
        genBtn.setFocusPainted(false);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        genBtn.addActionListener(e -> generateHashes());
        add(genBtn, gbc);

        resultArea = new JTextArea(6, 30);
        resultArea.setFont(new Font("Courier New", Font.PLAIN, 10));
        resultArea.setBackground(new Color(45, 45, 45));
        resultArea.setForeground(new Color(0, 255, 204));
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(resultArea);
        gbc.gridy = 3;
        add(scrollPane, gbc);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(Color.WHITE);
        return lbl;
    }

    private void generateHashes() {
        String text = inputField.getText().trim();
        if (text.isEmpty()) {
            resultArea.setText("Error: Input field is empty!");
            return;
        }

        String md5 = computeHash(text, "MD5");
        String sha256 = computeHash(text, "SHA-256");

        resultArea.setText("MD5 Hash:\n" + md5 + "\n\nSHA-256 Hash:\n" + sha256);
    }

    private String computeHash(String input, String algorithm) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] encodedhash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            return "Error computing hash: " + ex.getMessage();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HashGeneratorApp::new);
    }
}