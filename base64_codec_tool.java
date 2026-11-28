import javax.swing.*;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class base64_codec_tool extends JFrame {
    private JTextArea inputArea, outputArea;

    public base64_codec_tool() {
        setTitle("Smart Base64 Encoder & Decoder");
        setSize(460, 390);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        JLabel title = new JLabel("🔁 SMART BASE64 CODEC TOOL", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0; gbc.weighty = 0;
        add(title, gbc);

        inputArea = new JTextArea("Hello Developer 2026", 4, 30);
        inputArea.setFont(new Font("Courier New", Font.PLAIN, 11));
        inputArea.setBackground(new Color(45, 45, 45));
        inputArea.setForeground(Color.WHITE);
        inputArea.setCaretColor(new Color(0, 255, 204));
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        gbc.gridy = 1; gbc.weighty = 1.0;
        add(new JScrollPane(inputArea), gbc);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        btnPanel.setBackground(new Color(30, 30, 30));

        JButton encodeBtn = createButton("Encode Base64", new Color(76, 175, 80));
        JButton decodeBtn = createButton("Decode Base64", new Color(33, 150, 243));

        encodeBtn.addActionListener(e -> encodeText());
        decodeBtn.addActionListener(e -> decodeText());

        btnPanel.add(encodeBtn);
        btnPanel.add(decodeBtn);

        gbc.gridy = 2; gbc.weighty = 0;
        add(btnPanel, gbc);

        outputArea = new JTextArea(4, 30);
        outputArea.setFont(new Font("Courier New", Font.BOLD, 11));
        outputArea.setBackground(new Color(45, 45, 45));
        outputArea.setForeground(new Color(0, 255, 204));
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setEditable(false);
        gbc.gridy = 3; gbc.weighty = 1.0;
        add(new JScrollPane(outputArea), gbc);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 10));
        btn.setFocusPainted(false);
        return btn;
    }

    private void encodeText() {
        String raw = inputArea.getText();
        if (raw.isEmpty()) {
            outputArea.setText("Error: Encode karne ke liye text enter karein!");
            return;
        }
        String encoded = Base64.getEncoder().encodeToString(raw.getBytes(StandardCharsets.UTF_8));
        outputArea.setText(encoded);
    }

    private void decodeText() {
        String raw = inputArea.getText().trim();
        if (raw.isEmpty()) {
            outputArea.setText("Error: Decode karne ke liye Base64 string enter karein!");
            return;
        }
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(raw);
            outputArea.setText(new String(decodedBytes, StandardCharsets.UTF_8));
        } catch (IllegalArgumentException ex) {
            outputArea.setText("Error: Invalid Base64 input!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(base64_codec_tool::new);
    }
}