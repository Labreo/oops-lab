package Exp14;

import java.awt.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class StudentTableViewer extends JFrame {

    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JLabel statusLabel;
    private JButton refreshButton;
    private TableRowSorter<DefaultTableModel> rowSorter;

    private Connection connection;

    public StudentTableViewer() {
        setTitle("Student Records Database Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(12, 12));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(mainPanel);

        JPanel topPanel = new JPanel(new BorderLayout(10, 0));
        topPanel.add(new JLabel("Search by Student Name:"), BorderLayout.WEST);
        searchField = new JTextField();
        topPanel.add(searchField, BorderLayout.CENTER);
        refreshButton = new JButton("Refresh Data");
        topPanel.add(refreshButton, BorderLayout.EAST);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        String[] columns = {"ID", "Name", "Email", "Course", "Year", "Gender"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        studentTable = new JTable(tableModel);
        rowSorter = new TableRowSorter<>(tableModel);
        studentTable.setRowSorter(rowSorter);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        statusLabel = new JLabel("Total number of records currently displayed: 0");
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        mainPanel.add(statusLabel, BorderLayout.SOUTH);

        initializeDatabaseConnection();
        loadStudentRecords();

        refreshButton.addActionListener(e -> {
            searchField.setText("");
            loadStudentRecords();
        });

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filterTable(); }
            public void removeUpdate(DocumentEvent e) { filterTable(); }
            public void changedUpdate(DocumentEvent e) { filterTable(); }
        });

        rowSorter.addRowSorterListener(e -> updateRecordCount());
    }

    private void initializeDatabaseConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/exp14_db";
            String user = "root";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Connection Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadStudentRecords() {
        if (connection == null) return;

        tableModel.setRowCount(0);
        String sql = "SELECT id, name, email, course, year, gender FROM students";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("id"));
                row.add(rs.getString("name"));
                row.add(rs.getString("email"));
                row.add(rs.getString("course"));
                row.add(rs.getString("year"));
                row.add(rs.getString("gender"));
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error Fetching Data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        updateRecordCount();
    }

    private void filterTable() {
        String text = searchField.getText().trim();
        if (text.isEmpty()) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 1));
        }
    }

    private void updateRecordCount() {
        int count = studentTable.getRowCount();
        statusLabel.setText("Total number of records currently displayed: " + count);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new StudentTableViewer().setVisible(true);
        });
    }
}