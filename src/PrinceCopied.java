import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

public class PrinceCopied extends JFrame {

    private JFrame frame;
    private principal p;
    private Map<Integer, NormalTeacher> teachers = new HashMap<>();
    private JTextArea outputTextArea;

    public PrinceCopied() {
        p = principal.getInstant();
        initialize();
    }

    private void initialize() {
    	 frame = new JFrame("Principal GUI");
    	    frame.setBounds(100, 100, 900, 700);
    	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    	    JPanel panel = new JPanel();
    	    frame.getContentPane().add(panel, BorderLayout.CENTER);
    	    panel.setLayout(new BorderLayout());

    	    outputTextArea = new JTextArea(); // Use the class-level variable
    	    outputTextArea.setEditable(false);
    	    JScrollPane scrollPane = new JScrollPane(outputTextArea);
    	    panel.add(scrollPane, BorderLayout.CENTER);
    	    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 8));

        JButton addTeacherButton = new JButton("Add Teacher");
        addTeacherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTeacher();
            }
        });
        buttonPanel.add(addTeacherButton);

        JButton editTeacherButton = new JButton("Edit Teacher");
        editTeacherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editTeacher();
            }
        });
        buttonPanel.add(editTeacherButton);

        JButton deleteTeacherButton = new JButton("Delete Teacher");
        deleteTeacherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteTeacher();
            }
        });
        buttonPanel.add(deleteTeacherButton);

        JButton viewTeachersButton = new JButton("View All Teachers");
        viewTeachersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewAllTeachers();
            }
        });
        buttonPanel.add(viewTeachersButton);

        JButton viewAllStudentsButton = new JButton("View All Students");
        viewAllStudentsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewAllStudents();
            }
        });
        buttonPanel.add(viewAllStudentsButton);

        JButton getExamInfoButton = new JButton("Get Exams Info");
        getExamInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getExamInfo();
            }
        });
        buttonPanel.add(getExamInfoButton);

        JButton prepareTableButton = new JButton("Prepare Table");
        prepareTableButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prepareTable();
            }
        });
        buttonPanel.add(prepareTableButton);

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



	public void addTeacher() {
        JTextField idField = new JTextField();
        JTextField subjectField = new JTextField();
        JTextField notfTypeField = new JTextField();
        JTextField usernameField = new JTextField();
        JTextField fullNameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField salaryField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField passField = new JTextField();


        Object[] inputs = {
                "Teacher ID:", idField,
                "Subject:", subjectField,
                "Notification Type:", notfTypeField,
                "Username:", usernameField,
                "Full Name:", fullNameField,
                "Age:", ageField,
                "Salary:", salaryField,
                "Email:", emailField,
                "Password:", passField
        };
        int index = inputs.length;
        int option = JOptionPane.showConfirmDialog(frame, inputs, "Add Teacher", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            int id = Integer.parseInt(idField.getText());
            String subject = subjectField.getText();
            String NotfType = notfTypeField.getText();
            String username = usernameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String fullName = fullNameField.getText();
            int salary = Integer.parseInt(salaryField.getText());
            String email = emailField.getText();
            String pass = passField.getText();

            NormalTeacher normalTeacher = new NormalTeacher();
            normalTeacher.setAge(age);
            normalTeacher.setTeacherId(id);
            normalTeacher.setFullName(fullName);
            normalTeacher.setNotificationType(NotfType);
            normalTeacher.setSubject(subject);
            normalTeacher.setUsername(username);
            normalTeacher.setSalary(salary);
            normalTeacher.setEmail(email);
            normalTeacher.setPassword(pass);

            p.getTeachers().put(normalTeacher.getTeacherId(), normalTeacher);
            outputTextArea.append("Teacher Added Successfully!\n");        }
    }

    public void editTeacher() {
        JTextField idField = new JTextField();
        JTextField attributeNameField = new JTextField();
        JTextField newValueField = new JTextField();

        Object[] inputs = {
                "Teacher ID:", idField,
                "Attribute Name:", attributeNameField,
                "New Value:", newValueField
        };

        int option = JOptionPane.showConfirmDialog(frame, inputs, "Edit Teacher", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            int id = Integer.parseInt(idField.getText());
            String attributeName = attributeNameField.getText();
            String newValue = newValueField.getText();

            NormalTeacher teacher = p.getTeachers().get(id);

            if (teacher != null) {
                try {
                    Method setterMethod = NormalTeacher.class.getMethod("set" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1), String.class);

                    // Exception handling for number values
                    if (attributeName.equals("age")) {
                        int age = Integer.parseInt(newValue);
                        setterMethod.invoke(teacher, age);
                    } else if (attributeName.equals("salary")) {
                        double salary = Double.parseDouble(newValue);
                        setterMethod.invoke(teacher, salary);
                    } else {
                        setterMethod.invoke(teacher, newValue);
                    }

                    outputTextArea.append("Teacher details updated successfully!\n");
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                	outputTextArea.append("Error editing teacher details: ");
                }
            } else {
            	outputTextArea.append("Teacher not found!");
            }
        }
    }

    private void prepareTable() {
    	outputTextArea.setText("");
        p.PrepareTimetable();
        outputTextArea.append("Timetable prepared!\n");
        outputTextArea.append(p.getTableDetails()); // Append timetable details to the JTextArea
    }

    public void viewAllTeachers() {
        outputTextArea.setText(""); // Clear the text area before appending new content
        for (NormalTeacher teacher : p.getTeachers().values()) {
            outputTextArea.append("Teacher ID: " + teacher.getTeacherId() + "\n" +
                    "Name: " + teacher.getFullName() + "\n" +
                    "Email: " + teacher.getEmail() + "\n" +
                    "Age: " + teacher.getAge() + "\n" +
                    "Salary: " + teacher.getSalary() + "\n" +
                    "Subject: " + teacher.getSubject() + "\n" +
                    "Notification: " + teacher.getNotificationType() + "\n\n");
        }
    }

    public void deleteTeacher() {
        JTextField idField = new JTextField();

        Object[] inputs = {
                "Teacher ID:", idField
        };

        int option = JOptionPane.showConfirmDialog(frame, inputs, "Delete Teacher", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            int id = Integer.parseInt(idField.getText());

            if (p.getTeachers().containsKey(id)) {
                p.DeleteTeacher(id);
                outputTextArea.append("Teacher deleted successfully!\n");
            } else {
            	outputTextArea.append("Teacher not found!");
            }
        }
    }

    public void getExamInfo() {
    	outputTextArea.setText("");
        JTextField examIdField = new JTextField();
        JTextField subjectField = new JTextField();

        Object[] inputs = {
                "Exam ID:", examIdField,
                "Subject:", subjectField
        };

        int option = JOptionPane.showConfirmDialog(frame, inputs, "Get Exam Information", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            int examId = Integer.parseInt(examIdField.getText());
            String subject = subjectField.getText();

            ExamService proxy = new ExamProxy(new Exam(examId, subject));
            String examDetails = proxy.ViewExam(); // Get the exam details

            outputTextArea.append(examDetails + "\n"); // Append exam details to the JTextArea
        }
    }

    public void viewAllStudents() {
        outputTextArea.setText("");
        p.viewAllStudentsDetails(outputTextArea); // Pass the JTextArea to append details to it
    }

    public static void main(String[] args) {
        new PrinceCopied();
    }
}
