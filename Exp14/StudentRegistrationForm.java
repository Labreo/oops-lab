package Exp14;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class StudentRegistrationForm extends JFrame {

    private JTextField nameField, emailField, idDisplayField;
    private JComboBox<String> courseBox, yearBox;
    private JRadioButton maleRadio, femaleRadio;
    private ButtonGroup genderGroup;
    private JPasswordField pinField;
    private JButton registerButton, resetButton;

    private Connection connection;

    public StudentRegistrationForm() {
        setTitle("Student Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 500);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(mainPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("Full Name:"), gbc);
        nameField = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("Email ID:"), gbc);
        emailField = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(new JLabel("Course:"), gbc);
        String[] courses = {"Computer Engineering", "Information Technology", "Electronics", "Mechanical"};
        courseBox = new JComboBox<>(courses);
        gbc.gridx = 1;
        mainPanel.add(courseBox, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        mainPanel.add(new JLabel("Year:"), gbc);
        String[] years = {"First Year", "Second Year", "Third Year", "Fourth Year"};
        yearBox = new JComboBox<>(years);
        gbc.gridx = 1;
        mainPanel.add(yearBox, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        mainPanel.add(new JLabel("Gender:"), gbc);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        gbc.gridx = 1;
        mainPanel.add(genderPanel, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        mainPanel.add(new JLabel("Security PIN:"), gbc);
        pinField = new JPasswordField(20);
        gbc.gridx = 1;
        mainPanel.add(pinField, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        mainPanel.add(new JLabel("Generated ID:"), gbc);
        idDisplayField = new JTextField("Not Registered Yet");
        idDisplayField.setEditable(false);
        idDisplayField.setFont(new Font("SansSerif", Font.BOLD, 12));
        idDisplayField.setForeground(Color.BLUE);
        gbc.gridx = 1;
        mainPanel.add(idDisplayField, gbc);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        registerButton = new JButton("Register");
        resetButton = new JButton("Reset");
        buttonPanel.add(registerButton);
        buttonPanel.add(resetButton);

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 8, 8, 8);
        mainPanel.add(buttonPanel, gbc);

        initializeDatabaseConnection();

        registerButton.addActionListener(e -> handleRegistration());
        resetButton.addActionListener(e -> clearForm());
    }

    private void initializeDatabaseConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/exp14_db";
            String user = "root";
            String password = ""; 
            
            connection = DriverManager.getConnection(url, user, password);

            try (Statement stmt = connection.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS students (" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name VARCHAR(100) NOT NULL, " +
                        "email VARCHAR(100) NOT NULL, " +
                        "course VARCHAR(100) NOT NULL, " +
                        "year VARCHAR(50) NOT NULL, " +
                        "gender VARCHAR(20) NOT NULL, " +
                        "pin VARCHAR(20) NOT NULL, " +
                        "PRIMARY KEY (id))";
                stmt.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Init Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleRegistration() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String course = (String) courseBox.getSelectedItem();
        String year = (String) yearBox.getSelectedItem();
        String pin = new String(pinField.getPassword()).trim();
        
        String gender = "";
        if (maleRadio.isSelected()) {
            gender = "Male";
        } else if (femaleRadio.isSelected()) {
            gender = "Female";
        }

        if (name.isEmpty() || email.isEmpty() || gender.isEmpty() || pin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All input fields must be filled completely.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "INSERT INTO students (name, email, course, year, gender, pin) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, course);
            pstmt.setString(4, year);
            pstmt.setString(5, gender);
            pstmt.setString(6, pin);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long generatedId = generatedKeys.getLong(1);
                        idDisplayField.setText("ID: " + generatedId);
                        JOptionPane.showMessageDialog(this, "Registration Successful! Generated Student ID is: " + generatedId, "Success", JOptionPane.INFORMATION_MESSAGE);
                        clearFormFieldsOnly();
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error during registration: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFormFieldsOnly() {
        nameField.setText("");
        emailField.setText("");
        courseBox.setSelectedIndex(0);
        yearBox.setSelectedIndex(0);
        genderGroup.clearSelection();
        pinField.setText("");
    }

    private void clearForm() {
        clearFormFieldsOnly();
        idDisplayField.setText("Not Registered Yet");
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new StudentRegistrationForm().setVisible(true);
        });
    }
}