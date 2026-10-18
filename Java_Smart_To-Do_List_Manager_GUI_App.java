import javax.swing.*;
import java.awt.*;

public class TodoListApp extends JFrame {
    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JTextField taskInput;

    public TodoListApp() {
        setTitle("Smart To-Do List Manager");
        setSize(400, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 30));
        setLayout(new BorderLayout());

        JLabel title = new JLabel("✅ SMART TASK MANAGER", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 12));
        title.setForeground(new Color(0, 255, 204));
        add(title, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setBackground(new Color(45, 45, 45));
        taskList.setForeground(Color.WHITE);
        taskList.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(new Color(30, 30, 30));

        taskInput = new JTextField(18);
        JButton addBtn = new JButton("Add Task");
        addBtn.setBackground(new Color(76, 175, 80));
        addBtn.setForeground(Color.WHITE);
        addBtn.addActionListener(e -> {
            String task = taskInput.getText().trim();
            if (!task.isEmpty()) {
                listModel.addElement("▪ " + task);
                taskInput.setText("");
            }
        });

        JButton delBtn = new JButton("Delete");
        delBtn.setBackground(new Color(244, 67, 54));
        delBtn.setForeground(Color.WHITE);
        delBtn.addActionListener(e -> {
            int idx = taskList.getSelectedIndex();
            if (idx != -1) listModel.remove(idx);
        });

        bottomPanel.add(taskInput);
        bottomPanel.add(addBtn);
        bottomPanel.add(delBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TodoListApp::new);
    }
}