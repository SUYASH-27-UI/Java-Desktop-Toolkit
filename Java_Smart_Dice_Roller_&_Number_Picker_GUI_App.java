import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DiceRollerApp extends JFrame {
    private JLabel resultLabel;
    private JComboBox<String> diceDropdown;

    public DiceRollerApp() {
        setTitle("Smart Dice Roller & Number Picker");
        setSize(360, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("🎲 SMART DICE ROLLER", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; add(createLabel("Select Dice Type:"), gbc);
        String[] diceTypes = {"Standard 6-Sided (D6)", "8-Sided (D8)", "12-Sided (D12)", "20-Sided (D20)"};
        diceDropdown = new JComboBox<>(diceTypes);
        gbc.gridx = 1; add(diceDropdown, gbc);

        JButton rollBtn = new JButton("Roll Dice");
        rollBtn.setBackground(new Color(76, 175, 80));
        rollBtn.setForeground(Color.WHITE);
        rollBtn.setFont(new Font("Arial", Font.BOLD, 10));
        rollBtn.setFocusPainted(false);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        rollBtn.addActionListener(e -> rollDice());
        add(rollBtn, gbc);

        resultLabel = new JLabel("Rolled: 0", JLabel.CENTER);
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setFont(new Font("Courier New", Font.BOLD, 22));
        gbc.gridy = 3;
        add(resultLabel, gbc);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(Color.WHITE);
        return lbl;
    }

    private void rollDice() {
        int selection = diceDropdown.getSelectedIndex();
        int maxVal = 6;
        if (selection == 1) maxVal = 8;
        else if (selection == 2) maxVal = 12;
        else if (selection == 3) maxVal = 20;

        Random rnd = new Random();
        int rolled = rnd.nextInt(maxVal) + 1;
        resultLabel.setText("Rolled: " + rolled);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DiceRollerApp::new);
    }
}