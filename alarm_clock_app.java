import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class alarm_clock_app extends JFrame {
    private JLabel clockLabel, statusLabel;
    private JTextField alarmField;
    private Timer timer;
    private String alarmTime = "";

    public alarm_clock_app() {
        setTitle("Smart Alarm Clock");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("⏰ SMART ALARM CLOCK", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        clockLabel = new JLabel("00:00:00", JLabel.CENTER);
        clockLabel.setForeground(Color.WHITE);
        clockLabel.setFont(new Font("Courier New", Font.BOLD, 24));
        gbc.gridy = 1;
        add(clockLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 2; add(createLabel("Alarm (HH:MM):"), gbc);
        alarmField = new JTextField("07:30", 10);
        gbc.gridx = 1; add(alarmField, gbc);

        JButton setBtn = new JButton("Set Alarm");
        setBtn.setBackground(new Color(76, 175, 80));
        setBtn.setForeground(Color.WHITE);
        setBtn.setFont(new Font("Arial", Font.BOLD, 10));
        setBtn.setFocusPainted(false);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        setBtn.addActionListener(e -> setAlarm());
        add(setBtn, gbc);

        statusLabel = new JLabel("Status: No alarm set", JLabel.CENTER);
        statusLabel.setForeground(new Color(0, 255, 204));
        statusLabel.setFont(new Font("Courier New", Font.BOLD, 11));
        gbc.gridy = 4;
        add(statusLabel, gbc);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                SimpleDateFormat hmSdf = new SimpleDateFormat("HH:mm");
                Date now = new Date();
                
                clockLabel.setText(sdf.format(now));

                if (!alarmTime.isEmpty() && hmSdf.format(now).equals(alarmTime)) {
                    JOptionPane.showMessageDialog(alarm_clock_app.this, "Wake up! Alarm ringing for " + alarmTime);
                    alarmTime = "";
                    statusLabel.setText("Status: Alarm triggered!");
                }
            }
        });
        timer.start();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(Color.WHITE);
        return lbl;
    }

    private void setAlarm() {
        alarmTime = alarmField.getText().trim();
        statusLabel.setText("Status: Alarm set for " + alarmTime);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(alarm_clock_app::new);
    }
}