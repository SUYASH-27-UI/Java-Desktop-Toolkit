import javax.swing.*;
import java.awt.*;

public class StopwatchApp extends JFrame {
    private JLabel timeLabel;
    private Timer timer;
    private int elapsedSeconds = 0;
    private boolean isRunning = false;

    public StopwatchApp() {
        setTitle("Smart Stopwatch");
        setSize(360, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3;

        JLabel title = new JLabel("⏱️ SMART STOPWATCH", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 12));
        add(title, gbc);

        timeLabel = new JLabel("00:00:00", JLabel.CENTER);
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(new Font("Courier New", Font.BOLD, 28));
        gbc.gridy = 1;
        add(timeLabel, gbc);

        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.setBackground(new Color(30, 30, 30));

        JButton startBtn = createButton("Start", new Color(76, 175, 80));
        JButton stopBtn = createButton("Stop", new Color(255, 152, 0));
        JButton resetBtn = createButton("Reset", new Color(244, 67, 54));

        startBtn.addActionListener(e -> startStopwatch());
        stopBtn.addActionListener(e -> stopStopwatch());
        resetBtn.addActionListener(e -> resetStopwatch());

        btnPanel.add(startBtn);
        btnPanel.add(stopBtn);
        btnPanel.add(resetBtn);

        gbc.gridy = 2;
        add(btnPanel, gbc);

        // Initialize Swing Timer (fires every 1000ms)
        timer = new Timer(1000, e -> {
            elapsedSeconds++;
            updateDisplay();
        });

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

    private void startStopwatch() {
        if (!isRunning) {
            timer.start();
            isRunning = true;
        }
    }

    private void stopStopwatch() {
        if (isRunning) {
            timer.stop();
            isRunning = false;
        }
    }

    private void resetStopwatch() {
        timer.stop();
        isRunning = false;
        elapsedSeconds = 0;
        updateDisplay();
    }

    private void updateDisplay() {
        int hours = elapsedSeconds / 3600;
        int minutes = (elapsedSeconds % 3600) / 60;
        int secs = elapsedSeconds % 60;
        timeLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, secs));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StopwatchApp::new);
    }
}