import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class quote_generator_app extends JFrame {
    private JLabel quoteLabel;
    private String[] quotes = {
        "Talk is cheap. Show me the code. - Linus Torvalds",
        "Programs must be written for people to read, and only incidentally for machines to execute. - Harold Abelson",
        "Simplicity is prerequisite for reliability. - Edsger W. Dijkstra",
        "Code is like humor. When you have to explain it, it’s bad. - Cory House",
        "First, solve the problem. Then, write the code. - John Johnson"
    };

    public quote_generator_app() {
        setTitle("Smart Quote Generator");
        setSize(440, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("💡 SMART QUOTE GENERATOR", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0;
        add(title, gbc);

        quoteLabel = new JLabel("<html><div style='text-align: center; width: 350px;'>" + quotes[0] + "</div></html>", JLabel.CENTER);
        quoteLabel.setForeground(Color.WHITE);
        quoteLabel.setFont(new Font("Courier New", Font.ITALIC, 12));
        gbc.gridy = 1;
        add(quoteLabel, gbc);

        JButton genBtn = new JButton("New Quote");
        genBtn.setBackground(new Color(76, 175, 80));
        genBtn.setForeground(Color.WHITE);
        genBtn.setFont(new Font("Arial", Font.BOLD, 10));
        genBtn.setFocusPainted(false);
        gbc.gridy = 2;
        genBtn.addActionListener(e -> generateQuote());
        add(genBtn, gbc);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void generateQuote() {
        Random rnd = new Random();
        int idx = rnd.nextInt(quotes.length);
        quoteLabel.setText("<html><div style='text-align: center; width: 350px;'>" + quotes[idx] + "</div></html>");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(quote_generator_app::new);
    }
}