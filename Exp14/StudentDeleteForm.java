package Exp14;

import java.awt.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class StudentDeleteForm extends JFrame {

    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JButton deleteButton, refreshButton;
    private JLabel totalRecordsLabel;

    private Connection connection;

    public StudentDeleteForm() {
        setTitle("Student Records Remover");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 450);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(12, 12));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(mainPanel);

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        deleteButton = new JButton("Delete Selected Record");
        refreshButton = new JButton("Refresh Data");
        controlPanel.add(deleteButton);
        controlPanel.add(refreshButton);
        mainPanel.add(controlPanel, BorderLayout.NORTH);

        String[] columns = { "ID", "Name", "Email", "Course" };
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        studentTable = new JTable(tableModel);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        totalRecordsLabel = new JLabel("Total number of records currently displayed: 0");
        totalRecordsLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        mainPanel.add(totalRecordsLabel, BorderLayout.SOUTH);

        initializeDatabaseConnection();
        loadStudentRecords();

        refreshButton.addActionListener(e -> loadStudentRecords());
        deleteButton.addActionListener(e -> processRecordDeletion());
    }

    private void initializeDatabaseConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/exp14_db";
            String user = "root";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Connection Error: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadStudentRecords() {
        if (connection == null)
            return;

        tableModel.setRowCount(0);
        String sql = "SELECT id, name, email, course FROM students";

        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("id"));
                row.add(rs.getString("name"));
                row.add(rs.getString("email"));
                row.add(rs.getString("course"));
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error Fetching Data: " + ex.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        updateRecordCountLabel();
    }

    private void processRecordDeletion() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a record from the JTable before deletion.",
                    "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String targetId = studentTable.getValueAt(selectedRow, 0).toString();
        String targetName = studentTable.getValueAt(selectedRow, 1).toString();

        int userConfirmation = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to completely delete the record for " + targetName + "?",
                "Confirmation Required",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (userConfirmation == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM students WHERE id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, targetId);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    tableModel.removeRow(selectedRow);
                    updateRecordCountLabel();
                    JOptionPane.showMessageDialog(this, "Success: Record removed successfully.", "Deletion Status",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Failure: Record could not be removed from the database.",
                            "Deletion Status", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                        "Database processing error during deletion execution: " + ex.getMessage(), "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateRecordCountLabel() {
        int totalRows = tableModel.getRowCount();
        totalRecordsLabel.setText("Total number of records currently displayed: " + totalRows);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new StudentDeleteForm().setVisible(true);
        });
    }
}