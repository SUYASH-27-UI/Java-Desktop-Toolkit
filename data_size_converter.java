import javax.swing.*;
import java.awt.*;

public class data_size_converter extends JFrame {
    private static final String[] UNITS = {"Bytes", "KB", "MB", "GB", "TB"};
    private JTextField sizeField;
    private JComboBox<String> fromBox, toBox, baseBox;
    private JLabel resultLabel;

    public data_size_converter() {
        setTitle("Smart Data Size Converter");
        setSize(430, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("💾 SMART DATA SIZE CONVERTER", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; add(createLabel("Size Value:"), gbc);
        sizeField = new JTextField("1024", 12);
        sizeField.setFont(new Font("Courier New", Font.BOLD, 11));
        gbc.gridx = 1; add(sizeField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(createLabel("From Unit:"), gbc);
        fromBox = new JComboBox<>(UNITS);
        fromBox.setSelectedIndex(2); // MB default
        gbc.gridx = 1; add(fromBox, gbc);

        gbc.gridx = 0; gbc.gridy = 3; add(createLabel("To Unit:"), gbc);
        toBox = new JComboBox<>(UNITS);
        toBox.setSelectedIndex(3); // GB default
        gbc.gridx = 1; add(toBox, gbc);

        gbc.gridx = 0; gbc.gridy = 4; add(createLabel("Base Mode:"), gbc);
        baseBox = new JComboBox<>(new String[]{"Binary (1024)", "Decimal (1000)"});
        gbc.gridx = 1; add(baseBox, gbc);

        JButton convBtn = new JButton("Convert Size");
        convBtn.setBackground(new Color(76, 175, 80));
        convBtn.setForeground(Color.WHITE);
        convBtn.setFont(new Font("Arial", Font.BOLD, 10));
        convBtn.setFocusPainted(false);
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        convBtn.addActionListener(e -> convertSize());
        add(convBtn, gbc);

        resultLabel = new JLabel("Converted Size: --", JLabel.CENTER);
        resultLabel.setForeground(new Color(0, 255, 204));
        resultLabel.setFont(new Font("Courier New", Font.BOLD, 12));
        gbc.gridy = 6;
        add(resultLabel, gbc);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(Color.WHITE);
        return lbl;
    }

    private void convertSize() {
        try {
            double val = Double.parseDouble(sizeField.getText().trim());
            if (val < 0) {
                resultLabel.setText("Error: Size cannot be negative!");
                return;
            }

            String mode = (String) baseBox.getSelectedItem();
            double step = (mode != null && mode.contains("1024")) ? 1024.0 : 1000.0;

            int srcIdx = fromBox.getSelectedIndex();
            int dstIdx = toBox.getSelectedIndex();
            String targetUnit = UNITS[dstIdx];

            double inBytes = val * Math.pow(step, srcIdx);
            double converted = inBytes / Math.pow(step, dstIdx);

            resultLabel.setText(String.format("Converted Size: %.4f %s", converted, targetUnit));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Error: Invalid numeric input!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(data_size_converter::new);
    }
}