import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DigitalClockApp extends JFrame {
    private JLabel timeLabel;

    public DigitalClockApp() {
        setTitle("Smart Digital Clock");
        setSize(380, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        // Header Title
        JLabel titleLabel = new JLabel("⏰ LIVE DIGITAL CLOCK", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        titleLabel.setForeground(new Color(0, 255, 204));

        // Time Display Label
        timeLabel = new JLabel("", JLabel.CENTER);
        timeLabel.setFont(new Font("Courier New", Font.BOLD, 32));
        timeLabel.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.insets = new Insets(5, 5, 15, 5);
        add(titleLabel, gbc);

        gbc.gridy = 1;
        add(timeLabel, gbc);

        // Timer to update every 1 second (1000ms)
        Timer timer = new Timer(1000, e -> updateTime());
        timer.start();
        updateTime();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateTime() {
        String currentTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
        timeLabel.setText(currentTime);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DigitalClockApp::new);
    }
}