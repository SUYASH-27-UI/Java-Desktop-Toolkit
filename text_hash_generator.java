import javax.swing.*;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class text_hash_generator extends JFrame {
    private JTextField inputField;
    private JComboBox<String> algoBox;
    private JTextArea outputArea;

    public text_hash_generator() {
        setTitle("Smart Text Hash Generator");
        setSize(450, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("🔐 SMART TEXT HASH GENERATOR", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; add(createLabel("Input Text:"), gbc);
        inputField = new JTextField("Hello World", 18);
        gbc.gridx = 1; add(inputField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(createLabel("Algorithm:"), gbc);
        String[] algos = {"SHA-256", "SHA-1", "MD5"};
        algoBox = new JComboBox<>(algos);
        gbc.gridx = 1; add(algoBox, gbc);

        JButton genBtn = new JButton("Generate Hash");
        genBtn.setBackground(new Color(76, 175, 80));
        genBtn.setForeground(Color.WHITE);
        genBtn.setFont(new Font("Arial", Font.BOLD, 10));
        genBtn.setFocusPainted(false);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        genBtn.addActionListener(e -> generateHash());
        add(genBtn, gbc);

        outputArea = new JTextArea(4, 28);
        outputArea.setFont(new Font("Courier New", Font.BOLD, 11));
        outputArea.setBackground(new Color(45, 45, 45));
        outputArea.setForeground(new Color(0, 255, 204));
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(outputArea);
        gbc.gridy = 4;
        add(scrollPane, gbc);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(Color.WHITE);
        return lbl;
    }

    private void generateHash() {
        String text = inputField.getText();
        if (text.isEmpty()) {
            outputArea.setText("Error: Please enter text to hash.");
            return;
        }

        String algo = (String) algoBox.getSelectedItem();
        try {
            MessageDigest md = MessageDigest.getInstance(algo);
            byte[] hashBytes = md.digest(text.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            outputArea.setText(hexString.toString());
        } catch (Exception ex) {
            outputArea.setText("Error generating hash: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(text_hash_generator::new);
    }
}