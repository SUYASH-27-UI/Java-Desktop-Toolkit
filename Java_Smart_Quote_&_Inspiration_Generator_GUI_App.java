import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class QuoteGeneratorApp extends JFrame {
    private JLabel quoteLabel, authorLabel;

    private final String[][] quotes = {
        {"“Simplicity is prerequisite for reliability.”", "— Edsger W. Dijkstra"},
        {"“Talk is cheap. Show me the code.”", "— Linus Torvalds"},
        {"“Programs must be written for people to read, and only incidentally for machines to execute.”", "— Harold Abelson"},
        {"“Code is like humor. When you have to explain it, it’s bad.”", "— Cory House"},
        {"“Fix the cause, not the symptom.”", "— Steve Maguire"},
        {"“Make it work, make it right, make it fast.”", "— Kent Beck"},
        {"“Premature optimization is the root of all evil.”", "— Donald Knuth"}
    };

    public QuoteGeneratorApp() {
        setTitle("Smart Quote Generator");
        setSize(420, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("💡 SMART INSPIRATIONAL QUOTES", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1;
        add(title, gbc);

        // Quote Display Panel
        JPanel cardPanel = new JPanel();
        cardPanel.setBackground(new Color(45, 45, 45));
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        quoteLabel = new JLabel("", JLabel.CENTER);
        quoteLabel.setForeground(Color.WHITE);
        quoteLabel.setFont(new Font("Georgia", Font.ITALIC, 12));
        quoteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        authorLabel = new JLabel("", JLabel.CENTER);
        authorLabel.setForeground(new Color(0, 255, 204));
        authorLabel.setFont(new Font("Arial", Font.BOLD, 11));
        authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        cardPanel.add(quoteLabel);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        cardPanel.add(authorLabel);

        gbc.gridy = 1;
        add(cardPanel, gbc);

        JButton genBtn = new JButton("Generate New Quote");
        genBtn.setBackground(new Color(76, 175, 80));
        genBtn.setForeground(Color.WHITE);
        genBtn.setFont(new Font("Arial", Font.BOLD, 10));
        genBtn.setFocusPainted(false);
        gbc.gridy = 2;
        genBtn.addActionListener(e -> generateRandomQuote());
        add(genBtn, gbc);

        generateRandomQuote();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void generateRandomQuote() {
        Random rnd = new Random();
        int idx = rnd.nextInt(quotes.length);
        quoteLabel.setText("<html><div style='text-align: center; width: 300px;'>" + quotes[idx][0] + "</div></html>");
        authorLabel.setText(quotes[idx][1]);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuoteGeneratorApp::new);
    }
}