import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WordCounterApp extends JFrame {
    private JTextArea textArea;
    private JLabel statsLabel;

    public WordCounterApp() {
        setTitle("Smart Word & Character Counter");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new BorderLayout());

        // Header Title
        JLabel title = new JLabel("📊 SMART WORD & CHARACTER COUNTER", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 12));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        // Text Area with Scrollpane
        textArea = new JTextArea();
        textArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        textArea.setBackground(new Color(45, 45, 45));
        textArea.setForeground(Color.WHITE);
        textArea.setCaretColor(Color.WHITE);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        add(scrollPane, BorderLayout.CENTER);

        // Real-time listener to update stats on typing
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                updateStats();
            }
        });

        // Stats Display Footer Label
        statsLabel = new JLabel("Words: 0 | Characters: 0 | Sentences: 0", JLabel.CENTER);
        statsLabel.setForeground(Color.WHITE);
        statsLabel.setFont(new Font("Arial", Font.BOLD, 11));
        statsLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 15, 10));
        add(statsLabel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateStats() {
        String text = textArea.getText().trim();
        if (text.isEmpty()) {
            statsLabel.setText("Words: 0 | Characters: 0 | Sentences: 0");
            return;
        }

        int charCount = text.length();
        int charNoSpaces = text.replace(" ", "").replace("\n", "").length();
        
        String[] words = text.split("\\s+");
        int wordCount = words.length;
        
        int sentenceCount = text.split("[.!?]+").length;

        statsLabel.setText(String.format("Words: %d | Chars: %d (No Spaces: %d)", wordCount, charCount, charNoSpaces));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WordCounterApp::new);
    }
}
