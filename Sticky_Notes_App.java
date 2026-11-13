import javax.swing.*;
import java.awt.*;

public class Sticky_Notes_App extends JFrame {
    private JTextField noteField;
    private DefaultListModel<String> listModel;
    private JList<String> notesList;

    public Sticky_Notes_App() {
        setTitle("Smart Sticky Notes Manager");
        setSize(420, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("📌 SMART STICKY NOTES MANAGER", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; add(createLabel("New Note:"), gbc);
        noteField = new JTextField(18);
        gbc.gridx = 1; add(noteField, gbc);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        btnPanel.setBackground(new Color(30, 30, 30));
        JButton addBtn = createButton("Add Note", new Color(76, 175, 80));
        JButton delBtn = createButton("Delete Selected", new Color(244, 67, 54));

        addBtn.addActionListener(e -> addNote());
        delBtn.addActionListener(e -> deleteNote());

        btnPanel.add(addBtn);
        btnPanel.add(delBtn);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        add(btnPanel, gbc);

        listModel = new DefaultListModel<>();
        notesList = new JList<>(listModel);
        notesList.setFont(new Font("Courier New", Font.PLAIN, 11));
        notesList.setBackground(new Color(45, 45, 45));
        notesList.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(notesList);
        scrollPane.setPreferredSize(new Dimension(360, 140));
        gbc.gridy = 3;
        add(scrollPane, gbc);

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

    private void addNote() {
        String note = noteField.getText().trim();
        if (!note.isEmpty()) {
            listModel.addElement(note);
            noteField.setText("");
        }
    }

    private void deleteNote() {
        int idx = notesList.getSelectedIndex();
        if (idx != -1) {
            listModel.remove(idx);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Sticky_Notes_App::new);
    }
}