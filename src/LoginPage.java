import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginPage() {
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Placeholder
        panel.add(loginButton);

        add(panel);
        setVisible(true);
    }

    private void performLogin() {
        String enteredEmail = emailField.getText();
        char[] enteredPassword = passwordField.getPassword();
        String enteredPasswordString = new String(enteredPassword);

        // Check if the email and password are correct (for simplicity, using hardcoded values)
        if ("john@example.com".equals(enteredEmail) && "password123".equals(enteredPasswordString)) {
            // If login successful, open the SupervisingTeacherGUI
            dispose(); // Close the login window
            openSupervisingTeacherGUI();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid email or password. Please try again.");
        }

        // Clear the password field for security reasons
        passwordField.setText("");
    }

    private void openSupervisingTeacherGUI() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new SupervisingTeacherGUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new LoginPage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
