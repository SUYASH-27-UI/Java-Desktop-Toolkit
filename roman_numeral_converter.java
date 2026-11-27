import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class roman_numeral_converter extends JFrame {
    private static final int[] VALUES = {
        1000, 900, 500, 400,
        100, 90, 50, 40,
        10, 9, 5, 4, 1
    };
    private static final String[] SYMBOLS = {
        "M", "CM", "D", "CD",
        "C", "XC", "L", "XL",
        "X", "IX", "V", "IV", "I"
    };
    private static final Map<Character, Integer> ROMAN_MAP = new LinkedHashMap<>();

    static {
        ROMAN_MAP.put('I', 1);
        ROMAN_MAP.put('V', 5);
        ROMAN_MAP.put('X', 10);
        ROMAN_MAP.put('L', 50);
        ROMAN_MAP.put('C', 100);
        ROMAN_MAP.put('D', 500);
        ROMAN_MAP.put('M', 1000);
    }

    private JTextField valueField;
    private JLabel resultLabel;

    public roman_numeral_converter() {
        setTitle("Smart Roman Numeral Converter");
        setSize(430, 330);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("🏛️ SMART ROMAN NUMERAL CONVERTER", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; add(createLabel("Number or Roman:"), gbc);
        valueField = new JTextField("2026", 14);
        valueField.setFont(new Font("Courier New", Font.BOLD, 11));
        gbc.gridx = 1; add(valueField, gbc);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        btnPanel.setBackground(new Color(30, 30, 30));

        JButton toRomanBtn = createButton("Int -> Roman", new Color(76, 175, 80));
        JButton toIntBtn = createButton("Roman -> Int", new Color(33, 150, 243));

        toRomanBtn.addActionListener(e -> handleToRoman());
        toIntBtn.addActionListener(e -> handleToInt());

        btnPanel.add(toRomanBtn);
        btnPanel.add(toIntBtn);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        add(btnPanel, gbc);

        resultLabel = new JLabel("Result: --", JLabel.CENTER);
        resultLabel.setForeground(new Color(0, 255, 204));
        resultLabel.setFont(new Font("Courier New", Font.BOLD, 12));
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

    private JButton createButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 10));
        btn.setFocusPainted(false);
        return btn;
    }

    private String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int remaining = num;
        for (int i = 0; i < VALUES.length; i++) {
            while (remaining >= VALUES[i]) {
                sb.append(SYMBOLS[i]);
                remaining -= VALUES[i];
            }
        }
        return sb.toString();
    }

    private int romanToInt(String input) {
        String s = input.toUpperCase().trim();
        if (s.isEmpty()) {
            throw new IllegalArgumentException("Roman numeral empty nahi ho sakta!");
        }

        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            Integer curr = ROMAN_MAP.get(s.charAt(i));
            if (curr == null) {
                throw new IllegalArgumentException("Invalid Roman character: " + s.charAt(i));
            }
            if (i + 1 < s.length()) {
                Integer next = ROMAN_MAP.get(s.charAt(i + 1));
                if (next != null && curr < next) {
                    total -= curr;
                    continue;
                }
            }
            total += curr;
        }

        if (total < 1 || total > 3999 || !intToRoman(total).equals(s)) {
            throw new IllegalArgumentException("Non-standard ya out-of-range Roman numeral!");
        }
        return total;
    }

    private void handleToRoman() {
        try {
            int num = Integer.parseInt(valueField.getText().trim());
            if (num < 1 || num > 3999) {
                resultLabel.setText("Error: Number 1 se 3999 ke beech hona chahiye!");
                return;
            }
            resultLabel.setText("Roman Numeral: " + intToRoman(num));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Error: Valid integer (1-3999) enter karein!");
        }
    }

    private void handleToInt() {
        try {
            int val = romanToInt(valueField.getText());
            resultLabel.setText("Integer Value: " + val);
        } catch (IllegalArgumentException ex) {
            resultLabel.setText("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(roman_numeral_converter::new);
    }
}