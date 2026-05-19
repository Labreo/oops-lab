package Exp13;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

class StudentModel {
    private String name;
    private String course;
    private boolean isHosteler;

    public void setData(String name, String course, boolean isHosteler) {
        this.name = name;
        this.course = course;
        this.isHosteler = isHosteler;
    }

    public String getConfirmationMessage() {
        return "Success: " + name + " registered for " + course + " (Hostel: " + (isHosteler ? "Yes" : "No") + ")";
    }
}

class StudentView extends JFrame {
    private JTextField nameField;
    private JComboBox<String> courseBox;
    private JCheckBox hostelerCheck;
    private JButton submitButton;
    private JLabel messageLabel;

    public StudentView() {
        setTitle("Student Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Student Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nameLabel, gbc);

        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(220, 30));
        nameField.setOpaque(true);
        nameField.setBorder(new LineBorder(Color.GRAY, 1));
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(nameField, gbc);

        JLabel courseLabel = new JLabel("Course:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(courseLabel, gbc);

        String[] courses = {"Computer Engineering", "Information Technology", "Electronics"};
        courseBox = new JComboBox<>(courses);
        courseBox.setPreferredSize(new Dimension(220, 30));
        courseBox.setOpaque(true);
        courseBox.setBorder(new LineBorder(Color.GRAY, 1));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(courseBox, gbc);

        JLabel hostelerLabel = new JLabel("Accommodation:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(hostelerLabel, gbc);

        hostelerCheck = new JCheckBox("Require Hostel");
        hostelerCheck.setPreferredSize(new Dimension(220, 30));
        hostelerCheck.setOpaque(true);
        hostelerCheck.setBorder(new LineBorder(Color.GRAY, 1));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(hostelerCheck, gbc);

        submitButton = new JButton("Submit Registration");
        submitButton.setToolTipText("Click to save student details");
        submitButton.setMnemonic(KeyEvent.VK_S);
        submitButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        submitButton.setBackground(new Color(70, 130, 180));
        submitButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(submitButton, gbc);

        messageLabel = new JLabel(" ");
        messageLabel.setForeground(new Color(34, 139, 34));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(messageLabel, gbc);

        pack();
        setLocationRelativeTo(null);
    }

    public String getNameInput() {
        return nameField.getText();
    }

    public String getCourseInput() {
        return (String) courseBox.getSelectedItem();
    }

    public boolean getHostelerInput() {
        return hostelerCheck.isSelected();
    }

    public void setConfirmationMessage(String message) {
        messageLabel.setText(message);
    }

    public void addSubmitListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void clearForm() {
        nameField.setText("");
        courseBox.setSelectedIndex(0);
        hostelerCheck.setSelected(false);
    }
}

class StudentController {
    private StudentModel model;
    private StudentView view;

    public StudentController(StudentModel model, StudentView view) {
        this.model = model;
        this.view = view;

        this.view.addSubmitListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = view.getNameInput();
                String course = view.getCourseInput();
                boolean isHosteler = view.getHostelerInput();

                if (name.trim().isEmpty()) {
                    view.setConfirmationMessage("Error: Name field cannot be empty.");
                    return;
                }

                model.setData(name, course, isHosteler);
                view.setConfirmationMessage(model.getConfirmationMessage());
                view.clearForm();
            }
        });
    }
}

public class StudentRegistrationApp {
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StudentModel model = new StudentModel();
                StudentView view = new StudentView();
                new StudentController(model, view);
                view.setVisible(true);
            }
        });
    }
}