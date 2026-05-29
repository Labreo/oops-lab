package assignment;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.*;

public class AirlineReservationApp extends JFrame {
    JTextField t1, t2, t3, t4, t5, t6, t7;
    JComboBox cb1;
    JCheckBox c1, c2;
    JButton b1, b2;
    JTable tab;
    DefaultTableModel dtm;
    Connection con;
    String passPath = "";

    AirlineReservationApp() {
        setTitle("Airline");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(10, 2));

        p1.add(new JLabel("Ticket Number:"));
        t1 = new JTextField(10);
        p1.add(t1);

        p1.add(new JLabel("Passenger Name:"));
        t2 = new JTextField(10);
        p1.add(t2);

        p1.add(new JLabel("Flight Number:"));
        t3 = new JTextField(10);
        p1.add(t3);

        p1.add(new JLabel("Source:"));
        t4 = new JTextField(10);
        p1.add(t4);

        p1.add(new JLabel("Destination:"));
        t5 = new JTextField(10);
        p1.add(t5);

        p1.add(new JLabel("Seat Class:"));
        String classes[] = {"Economy", "Business", "First Class"};
        cb1 = new JComboBox(classes);
        p1.add(cb1);

        p1.add(new JLabel("Meal:"));
        JPanel meal = new JPanel();
        c1 = new JCheckBox("Veg");
        c2 = new JCheckBox("Non-Veg");
        meal.add(c1);
        meal.add(c2);
        p1.add(meal);

        p1.add(new JLabel("Price:"));
        t6 = new JTextField(10);
        p1.add(t6);

        p1.add(new JLabel("Travel Date:"));
        t7 = new JTextField(10);
        p1.add(t7);

        p1.add(new JLabel("Passport:"));
        b1 = new JButton("Upload");
        p1.add(b1);
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(p1, BorderLayout.CENTER);

        b2 = new JButton("Submit");
        JPanel p2 = new JPanel();
        p2.add(b2);
        topPanel.add(p2, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        String cols[] = {"Ticket", "Name", "Flight", "Source", "Dest", "Class", "Meal", "Price", "Date", "Passport"};
        dtm = new DefaultTableModel(cols, 0);
        tab = new JTable(dtm);
        add(new JScrollPane(tab), BorderLayout.CENTER);

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline_db", "root", "");
            Statement s = con.createStatement();
            s.execute("CREATE TABLE IF NOT EXISTS res (tno VARCHAR(50) PRIMARY KEY, name VARCHAR(100), fno VARCHAR(50), src VARCHAR(100), dest VARCHAR(100), cls VARCHAR(50), meal VARCHAR(50), prc DOUBLE, dt VARCHAR(50), pass VARCHAR(255))");
        } catch(Exception e) {
            System.out.println(e);
        }

        loadTableData();

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jf = new JFileChooser();
                int r = jf.showOpenDialog(null);
                if(r == JFileChooser.APPROVE_OPTION) {
                    passPath = jf.getSelectedFile().getAbsolutePath();
                    System.out.println("Passport selected: " + passPath);
                }
            }
        });

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement ps = con.prepareStatement("insert into res values(?,?,?,?,?,?,?,?,?,?)");
                    ps.setString(1, t1.getText());
                    ps.setString(2, t2.getText());
                    ps.setString(3, t3.getText());
                    ps.setString(4, t4.getText());
                    ps.setString(5, t5.getText());
                    ps.setString(6, cb1.getSelectedItem().toString());
                    String m = "";
                    if(c1.isSelected()) m += "Veg ";
                    if(c2.isSelected()) m += "Non-Veg";
                    ps.setString(7, m);
                    ps.setDouble(8, Double.parseDouble(t6.getText()));
                    ps.setString(9, t7.getText());
                    ps.setString(10, passPath);
                    ps.executeUpdate();
                    System.out.println("Saved!");
                    
            
                    loadTableData();
                } catch(Exception ex) {
                    System.out.println(ex);
                }
            }
        });
    }

    void loadTableData() {
        try {
            dtm.setRowCount(0);
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from res");
            while(rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                v.add(rs.getString(6));
                v.add(rs.getString(7));
                v.add(rs.getDouble(8));
                v.add(rs.getString(9));
                v.add(rs.getString(10));
                dtm.addRow(v);
            }
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    public static void main(String args[]) {
        AirlineReservationApp a = new AirlineReservationApp();
        a.setVisible(true);
    }
}
