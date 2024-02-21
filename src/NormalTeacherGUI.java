import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class NormalTeacherGUI extends JPanel{

    private JFrame frame;
    private NormalTeacher normalTeacher;

    public NormalTeacherGUI() {
        normalTeacher = new NormalTeacher();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Normal Teacher GUI");
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout());

        JTextArea outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 7));

        JButton addExamButton = new JButton("Add Exam");
        addExamButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addExam();
            }
        });
        buttonPanel.add(addExamButton);

        JButton editExamButton = new JButton("Edit Exam");
        editExamButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editExamDialog(); // Call the private method
            }
        });
        buttonPanel.add(editExamButton);

        JButton deleteExamButton = new JButton("Delete Exam");
        deleteExamButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteExam();
            }
        });
        buttonPanel.add(deleteExamButton);

        JButton assignAssignmentButton = new JButton("Assign Assignment");
        assignAssignmentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                assignAssignment();
            }
        });
        buttonPanel.add(assignAssignmentButton);

        JButton viewAllExamsButton = new JButton("View All Exams");
        viewAllExamsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewAllExams(outputTextArea);
            }
        });
        buttonPanel.add(viewAllExamsButton);        
        
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(exitButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void addExam() {
        JTextField idField = new JTextField();
        JTextField subjectField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField durationField = new JTextField();
        JTextField dateField = new JTextField();

        Object[] fields = {
                "Exam ID:", idField,
                "Subject:", subjectField,
                "Type:", typeField,
                "Description:", descriptionField,
                "Duration:", durationField,
                "Date (yyyy-MM-dd):", dateField
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Add Exam", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idField.getText());
                String subject = subjectField.getText();
                String type = typeField.getText();
                String description = descriptionField.getText();
                int duration = Integer.parseInt(durationField.getText());
                String dateString = dateField.getText();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(dateString);

                Exam exam = new Exam(id, subject);
                exam.setExamType(type);
                exam.setDescription(description);
                exam.setDuration(duration);
                exam.setDate(date);

                normalTeacher.getExams().put(exam.getExamID(), exam);

                JOptionPane.showMessageDialog(frame, "Exam added successfully!");
            } catch (NumberFormatException | ParseException e) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid values.");
            }
        }
    }

    public void editExamDialog() {
        JTextField idField = new JTextField();
        JTextField newValueField = new JTextField();
        JTextField attributeNameField = new JTextField();

        Object[] fields = {
            "Enter exam ID:", idField,
            "Enter attribute name:", attributeNameField,
            "Enter new value:", newValueField
            
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Edit Exam", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idField.getText());
                String attributeName = attributeNameField.getText();
                String newValue =  newValueField.getText();
                // Call the editExam method with the user-provided values
                normalTeacher.editExam(id, attributeName, newValue);

                JOptionPane.showMessageDialog(frame, "Exam edited successfully!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid values.");
            }
        }
    }


    private void deleteExam() {
        JTextField idField = new JTextField();

        Object[] fields = {
                "Exam ID:", idField
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Delete Exam", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idField.getText());

                normalTeacher.deleteExam(id);

                JOptionPane.showMessageDialog(frame, "Exam deleted successfully!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid Exam ID.");
            }
        }
    }

    private void assignAssignment() {
        JTextField classroomIdField = new JTextField();
        JTextField assignmentIdField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField deadlineField = new JTextField();
        JTextField subjectField = new JTextField();

        Object[] fields = {
                "Classroom ID:", classroomIdField,
                "Assignment ID:", assignmentIdField,
                "Description:", descriptionField,
                "Deadline:", deadlineField,
                "Subject:", subjectField
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Assign Assignment", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                int classroomId = Integer.parseInt(classroomIdField.getText());
                int assignmentId = Integer.parseInt(assignmentIdField.getText());
                String description = descriptionField.getText();
                String deadlineString = deadlineField.getText();
                String subject = subjectField.getText();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date deadline = dateFormat.parse(deadlineString);

                Assignment assignment = new Assignment();
                assignment.setAssignmentId(assignmentId);
                assignment.setDescription(description);
                assignment.setDeadline(deadline);
                assignment.setSubject(subject);

                // Update assignments in normalTeacher
                normalTeacher.assignAssignment(classroomId, assignment);

                JOptionPane.showMessageDialog(frame, "Assignment assigned successfully!");
            } catch (NumberFormatException | ParseException e) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid values.");
            }
        }
    }


    private void viewAllExams(JTextArea outputTextArea) {
        outputTextArea.setText("");
        for (Map.Entry<Integer, Exam> entry : normalTeacher.getExams().entrySet()) {
            outputTextArea.append("Exam ID: " + entry.getKey() + "\n");
            outputTextArea.append("Subject: " + entry.getValue().getSubject() + "\n");
            outputTextArea.append("Type: " + entry.getValue().getExamType() + "\n");
            outputTextArea.append("Description: " + entry.getValue().getDescription() + "\n");
            outputTextArea.append("Duration: " + entry.getValue().getDuration() + "\n");
            outputTextArea.append("Date: " + entry.getValue().getDate() + "\n");
            outputTextArea.append("----------------------------\n");
        }
        }
        
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new NormalTeacherGUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
