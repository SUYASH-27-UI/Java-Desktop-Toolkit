import javax.swing.*;
import java.awt.*;

public class text_stats_analyzer extends JFrame {
    private static final double WORDS_PER_MINUTE = 200.0;
    private JTextArea textArea;
    private JLabel statsLabel;

    public text_stats_analyzer() {
        setTitle("Smart Text & Reading Time Analyzer");
        setSize(460, 390);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.BOTH;

        JLabel title = new JLabel("📝 SMART TEXT STATS ANALYZER", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; gbc.weightx = 1.0; gbc.weighty = 0;
        add(title, gbc);

        textArea = new JTextArea(
            "Dark-themed desktop utilities make daily coding workflows fast and clean.",
            6,
            30
        );
        textArea.setFont(new Font("Courier New", Font.PLAIN, 11));
        textArea.setBackground(new Color(45, 45, 45));
        textArea.setForeground(Color.WHITE);
        textArea.setCaretColor(new Color(0, 255, 204));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        gbc.gridy = 1; gbc.weighty = 1.0;
        add(scrollPane, gbc);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        btnPanel.setBackground(new Color(30, 30, 30));

        JButton analyzeBtn = createButton("Analyze Text", new Color(76, 175, 80));
        JButton clearBtn = createButton("Clear", new Color(244, 67, 54));

        analyzeBtn.addActionListener(e -> analyzeText());
        clearBtn.addActionListener(e -> clearText());

        btnPanel.add(analyzeBtn);
        btnPanel.add(clearBtn);

        gbc.gridy = 2; gbc.weighty = 0;
        add(btnPanel, gbc);

        statsLabel = new JLabel("Words: 0 | Lines: 0 | Read: 0.0 min", JLabel.CENTER);
        statsLabel.setForeground(new Color(0, 255, 204));
        statsLabel.setFont(new Font("Courier New", Font.BOLD, 11));
        gbc.gridy = 3;
        add(statsLabel, gbc);

        analyzeText();

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

    private void analyzeText() {
        String raw = textArea.getText();
        String trimmed = raw.trim();

        int wordCount = trimmed.isEmpty() ? 0 : trimmed.split("\\s+").length;
        int charWithSpaces = raw.length();
        int charNoSpaces = raw.replaceAll("\\s+", "").length();
        int lineCount = raw.isEmpty() ? 0 : raw.split("\\R", -1).length;
        double readMinutes = wordCount / WORDS_PER_MINUTE;

        statsLabel.setText(String.format(
            "<html>Words: %d | Lines: %d | Read: %.1f min<br>Chars (with spaces): %d | No spaces: %d</html>",
            wordCount, lineCount, readMinutes, charWithSpaces, charNoSpaces
        ));
    }

    private void clearText() {
        textArea.setText("");
        analyzeText();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(text_stats_analyzer::new);
    }
}