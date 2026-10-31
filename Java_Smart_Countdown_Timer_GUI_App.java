import javax.swing.*;
import java.awt.*;

public class CountdownTimerApp extends JFrame {
    private JTextField minuteField;
    private JLabel timerLabel;
    private Timer timer;
    private int totalSeconds = 0;
    private boolean isRunning = false;

    public CountdownTimerApp() {
        setTitle("Smart Countdown Timer");
        setSize(360, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("⏳ SMART COUNTDOWN TIMER", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; add(createLabel("Minutes:"), gbc);
        minuteField = new JTextField("5", 10);
        gbc.gridx = 1; add(minuteField, gbc);

        timerLabel = new JLabel("00:05:00", JLabel.CENTER);
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setFont(new Font("Courier New", Font.BOLD, 24));
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        add(timerLabel, gbc);

        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.setBackground(new Color(30, 30, 30));

        JButton startBtn = createButton("Start", new Color(76, 175, 80));
        JButton stopBtn = createButton("Stop", new Color(255, 152, 0));
        JButton resetBtn = createButton("Reset", new Color(244, 67, 54));

        startBtn.addActionListener(e -> startTimer());
        stopBtn.addActionListener(e -> stopTimer());
        resetBtn.addActionListener(e -> resetTimer());

        btnPanel.add(startBtn);
        btnPanel.add(stopBtn);
        btnPanel.add(resetBtn);

        gbc.gridy = 3;
        add(btnPanel, gbc);

        // Swing Timer ticking every second
        timer = new Timer(1000, e -> {
            if (totalSeconds > 0) {
                totalSeconds--;
                updateDisplay();
            } else {
                timer.stop();
                isRunning = false;
                JOptionPane.showMessageDialog(this, "Countdown Finished!");
            }
        });

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

    private void startTimer() {
        if (!isRunning) {
            if (totalSeconds == 0) {
                try {
                    int mins = Integer.parseInt(minuteField.getText().trim());
                    totalSeconds = mins * 60;
                } catch (Exception ex) {
                    totalSeconds = 300;
                }
            }
            timer.start();
            isRunning = true;
        }
    }

    private void stopTimer() {
        if (isRunning) {
            timer.stop();
            isRunning = false;
        }
    }

    private void resetTimer() {
        timer.stop();
        isRunning = false;
        try {
            int mins = Integer.parseInt(minuteField.getText().trim());
            totalSeconds = mins * 60;
        } catch (Exception ex) {
            totalSeconds = 300;
        }
        updateDisplay();
    }

    private void updateDisplay() {
        int hrs = totalSeconds / 3600;
        int mins = (totalSeconds % 3600) / 60;
        int secs = totalSeconds % 60;
        timerLabel.setText(String.format("%02d:%02d:%02d", hrs, mins, secs));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CountdownTimerApp::new);
    }
}