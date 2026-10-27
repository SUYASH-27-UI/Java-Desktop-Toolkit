import javax.swing.*;
import java.awt.*;
import java.io.*;

public class QuickNotepadApp extends JFrame {
    private JTextArea textArea;
    private JFileChooser fileChooser;

    public QuickNotepadApp() {
        setTitle("Smart Quick Notepad");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new BorderLayout());

        // Header Title
        JLabel title = new JLabel("📝 SMART QUICK NOTEPAD", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 12));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        // Text Area with Scrollpane
        textArea = new JTextArea();
        textArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        textArea.setBackground(new Color(45, 45, 45));
        textArea.setForeground(Color.WHITE);
        textArea.setCaretColor(Color.WHITE);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        add(scrollPane, BorderLayout.CENTER);

        // Bottom Button Panel
        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.setBackground(new Color(30, 30, 30));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        JButton saveBtn = createButton("Save File", new Color(76, 175, 80));
        JButton openBtn = createButton("Open File", new Color(33, 150, 243));
        JButton clearBtn = createButton("Clear", new Color(244, 67, 54));

        saveBtn.addActionListener(e -> saveFile());
        openBtn.addActionListener(e -> openFile());
        clearBtn.addActionListener(e -> textArea.setText(""));

        btnPanel.add(saveBtn);
        btnPanel.add(openBtn);
        btnPanel.add(clearBtn);
        add(btnPanel, BorderLayout.SOUTH);

        fileChooser = new JFileChooser();

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

    private void saveFile() {
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "File successfully saved!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void openFile() {
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.read(reader, null);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error opening file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuickNotepadApp::new);
    }
}