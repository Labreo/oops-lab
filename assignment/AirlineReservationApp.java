package assignment;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class AirlineReservationApp extends JFrame {
    JTextField t1, t2, t3, t4, t5, t6, t7;
    JComboBox cb1; JCheckBox c1, c2;
    JButton b1, bAdd, bUpdate, bDelete, bSearch, bReset, bExit;
    JTable tab; DefaultTableModel dtm;
    Connection con; String passPath = "";

    AirlineReservationApp() {
        setTitle("Airline"); setSize(800, 600); setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JMenuBar mb = new JMenuBar();
        JMenu mFile = new JMenu("File"), mEdit = new JMenu("Edit"), mHelp = new JMenu("Help");
        JMenuItem miExit = new JMenuItem("Exit"), miAbout = new JMenuItem("About");
        mFile.add(miExit); mHelp.add(miAbout); mb.add(mFile); mb.add(mEdit); mb.add(mHelp);
        setJMenuBar(mb);
        miExit.addActionListener(e -> System.exit(0));
        miAbout.addActionListener(e -> JOptionPane.showMessageDialog(this, "Airline App v1.0"));

        JPanel p1 = new JPanel(new GridLayout(10, 2));
        p1.add(new JLabel("Ticket Number:")); p1.add(t1 = new JTextField(10));
        p1.add(new JLabel("Passenger Name:")); p1.add(t2 = new JTextField(10));
        p1.add(new JLabel("Flight Number:")); p1.add(t3 = new JTextField(10));
        p1.add(new JLabel("Source:")); p1.add(t4 = new JTextField(10));
        p1.add(new JLabel("Destination:")); p1.add(t5 = new JTextField(10));
        p1.add(new JLabel("Seat Class:")); p1.add(cb1 = new JComboBox(new String[]{"Economy", "Business", "First Class"}));
        p1.add(new JLabel("Meal:")); JPanel meal = new JPanel(); meal.add(c1 = new JCheckBox("Veg")); meal.add(c2 = new JCheckBox("Non-Veg")); p1.add(meal);
        p1.add(new JLabel("Price:")); p1.add(t6 = new JTextField(10));
        p1.add(new JLabel("Travel Date:")); p1.add(t7 = new JTextField(10));
        p1.add(new JLabel("Passport:")); p1.add(b1 = new JButton("Upload"));
        
        JPanel p2 = new JPanel(new FlowLayout());
        bAdd = new JButton("Add"); bUpdate = new JButton("Update"); bDelete = new JButton("Delete");
        bSearch = new JButton("Search"); bReset = new JButton("Reset"); bExit = new JButton("Exit");
        p2.add(bAdd); p2.add(bUpdate); p2.add(bDelete); p2.add(bSearch); p2.add(bReset); p2.add(bExit);
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(p1, BorderLayout.CENTER); topPanel.add(p2, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        dtm = new DefaultTableModel(new String[]{"Ticket", "Name", "Flight", "Source", "Dest", "Class", "Meal", "Price", "Date", "Passport"}, 0);
        tab = new JTable(dtm); add(new JScrollPane(tab), BorderLayout.CENTER);

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline_db", "root", "");
            Statement s = con.createStatement();
            s.execute("CREATE TABLE IF NOT EXISTS res (tno VARCHAR(50) PRIMARY KEY, name VARCHAR(100), fno VARCHAR(50), src VARCHAR(100), dest VARCHAR(100), cls VARCHAR(50), meal VARCHAR(50), prc DOUBLE, dt VARCHAR(50), pass VARCHAR(255))");
        } catch(Exception e) { System.out.println(e); }
        loadTable();

        b1.addActionListener(e -> {
            JFileChooser jf = new JFileChooser();
            if(jf.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) passPath = jf.getSelectedFile().getAbsolutePath();
        });

        bAdd.addActionListener(e -> {
            if(t1.getText().isEmpty() || t2.getText().isEmpty()) { JOptionPane.showMessageDialog(this, "Fill Ticket No and Name!"); return; }
            try {
                PreparedStatement ps = con.prepareStatement("insert into res values(?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, t1.getText()); ps.setString(2, t2.getText()); ps.setString(3, t3.getText());
                ps.setString(4, t4.getText()); ps.setString(5, t5.getText()); ps.setString(6, cb1.getSelectedItem().toString());
                ps.setString(7, (c1.isSelected() ? "Veg " : "") + (c2.isSelected() ? "Non-Veg" : ""));
                ps.setDouble(8, Double.parseDouble(t6.getText().isEmpty() ? "0" : t6.getText()));
                ps.setString(9, t7.getText()); ps.setString(10, passPath);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Added!"); loadTable(); clear();
            } catch(Exception ex) { JOptionPane.showMessageDialog(this, ex.getMessage()); }
        });

        bUpdate.addActionListener(e -> {
            if(t1.getText().isEmpty()) { JOptionPane.showMessageDialog(this, "Enter Ticket No!"); return; }
            try {
                PreparedStatement ps = con.prepareStatement("update res set name=?, fno=?, src=?, dest=?, cls=?, meal=?, prc=?, dt=?, pass=? where tno=?");
                ps.setString(1, t2.getText()); ps.setString(2, t3.getText()); ps.setString(3, t4.getText());
                ps.setString(4, t5.getText()); ps.setString(5, cb1.getSelectedItem().toString());
                ps.setString(6, (c1.isSelected() ? "Veg " : "") + (c2.isSelected() ? "Non-Veg" : ""));
                ps.setDouble(7, Double.parseDouble(t6.getText().isEmpty() ? "0" : t6.getText()));
                ps.setString(8, t7.getText()); ps.setString(9, passPath); ps.setString(10, t1.getText());
                if(ps.executeUpdate() > 0) { JOptionPane.showMessageDialog(this, "Updated!"); loadTable(); clear(); }
                else JOptionPane.showMessageDialog(this, "Not Found!");
            } catch(Exception ex) { JOptionPane.showMessageDialog(this, ex.getMessage()); }
        });

        bDelete.addActionListener(e -> {
            if(t1.getText().isEmpty()) { JOptionPane.showMessageDialog(this, "Enter Ticket No!"); return; }
            if(JOptionPane.showConfirmDialog(this, "Delete?") == JOptionPane.YES_OPTION) {
                try {
                    PreparedStatement ps = con.prepareStatement("delete from res where tno=?");
                    ps.setString(1, t1.getText());
                    if(ps.executeUpdate() > 0) { JOptionPane.showMessageDialog(this, "Deleted!"); loadTable(); clear(); }
                    else JOptionPane.showMessageDialog(this, "Not Found!");
                } catch(Exception ex) { JOptionPane.showMessageDialog(this, ex.getMessage()); }
            }
        });

        bSearch.addActionListener(e -> {
            if(t1.getText().isEmpty()) { JOptionPane.showMessageDialog(this, "Enter Ticket No!"); return; }
            try {
                PreparedStatement ps = con.prepareStatement("select * from res where tno=?");
                ps.setString(1, t1.getText()); ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                    t2.setText(rs.getString(2)); t3.setText(rs.getString(3)); t4.setText(rs.getString(4));
                    t5.setText(rs.getString(5)); cb1.setSelectedItem(rs.getString(6));
                    String m = rs.getString(7); if(m == null) m = "";
                    c1.setSelected(m.contains("Veg")); c2.setSelected(m.contains("Non-Veg"));
                    t6.setText(String.valueOf(rs.getDouble(8))); t7.setText(rs.getString(9)); passPath = rs.getString(10);
                    JOptionPane.showMessageDialog(this, "Found!");
                } else JOptionPane.showMessageDialog(this, "Not Found!");
            } catch(Exception ex) { JOptionPane.showMessageDialog(this, ex.getMessage()); }
        });

        bReset.addActionListener(e -> clear());
        bExit.addActionListener(e -> System.exit(0));
    }

    void clear() {
        t1.setText(""); t2.setText(""); t3.setText(""); t4.setText(""); t5.setText(""); t6.setText(""); t7.setText("");
        cb1.setSelectedIndex(0); c1.setSelected(false); c2.setSelected(false); passPath = "";
    }

    void loadTable() {
        try {
            dtm.setRowCount(0);
            Statement s = con.createStatement(); ResultSet rs = s.executeQuery("select * from res");
            while(rs.next()) {
                Vector v = new Vector();
                for(int i = 1; i <= 10; i++) v.add(rs.getObject(i));
                dtm.addRow(v);
            }
        } catch(Exception ex) { System.out.println(ex); }
    }

    public static void main(String args[]) {
        new AirlineReservationApp().setVisible(true);
    }
}
