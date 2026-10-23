import javax.swing.*;
import java.awt.*;

public class PomodoroTimerApp extends JFrame {
    private JLabel timerLabel;
    private Timer timer;
    private int timeLeft = 25 * 60; // 25 minutes default
    private boolean isRunning = false;

    public PomodoroTimerApp() {
        setTitle("Smart Pomodoro Timer");
        setSize(360, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3;

        JLabel title = new JLabel("🍅 SMART POMODORO TIMER", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 12));
        add(title, gbc);

        timerLabel = new JLabel("25:00", JLabel.CENTER);
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setFont(new Font("Courier New", Font.BOLD, 36));
        gbc.gridy = 1;
        add(timerLabel, gbc);

        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.setBackground(new Color(30, 30, 30));

        JButton startBtn = createButton("Start", new Color(76, 175, 80));
        JButton pauseBtn = createButton("Pause", new Color(255, 152, 0));
        JButton resetBtn = createButton("Reset", new Color(244, 67, 54));

        startBtn.addActionListener(e -> startTimer());
        pauseBtn.addActionListener(e -> pauseTimer());
        resetBtn.addActionListener(e -> resetTimer());

        btnPanel.add(startBtn);
        btnPanel.add(pauseBtn);
        btnPanel.add(resetBtn);

        gbc.gridy = 2;
        add(btnPanel, gbc);

        // Swing Timer ticking every second
        timer = new Timer(1000, e -> {
            if (timeLeft > 0) {
                timeLeft--;
                updateDisplay();
            } else {
                timer.stop();
                isRunning = false;
                JOptionPane.showMessageDialog(this, "Pomodoro session complete! Take a break.");
            }
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

    private void startTimer() {
        if (!isRunning) {
            timer.start();
            isRunning = true;
        }
    }

    private void pauseTimer() {
        if (isRunning) {
            timer.stop();
            isRunning = false;
        }
    }

    private void resetTimer() {
        timer.stop();
        isRunning = false;
        timeLeft = 25 * 60;
        updateDisplay();
    }

    private void updateDisplay() {
        int mins = timeLeft / 60;
        int secs = timeLeft % 60;
        timerLabel.setText(String.format("%02d:%02d", mins, secs));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PomodoroTimerApp::new);
    }
}