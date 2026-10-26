import javax.swing.*;
import java.awt.*;

public class RgbHexConverterApp extends JFrame {
    private JTextField redField, greenField, blueField;
    private JLabel hexLabel;
    private JPanel colorPreviewPanel;

    public RgbHexConverterApp() {
        setTitle("Smart RGB to Hex Color Converter");
        setSize(380, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("🎨 RGB TO HEX COLOR CONVERTER", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; add(createLabel("Red (0-255):"), gbc);
        redField = new JTextField("0", 10);
        gbc.gridx = 1; add(redField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(createLabel("Green (0-255):"), gbc);
        greenField = new JTextField("255", 10);
        gbc.gridx = 1; add(greenField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; add(createLabel("Blue (0-255):"), gbc);
        blueField = new JTextField("204", 10);
        gbc.gridx = 1; add(blueField, gbc);

        JButton convertBtn = new JButton("Convert & Preview");
        convertBtn.setBackground(new Color(76, 175, 80));
        convertBtn.setForeground(Color.WHITE);
        convertBtn.setFont(new Font("Arial", Font.BOLD, 10));
        convertBtn.setFocusPainted(false);
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        convertBtn.addActionListener(e -> convertColor());
        add(convertBtn, gbc);

        hexLabel = new JLabel("Hex: #00FFCC", JLabel.CENTER);
        hexLabel.setForeground(Color.WHITE);
        hexLabel.setFont(new Font("Courier New", Font.BOLD, 14));
        gbc.gridy = 5;
        add(hexLabel, gbc);

        // Color Preview Box
        colorPreviewPanel = new JPanel();
        colorPreviewPanel.setBackground(new Color(0, 255, 204));
        colorPreviewPanel.setPreferredSize(new Dimension(100, 30));
        gbc.gridy = 6;
        add(colorPreviewPanel, gbc);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(Color.WHITE);
        return lbl;
    }

    private void convertColor() {
        try {
            int r = Integer.parseInt(redField.getText().trim());
            int g = Integer.parseInt(greenField.getText().trim());
            int b = Integer.parseInt(blueField.getText().trim());

            if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
                hexLabel.setText("Values must be between 0-255");
                return;
            }

            String hexCode = String.format("#%02X%02X%02X", r, g, b);
            hexLabel.setText("Hex: " + hexCode);
            colorPreviewPanel.setBackground(new Color(r, g, b));
        } catch (Exception ex) {
            hexLabel.setText("Invalid RGB numbers!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RgbHexConverterApp::new);
    }
}