import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class StudentGUI extends JFrame {

    private Student student;
    private JFrame actionsFrame;

    private JTextField nameField;
    private JTextArea outputArea;
    private JLabel credentialsLabel;
    private JFrame loginFrame;

    // Map to store exams for each grade
    private Map<Integer, String> exams;

    public StudentGUI() {
        student = new Student();
        initializeExams();
        initializeUI();
    }

    private void initializeExams() {
        exams = new HashMap<>();
        // Add exams for different grade levels
        exams.put(9, "Ninth Grade Exam Questions...");
        exams.put(10, "Tenth Grade Exam Questions...");
        exams.put(11, "Eleventh Grade Exam Questions...");
        exams.put(12, "Twelfth Grade Exam Questions...");
    }

    private void initializeUI() {
        setTitle("Exam Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Enter your name:");
        nameField = new JTextField();
        JButton generateCredentialsButton = new JButton("Generate Credentials");
        JButton loginButton = new JButton("Login");

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        credentialsLabel = new JLabel();

        generateCredentialsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateCredentials();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginForm();
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(generateCredentialsButton);
        panel.add(loginButton);
        panel.add(credentialsLabel);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        setVisible(true);
    }

    private void generateCredentials() {
        // Use the existing StudentCredentials instance associated with the current Student
        StudentCredentials studentCredentials = student.p.getStudentData();
        studentCredentials.generateStudentCredentials();
        outputArea.append("Credentials generated. Copy the username and password.\n");
        credentialsLabel.setText("Username: " + student.getUserName() + "   Password: " + student.getPassword());
    }

    private void showLoginForm() {
        loginFrame = new JFrame("Login");
        loginFrame.setSize(300, 150);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                showActionsFrame();
                if (student.getUserName().equals(username) && student.getPassword().equals(password)) {

                    loginFrame.dispose();
                    outputArea.append("Login successful.\n");
                    showActionsFrame();
                } else {
                    loginFrame.dispose();
                    JOptionPane.showMessageDialog(loginFrame, "Invalid username or password");
                }
            }
        });

        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(submitButton);

        loginFrame.add(loginPanel);
        loginFrame.setVisible(true);
    }

    private void showActionsFrame() {
        actionsFrame = new JFrame("Actions After Login");
        actionsFrame.setSize(400, 300);
        actionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new GridLayout(3, 1));

        JButton takeExamButton = new JButton("Take Exam");
        JButton viewGradesButton = new JButton("View Grades");
        JButton writeNoteButton = new JButton("Write Note to Principal");

        takeExamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                takeExam();
            }
        });

        viewGradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add logic for "View Grades" button
                outputArea.append("View Grades button clicked.\n");
            }
        });

        writeNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add logic for "Write Note to Principal" button
                outputArea.append("Write Note to Principal button clicked.\n");
            }
        });

        sidebarPanel.add(takeExamButton);
        sidebarPanel.add(viewGradesButton);
        sidebarPanel.add(writeNoteButton);

        actionsFrame.add(sidebarPanel, BorderLayout.WEST);

        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        actionsFrame.setVisible(true);
    }

    private void takeExam() {
        // Get the student's grade level
        int gradeLevel = student.getGrade();
        // Get the corresponding exam for the grade level
        String examQuestions = exams.get(gradeLevel);

        if (examQuestions != null) {
            // You can add your actual exam logic here
            outputArea.append("Exam started for Grade " + gradeLevel + ".\n" + examQuestions + "\n");
        } else {
            outputArea.append("No exam available for Grade " + gradeLevel + ".\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentGUI();
            }
        });
    }
}
