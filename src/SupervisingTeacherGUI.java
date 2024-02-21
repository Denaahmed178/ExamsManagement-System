import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SupervisingTeacherGUI extends JPanel{

    private JFrame frame;
    private SupervisingTeacher supervisingTeacher;
    private JTextArea outputTextArea; 
    public SupervisingTeacherGUI() {
        supervisingTeacher = new SupervisingTeacher(new NormalTeacher());
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Supervising Teacher GUI");
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout());

        outputTextArea = new JTextArea();  // Initialize outputTextArea
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));

        JButton viewGradesButton = new JButton("View Grades");
        viewGradesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewGrades(outputTextArea);
            }
        });
        buttonPanel.add(viewGradesButton);

        JButton issueReportsButton = new JButton("Issue Reports");
        issueReportsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                issueReports(outputTextArea);
            }
        });
        buttonPanel.add(issueReportsButton);

        JButton sendNotificationButton = new JButton("Send Notification");
        sendNotificationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendNotificationDialog();
            }
        });
        buttonPanel.add(sendNotificationButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void viewGrades(JTextArea outputTextArea) {
        int studentID = getStudentIDFromUser();
        String grades = supervisingTeacher.getGrades(studentID);
        outputTextArea.setText(grades);
    }

    private void issueReports(JTextArea outputTextArea) {
        outputTextArea.setText("");
        int studentID = getStudentIDFromUser();
        String report = supervisingTeacher.getReport(studentID);
        outputTextArea.append(report);
    }

    private void sendNotificationDialog() {
        JTextField studentIDField = new JTextField();
        JTextField notificationTypeField = new JTextField();
        JTextField messageField = new JTextField();

        Object[] fields = {
                "Student ID:", studentIDField,
                "Notification Type (Email/SMS):", notificationTypeField,
                "Message:", messageField
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Send Notification", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                int studentID = Integer.parseInt(studentIDField.getText());
                String notificationType = notificationTypeField.getText();
                String message = messageField.getText();

                supervisingTeacher.sendNotification(studentID, notificationType, message);
                
                // Append the notification information to the outputTextArea
                outputTextArea.append("Notification sent to Student ID " + studentID + " via " + notificationType + ":\n");
                outputTextArea.append("Message: " + message + "\n\n");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid values.");
            }
        }
    }


    private int getStudentIDFromUser() {
        JTextField studentIDField = new JTextField();
        Object[] fields = {
                "Enter Student ID:", studentIDField
        };
        int option = JOptionPane.showConfirmDialog(frame, fields, "Enter Student ID", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                return Integer.parseInt(studentIDField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid Student ID.");
            }
        }
        return -1;
    }
    

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new SupervisingTeacherGUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
