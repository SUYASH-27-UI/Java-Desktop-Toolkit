import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pomodoro_Timer extends JFrame {
    private JLabel timeLabel;
    private Timer timer;
    private int secondsLeft = 1500; // 25 minutes
    private boolean isRunning = false;

    public Pomodoro_Timer() {
        setTitle("Smart Pomodoro Focus Timer");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("🍅 SMART POMODORO TIMER", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        timeLabel = new JLabel("25:00", JLabel.CENTER);
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(new Font("Courier New", Font.BOLD, 32));
        gbc.gridy = 1;
        add(timeLabel, gbc);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        btnPanel.setBackground(new Color(30, 30, 30));

        JButton startBtn = createButton("Start Focus", new Color(76, 175, 80));
        JButton resetBtn = createButton("Reset", new Color(244, 67, 54));

        startBtn.addActionListener(e -> startTimer());
        resetBtn.addActionListener(e -> resetTimer());

        btnPanel.add(startBtn);
        btnPanel.add(resetBtn);

        gbc.gridy = 2;
        add(btnPanel, gbc);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (secondsLeft > 0) {
                    secondsLeft--;
                    int mins = secondsLeft / 60;
                    int secs = secondsLeft % 60;
                    timeLabel.setText(String.format("%02d:%02d", mins, secs));
                } else {
                    timer.stop();
                    isRunning = false;
                    JOptionPane.showMessageDialog(Pomodoro_Timer.this, "Focus session complete! Take a break.");
                }
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

    private void resetTimer() {
        timer.stop();
        isRunning = false;
        secondsLeft = 1500;
        timeLabel.setText("25:00");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Pomodoro_Timer::new);
    }
}