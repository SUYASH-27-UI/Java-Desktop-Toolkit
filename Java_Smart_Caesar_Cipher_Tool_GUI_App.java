import javax.swing.*;
import java.awt.*;

public class CaesarCipherApp extends JFrame {
    private JTextArea inputArea, outputArea;
    private JTextField shiftField;

    public CaesarCipherApp() {
        setTitle("Smart Caesar Cipher Tool");
        setSize(480, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("🔐 SMART CAESAR CIPHER TOOL", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; add(createLabel("Input Text:"), gbc);
        
        inputArea = new JTextArea(4, 25);
        inputArea.setFont(new Font("Courier New", Font.PLAIN, 11));
        inputArea.setBackground(new Color(45, 45, 45));
        inputArea.setForeground(Color.WHITE);
        inputArea.setCaretColor(Color.WHITE);
        inputArea.setLineWrap(true);
        gbc.gridy = 2; gbc.gridwidth = 2;
        add(new JScrollPane(inputArea), gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 3; add(createLabel("Shift Key (1-25):"), gbc);
        shiftField = new JTextField("3", 6);
        gbc.gridx = 1; add(shiftField, gbc);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        btnPanel.setBackground(new Color(30, 30, 30));
        JButton encBtn = createButton("Encrypt", new Color(76, 175, 80));
        JButton decBtn = createButton("Decrypt", new Color(255, 152, 0));
        
        encBtn.addActionListener(e -> processCipher(true));
        decBtn.addActionListener(e -> processCipher(false));

        btnPanel.add(encBtn);
        btnPanel.add(decBtn);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        add(btnPanel, gbc);

        gbc.gridy = 5; add(createLabel("Result Output:"), gbc);
        
        outputArea = new JTextArea(4, 25);
        outputArea.setFont(new Font("Courier New", Font.PLAIN, 11));
        outputArea.setBackground(new Color(45, 45, 45));
        outputArea.setForeground(new Color(0, 255, 204));
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        gbc.gridy = 6;
        add(new JScrollPane(outputArea), gbc);

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

    private void processCipher(boolean encrypt) {
        String text = inputArea.getText().trim();
        if (text.isEmpty()) {
            outputArea.setText("Error: Input text is empty!");
            return;
        }

        int shift;
        try {
            shift = Integer.parseInt(shiftField.getText().trim());
        } catch (NumberFormatException ex) {
            outputArea.setText("Error: Shift key must be an integer!");
            return;
        }

        if (!encrypt) shift = -shift;

        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                char shifted = (char) ((ch - base + shift + 26) % 26 + base);
                result.append(shifted);
            } else {
                result.append(ch);
            }
        }

        outputArea.setText(result.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CaesarCipherApp::new);
    }
}