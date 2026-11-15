import javax.swing.*;
import java.awt.*;

public class todo_list_app extends JFrame {
    private JTextField taskField;
    private DefaultListModel<String> listModel;
    private JList<String> taskList;

    public todo_list_app() {
        setTitle("Smart Todo List Manager");
        setSize(420, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("📋 SMART TODO LIST MANAGER", JLabel.CENTER);
        title.setForeground(new Color(0, 255, 204));
        title.setFont(new Font("Arial", Font.BOLD, 11));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; add(createLabel("New Task:"), gbc);
        taskField = new JTextField(18);
        gbc.gridx = 1; add(taskField, gbc);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        btnPanel.setBackground(new Color(30, 30, 30));
        JButton addBtn = createButton("Add Task", new Color(76, 175, 80));
        JButton delBtn = createButton("Delete Selected", new Color(244, 67, 54));

        addBtn.addActionListener(e -> addTask());
        delBtn.addActionListener(e -> deleteTask());

        btnPanel.add(addBtn);
        btnPanel.add(delBtn);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        add(btnPanel, gbc);

        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setFont(new Font("Courier New", Font.PLAIN, 11));
        taskList.setBackground(new Color(45, 45, 45));
        taskList.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setPreferredSize(new Dimension(360, 150));
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

    private void addTask() {
        String task = taskField.getText().trim();
        if (!task.isEmpty()) {
            listModel.addElement(task);
            taskField.setText("");
        }
    }

    private void deleteTask() {
        int idx = taskList.getSelectedIndex();
        if (idx != -1) {
            listModel.remove(idx);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(todo_list_app::new);
    }
}