package Exp14;

import java.awt.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class StudentDatabaseApp extends JFrame {

    private JTextField hostField, portField, dbNameField, urlField;
    private JComboBox<String> dbTypeBox;
    private JPasswordField passField;
    private JButton connectButton;
    private JLabel connStatusLabel;

    private JTextField studentNameField, studentCourseField;
    private JButton insertButton, updateButton, deleteButton;
    private JTable recordTable;
    private DefaultTableModel tableModel;
    private JLabel totalRecordsLabel;

    private Connection connection;
    private String currentEditingId = null;

    public StudentDatabaseApp() {
        setTitle("GUI Application Using Database Connectivity");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(mainPanel);

        JPanel leftPanel = new JPanel(new BorderLayout(10, 10));
        leftPanel.setPreferredSize(new Dimension(420, 0));
        mainPanel.add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        JPanel dbConfigPanel = new JPanel(new GridBagLayout());
        dbConfigPanel.setBorder(new TitledBorder("Database Configuration"));
        leftPanel.add(dbConfigPanel, BorderLayout.NORTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        dbConfigPanel.add(new JLabel("DB Type:"), gbc);
        dbTypeBox = new JComboBox<>(new String[]{"MySQL", "PostgreSQL", "SQLite"});
        gbc.gridx = 1;
        dbConfigPanel.add(dbTypeBox, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        dbConfigPanel.add(new JLabel("Host:"), gbc);
        hostField = new JTextField("localhost");
        gbc.gridx = 1;
        dbConfigPanel.add(hostField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        dbConfigPanel.add(new JLabel("Port:"), gbc);
        portField = new JTextField("3306");
        gbc.gridx = 1;
        dbConfigPanel.add(portField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        dbConfigPanel.add(new JLabel("DB Name:"), gbc);
        dbNameField = new JTextField("exp14_db");
        gbc.gridx = 1;
        dbConfigPanel.add(dbNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        dbConfigPanel.add(new JLabel("Password:"), gbc);
        passField = new JPasswordField("");
        gbc.gridx = 1;
        dbConfigPanel.add(passField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        dbConfigPanel.add(new JLabel("JDBC URL:"), gbc);
        urlField = new JTextField();
        urlField.setEditable(false);
        urlField.setBackground(new Color(240, 240, 240));
        gbc.gridx = 1;
        dbConfigPanel.add(urlField, gbc);

        connectButton = new JButton("Connect to Database");
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        dbConfigPanel.add(connectButton, gbc);

        connStatusLabel = new JLabel("Not Connected", SwingConstants.CENTER);
        connStatusLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        connStatusLabel.setForeground(Color.RED);
        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        dbConfigPanel.add(connStatusLabel, gbc);

        JPanel entryPanel = new JPanel(new GridBagLayout());
        entryPanel.setBorder(new TitledBorder("Student Data Management"));
        leftPanel.add(entryPanel, BorderLayout.CENTER);

        GridBagConstraints gbcEntry = new GridBagConstraints();
        gbcEntry.insets = new Insets(8, 8, 8, 8);
        gbcEntry.fill = GridBagConstraints.HORIZONTAL;

        gbcEntry.gridx = 0; gbcEntry.gridy = 0;
        entryPanel.add(new JLabel("Student Name:"), gbcEntry);
        studentNameField = new JTextField();
        studentNameField.setEnabled(false);
        gbcEntry.gridx = 1;
        entryPanel.add(studentNameField, gbcEntry);

        gbcEntry.gridx = 0; gbcEntry.gridy = 1;
        entryPanel.add(new JLabel("Course Name:"), gbcEntry);
        studentCourseField = new JTextField();
        studentCourseField.setEnabled(false);
        gbcEntry.gridx = 1;
        entryPanel.add(studentCourseField, gbcEntry);

        JPanel actionButtonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        insertButton = new JButton("Insert");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        insertButton.setEnabled(false);
        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);
        actionButtonPanel.add(insertButton);
        actionButtonPanel.add(updateButton);
        actionButtonPanel.add(deleteButton);

        gbcEntry.gridx = 0; gbcEntry.gridy = 2; gbcEntry.gridwidth = 2;
        entryPanel.add(actionButtonPanel, gbcEntry);

        String[] columnNames = {"ID", "Student Name", "Course"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        recordTable = new JTable(tableModel);
        recordTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane tableScrollPane = new JScrollPane(recordTable);
        rightPanel.add(tableScrollPane, BorderLayout.CENTER);

        totalRecordsLabel = new JLabel("Total Records: 0", SwingConstants.LEFT);
        totalRecordsLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        rightPanel.add(totalRecordsLabel, BorderLayout.SOUTH);

        DocumentListener urlUpdater = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { updateUrl(); }
            public void removeUpdate(DocumentEvent e) { updateUrl(); }
            public void changedUpdate(DocumentEvent e) { updateUrl(); }
        };

        hostField.getDocument().addDocumentListener(urlUpdater);
        portField.getDocument().addDocumentListener(urlUpdater);
        dbNameField.getDocument().addDocumentListener(urlUpdater);
        dbTypeBox.addActionListener(e -> {
            String selectedType = (String) dbTypeBox.getSelectedItem();
            if ("MySQL".equals(selectedType)) {
                portField.setText("3306");
                portField.setEnabled(true);
                hostField.setEnabled(true);
            } else if ("PostgreSQL".equals(selectedType)) {
                portField.setText("5432");
                portField.setEnabled(true);
                hostField.setEnabled(true);
            } else if ("SQLite".equals(selectedType)) {
                portField.setText("");
                portField.setEnabled(false);
                hostField.setEnabled(false);
            }
            updateUrl();
        });

        updateUrl();

        connectButton.addActionListener(e -> establishDatabaseConnection());
        insertButton.addActionListener(e -> insertRecord());
        updateButton.addActionListener(e -> updateRecord());
        deleteButton.addActionListener(e -> deleteRecord());

        recordTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                populateFormFromSelectedRow();
            }
        });
    }

    private void updateUrl() {
        String dbType = (String) dbTypeBox.getSelectedItem();
        String host = hostField.getText().trim();
        String port = portField.getText().trim();
        String dbName = dbNameField.getText().trim();

        String url = "";
        if ("MySQL".equals(dbType)) {
            url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
        } else if ("PostgreSQL".equals(dbType)) {
            url = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
        } else if ("SQLite".equals(dbType)) {
            url = "jdbc:sqlite:" + dbName + ".db";
        }
        urlField.setText(url);
    }

    private void establishDatabaseConnection() {
        String url = urlField.getText();
        String user = "root"; 
        String password = new String(passField.getPassword());

        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }

            connection = DriverManager.getConnection(url, user, password);
            connStatusLabel.setText("Connection Success");
            connStatusLabel.setForeground(new Color(34, 139, 34));

            studentNameField.setEnabled(true);
            studentCourseField.setEnabled(true);
            insertButton.setEnabled(true);
            updateButton.setEnabled(true);
            deleteButton.setEnabled(true);

            initializeDatabaseStructure();
            fetchAndPopulateTable();

        } catch (SQLException ex) {
            connStatusLabel.setText("Connection Failure: " + ex.getMessage());
            connStatusLabel.setForeground(Color.RED);
            
            studentNameField.setEnabled(false);
            studentCourseField.setEnabled(false);
            insertButton.setEnabled(false);
            updateButton.setEnabled(false);
            deleteButton.setEnabled(false);
            
            tableModel.setRowCount(0);
            updateRecordCountLabel();
        }
    }

    private void initializeDatabaseStructure() {
        try (Statement stmt = connection.createStatement()) {
            String dbType = (String) dbTypeBox.getSelectedItem();
            String sql;
            if ("SQLite".equals(dbType)) {
                sql = "CREATE TABLE IF NOT EXISTS students (" +
                      "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                      "name TEXT NOT NULL, " +
                      "email TEXT, " +
                      "course TEXT NOT NULL, " +
                      "year TEXT, " +
                      "gender TEXT, " +
                      "pin TEXT)";
            } else {
                sql = "CREATE TABLE IF NOT EXISTS students (" +
                      "id INT NOT NULL AUTO_INCREMENT, " +
                      "name VARCHAR(100) NOT NULL, " +
                      "email VARCHAR(100), " +
                      "course VARCHAR(100) NOT NULL, " +
                      "year VARCHAR(50), " +
                      "gender VARCHAR(20), " +
                      "pin VARCHAR(20), " +
                      "PRIMARY KEY (id))";
            }
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error initializing table: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fetchAndPopulateTable() {
        tableModel.setRowCount(0);
        String sql = "SELECT id, name, course FROM students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getString("id"));
                row.add(rs.getString("name"));
                row.add(rs.getString("course"));
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        updateRecordCountLabel();
    }

    private void insertRecord() {
        String name = studentNameField.getText().trim();
        String course = studentCourseField.getText().trim();

        if (name.isEmpty() || course.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both Name and Course fields.", "Validation Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "INSERT INTO students (name, course) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.setString(2, course);
            
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                String generatedId = "";
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getString(1);
                    }
                }
                
                tableModel.addRow(new Object[]{generatedId, name, course});
                updateRecordCountLabel();
                clearFormFields();
                
                JOptionPane.showMessageDialog(this, "Success: Data successfully inserted from GUI to the database.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failure: Record could not be inserted.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error during insert operation: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void populateFormFromSelectedRow() {
        int selectedRow = recordTable.getSelectedRow();
        if (selectedRow != -1) {
            currentEditingId = recordTable.getValueAt(selectedRow, 0).toString();
            String name = recordTable.getValueAt(selectedRow, 1).toString();
            String course = recordTable.getValueAt(selectedRow, 2).toString();

            studentNameField.setText(name);
            studentCourseField.setText(course);
        }
    }

    private void updateRecord() {
        if (currentEditingId == null) {
            JOptionPane.showMessageDialog(this, "Please select an existing record from the JTable before editing.", "Validation Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String name = studentNameField.getText().trim();
        String course = studentCourseField.getText().trim();

        if (name.isEmpty() || course.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name and Course components cannot be blank.", "Validation Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "UPDATE students SET name = ?, course = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, course);
            pstmt.setString(3, currentEditingId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                int selectedRow = recordTable.getSelectedRow();
                tableModel.setValueAt(name, selectedRow, 1);
                tableModel.setValueAt(course, selectedRow, 2);
                
                clearFormFields();
                JOptionPane.showMessageDialog(this, "Success: Record updated immediately in the JTable.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error during update execution: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteRecord() {
        int selectedRow = recordTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "User selects a record from the JTable before deletion.", "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String targetId = recordTable.getValueAt(selectedRow, 0).toString();
        String targetName = recordTable.getValueAt(selectedRow, 1).toString();

        int userConfirmation = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you want to completely delete the record for " + targetName + "?", 
                "Confirmation Dialog Required", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE
        );

        if (userConfirmation == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM students WHERE id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, targetId);
                
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    tableModel.removeRow(selectedRow);
                    updateRecordCountLabel();
                    clearFormFields();
                    JOptionPane.showMessageDialog(this, "Success: Deleted row is removed from the DefaultTableModel.", "Deletion Completed", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database error during deletion process: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateRecordCountLabel() {
        int totalRows = tableModel.getRowCount();
        totalRecordsLabel.setText("Total number of records fetched: " + totalRows);
    }

    private void clearFormFields() {
        studentNameField.setText("");
        studentCourseField.setText("");
        recordTable.clearSelection();
        currentEditingId = null;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new StudentDatabaseApp().setVisible(true);
        });
    }
}