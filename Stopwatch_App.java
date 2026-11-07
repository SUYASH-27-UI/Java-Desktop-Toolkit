import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stopwatch_App extends JFrame {
    private JLabel timeLabel;
    private JTextArea lapArea;
    private Timer timer;
    private int counter = 0;
    private int lapCount = 1;

    public Stopwatch_App() {
        setTitle("Smart Stopwatch & Lap Timer");
        setSize(420, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("⏱️ SMART STOPWATCH & LAP TIMER", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 4;
        add(title, gbc);

        timeLabel = new JLabel("00:00:00", JLabel.CENTER);
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(new Font("Courier New", Font.BOLD, 28));
        gbc.gridy = 1;
        add(timeLabel, gbc);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 0));
        btnPanel.setBackground(new Color(30, 30, 30));

        JButton startBtn = createButton("Start", new Color(76, 175, 80));
        JButton stopBtn = createButton("Stop", new Color(255, 152, 0));
        JButton lapBtn = createButton("Lap", new Color(33, 150, 243));
        JButton resetBtn = createButton("Reset", new Color(244, 67, 54));

        startBtn.addActionListener(e -> startTimer());
        stopBtn.addActionListener(e -> stopTimer());
        lapBtn.addActionListener(e -> recordLap());
        resetBtn.addActionListener(e -> resetTimer());

        btnPanel.add(startBtn);
        btnPanel.add(stopBtn);
        btnPanel.add(lapBtn);
        btnPanel.add(resetBtn);

        gbc.gridy = 2;
        add(btnPanel, gbc);

        lapArea = new JTextArea(6, 25);
        lapArea.setFont(new Font("Courier New", Font.PLAIN, 11));
        lapArea.setBackground(new Color(45, 45, 45));
        lapArea.setForeground(new Color(0, 255, 204));
        lapArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(lapArea);
        gbc.gridy = 3;
        add(scrollPane, gbc);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter++;
                int hrs = counter / 3600;
                int mins = (counter % 3600) / 60;
                int secs = counter % 60;
                timeLabel.setText(String.format("%02d:%02d:%02d", hrs, mins, secs));
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
        if (!timer.isRunning()) {
            timer.start();
        }
    }

    private void stopTimer() {
        if (timer.isRunning()) {
            timer.stop();
        }
    }

    private void recordLap() {
        if (timer.isRunning()) {
            lapArea.append("Lap " + lapCount++ + ": " + timeLabel.getText() + "\n");
        }
    }

    private void resetTimer() {
        timer.stop();
        counter = 0;
        lapCount = 1;
        timeLabel.setText("00:00:00");
        lapArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Stopwatch_App::new);
    }
}