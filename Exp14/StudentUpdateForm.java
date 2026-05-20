package Exp14;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class StudentUpdateForm extends JFrame {

    private JTextField searchIdField;
    private JButton fetchButton;

    private JTextField nameField, emailField, courseField;
    private JButton updateButton;

    private Connection connection;

    public StudentUpdateForm() {
        setTitle("Student Record Modifier");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 380);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(mainPanel);

        JPanel searchPanel = new JPanel(new BorderLayout(10, 0));
        searchPanel.setBorder(new TitledBorder("Search Student"));
        searchPanel.add(new JLabel("Enter Student ID:"), BorderLayout.WEST);
        searchIdField = new JTextField();
        searchPanel.add(searchIdField, BorderLayout.CENTER);
        fetchButton = new JButton("Fetch Details");
        searchPanel.add(fetchButton, BorderLayout.EAST);
        mainPanel.add(searchPanel, BorderLayout.NORTH);

        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBorder(new TitledBorder("Editable Student Information"));
        mainPanel.add(detailsPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        detailsPanel.add(new JLabel("Full Name:"), gbc);
        nameField = new JTextField(20);
        nameField.setEnabled(false);
        gbc.gridx = 1;
        detailsPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        detailsPanel.add(new JLabel("Email ID:"), gbc);
        emailField = new JTextField(20);
        emailField.setEnabled(false);
        gbc.gridx = 1;
        detailsPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        detailsPanel.add(new JLabel("Course:"), gbc);
        courseField = new JTextField(20);
        courseField.setEnabled(false);
        gbc.gridx = 1;
        detailsPanel.add(courseField, gbc);

        updateButton = new JButton("Update Information");
        updateButton.setEnabled(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 8, 8, 8);
        detailsPanel.add(updateButton, gbc);

        initializeDatabaseConnection();

        fetchButton.addActionListener(e -> fetchStudentDetails());
        updateButton.addActionListener(e -> updateStudentDetails());
    }

    private void initializeDatabaseConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/exp14_db";
            String user = "root";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Connection Failed: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fetchStudentDetails() {
        String idText = searchIdField.getText().trim();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out the Student ID field to search.", "Validation Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "SELECT name, email, course FROM students WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, idText);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    nameField.setText(rs.getString("name"));
                    emailField.setText(rs.getString("email"));
                    courseField.setText(rs.getString("course"));

                    nameField.setEnabled(true);
                    emailField.setEnabled(true);
                    courseField.setEnabled(true);
                    updateButton.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(this, "No record found matching Student ID: " + idText, "Not Found",
                            JOptionPane.INFORMATION_MESSAGE);
                    clearEditableFields();
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching data details: " + ex.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStudentDetails() {
        String idText = searchIdField.getText().trim();
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String course = courseField.getText().trim();

        if (name.isEmpty() || email.isEmpty() || course.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fields cannot be left blank for update.", "Validation Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "UPDATE students SET name = ?, email = ?, course = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, course);
            pstmt.setString(4, idText);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Success: Student details updated successfully.", "Update Status",
                        JOptionPane.INFORMATION_MESSAGE);
                searchIdField.setText("");
                clearEditableFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failure: Record modification could not be completed.",
                        "Update Status", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Database processing error during modification execution: " + ex.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearEditableFields() {
        nameField.setText("");
        emailField.setText("");
        courseField.setText("");

        nameField.setEnabled(false);
        emailField.setEnabled(false);
        courseField.setEnabled(false);
        updateButton.setEnabled(false);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new StudentUpdateForm().setVisible(true);
        });
    }
}