import javax.swing.*;
import java.awt.*;

public class stopwatch_tracker extends JFrame {
    private JLabel timeLabel;
    private JButton startBtn;
    private DefaultListModel<String> lapModel;
    private Timer timer;
    private int tenthsElapsed = 0;
    private int lapCount = 0;
    private boolean isRunning = false;

    public stopwatch_tracker() {
        setTitle("Smart Stopwatch & Lap Tracker");
        setSize(430, 370);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("⏱️ SMART STOPWATCH & LAP TRACKER", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0;
        add(title, gbc);

        timeLabel = new JLabel("00:00.0", JLabel.CENTER);
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        gbc.gridy = 1;
        add(timeLabel, gbc);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        btnPanel.setBackground(new Color(30, 30, 30));

        startBtn = createButton("Start", new Color(76, 175, 80));
        JButton lapBtn = createButton("Lap", new Color(33, 150, 243));
        JButton resetBtn = createButton("Reset", new Color(244, 67, 54));

        startBtn.addActionListener(e -> toggleStart());
        lapBtn.addActionListener(e -> recordLap());
        resetBtn.addActionListener(e -> resetStopwatch());

        btnPanel.add(startBtn);
        btnPanel.add(lapBtn);
        btnPanel.add(resetBtn);

        gbc.gridy = 2;
        add(btnPanel, gbc);

        lapModel = new DefaultListModel<>();
        JList<String> lapList = new JList<>(lapModel);
        lapList.setFont(new Font("Courier New", Font.PLAIN, 11));
        lapList.setBackground(new Color(45, 45, 45));
        lapList.setForeground(new Color(0, 255, 204));

        JScrollPane scrollPane = new JScrollPane(lapList);
        scrollPane.setPreferredSize(new Dimension(370, 140));
        gbc.gridy = 3;
        add(scrollPane, gbc);

        timer = new Timer(100, e -> {
            tenthsElapsed++;
            timeLabel.setText(formatTime(tenthsElapsed));
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

    private String formatTime(int tenths) {
        int totalSeconds = tenths / 10;
        int mins = totalSeconds / 60;
        int secs = totalSeconds % 60;
        int d = tenths % 10;
        return String.format("%02d:%02d.%d", mins, secs, d);
    }

    private void toggleStart() {
        if (!isRunning) {
            timer.start();
            isRunning = true;
            startBtn.setText("Pause");
            startBtn.setBackground(new Color(255, 152, 0));
        } else {
            timer.stop();
            isRunning = false;
            startBtn.setText("Resume");
            startBtn.setBackground(new Color(76, 175, 80));
        }
    }

    private void recordLap() {
        if (tenthsElapsed > 0) {
            lapCount++;
            String entry = String.format("Lap #%02d  ->  %s", lapCount, formatTime(tenthsElapsed));
            lapModel.add(0, entry);
        }
    }

    private void resetStopwatch() {
        timer.stop();
        isRunning = false;
        tenthsElapsed = 0;
        lapCount = 0;
        timeLabel.setText("00:00.0");
        startBtn.setText("Start");
        startBtn.setBackground(new Color(76, 175, 80));
        lapModel.clear();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(stopwatch_tracker::new);
    }
}