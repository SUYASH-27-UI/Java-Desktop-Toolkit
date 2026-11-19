import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class number_guessing_game extends JFrame {
    private JTextField guessField;
    private JLabel statusLabel;
    private int targetNumber;
    private int attempts = 0;
    private Random random = new Random();

    public number_guessing_game() {
        setTitle("Smart Number Guessing Game");
        setSize(420, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        targetNumber = random.nextInt(100) + 1;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("🎮 NUMBER GUESSING GAME", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; add(createLabel("Guess (1-100):"), gbc);
        guessField = new JTextField("50", 10);
        gbc.gridx = 1; add(guessField, gbc);

        JButton guessBtn = new JButton("Submit Guess");
        guessBtn.setBackground(new Color(76, 175, 80));
        guessBtn.setForeground(Color.WHITE);
        guessBtn.setFont(new Font("Arial", Font.BOLD, 10));
        guessBtn.setFocusPainted(false);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        guessBtn.addActionListener(e -> checkGuess());
        add(guessBtn, gbc);

        statusLabel = new JLabel("Status: Make your first guess!", JLabel.CENTER);
        statusLabel.setForeground(new Color(0, 255, 204));
        statusLabel.setFont(new Font("Courier New", Font.BOLD, 11));
        gbc.gridy = 3;
        add(statusLabel, gbc);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(Color.WHITE);
        return lbl;
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText().trim());
            attempts++;

            if (guess < targetNumber) {
                statusLabel.setText("Attempt " + attempts + ": Too Low! Try higher.");
            } else if (guess > targetNumber) {
                statusLabel.setText("Attempt " + attempts + ": Too High! Try lower.");
            } else {
                statusLabel.setText("<html>🎉 Correct! Number was " + targetNumber + "<br>Attempts: " + attempts + "</html>");
                targetNumber = random.nextInt(100) + 1;
                attempts = 0;
            }
        } catch (NumberFormatException ex) {
            statusLabel.setText("Error: Enter a valid integer!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(number_guessing_game::new);
    }
}