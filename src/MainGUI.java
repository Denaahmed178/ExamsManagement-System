import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {

    public MainGUI() {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JButton loginButton = new JButton("Supervising Teacher GUI");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginPage();
            }
        });
        panel.add(loginButton);

        JButton normalTeacherButton = new JButton("Normal Teacher GUI");
        normalTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openNormalTeacherGUI();
            }
        });
        panel.add(normalTeacherButton);


        JButton princeCopiedButton = new JButton("Prince Copied");
        princeCopiedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPrinceCopied();
            }
        });
        panel.add(princeCopiedButton);

        add(panel);
        setVisible(true);
    }

    private void openLoginPage() {
        // Code to open LoginPage goes here
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
        dispose(); // Close the main menu window
    }

    private void openNormalTeacherGUI() {
        // Code to open NormalTeacherGUI goes here
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new NormalTeacherGUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        dispose(); // Close the main menu window
    }



    private void openPrinceCopied() {
        // Code to open PrinceCopied goes here
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new PrinceCopied();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        dispose(); // Close the main menu window
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new MainGUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
