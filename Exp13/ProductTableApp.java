package Exp13;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class ProductTableApp {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Product Inventory System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);

            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setLayout(new GridBagLayout());
            frame.setContentPane(layeredPane);

            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new BorderLayout(10, 10));
            bottomPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

            JTextField filterField = new JTextField();
            filterField.setFont(new Font("Serif", Font.BOLD, 18));
            filterField.setBorder(BorderFactory.createTitledBorder(
                    new LineBorder(new Color(0, 102, 204), 2, true),
                    "Filter by Product Name",
                    TitledBorder.LEFT,
                    TitledBorder.TOP,
                    new Font("SansSerif", Font.BOLD, 12),
                    Color.DARK_GRAY));
            bottomPanel.add(filterField, BorderLayout.NORTH);

            String[] columns = { "ID", "Name", "Category", "Price", "In Stock" };
            Object[][] data = {
                    { "P001", "Wireless Mouse", "Electronics", "$25.00", "Yes" },
                    { "P002", "Ergonomic Chair", "Furniture", "$199.99", "No" },
                    { "P003", "Mechanical Keyboard", "Electronics", "$85.50", "Yes" },
                    { "P004", "Water Bottle", "Accessories", "$15.00", "Yes" },
                    { "P005", "Noise Cancelling Headphones", "Audio", "$250.00", "No" }
            };

            DefaultTableModel model = new DefaultTableModel(data, columns) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            JTable table = new JTable(model) {
                @Override
                public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                    Component c = super.prepareRenderer(renderer, row, column);
                    if (!isRowSelected(row)) {
                        c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(225, 235, 245));
                    }
                    return c;
                }
            };

            table.setFont(new Font("Monospaced", Font.PLAIN, 15));
            table.setRowHeight(35);
            table.setBorder(new LineBorder(Color.GRAY, 1));

            JTableHeader header = table.getTableHeader();
            header.setFont(new Font("SansSerif", Font.BOLD, 14));
            header.setBackground(new Color(200, 200, 200));

            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            table.setRowSorter(sorter);

            filterField.getDocument().addDocumentListener(new DocumentListener() {
                public void insertUpdate(DocumentEvent e) {
                    filter();
                }

                public void removeUpdate(DocumentEvent e) {
                    filter();
                }

                public void changedUpdate(DocumentEvent e) {
                    filter();
                }

                private void filter() {
                    String text = filterField.getText();
                    if (text.trim().isEmpty()) {
                        sorter.setRowFilter(null);
                    } else {
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 1));
                    }
                }
            });

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBorder(new CompoundBorder(
                    new LineBorder(Color.BLACK, 2),
                    new EmptyBorder(5, 5, 5, 5)));
            bottomPanel.add(scrollPane, BorderLayout.CENTER);

            BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = img.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(new Color(255, 69, 0, 200));
            g2d.fillOval(10, 10, 80, 80);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Impact", Font.PLAIN, 18));
            g2d.drawString("SALE", 32, 55);
            g2d.dispose();

            JLabel imageLabel = new JLabel(new ImageIcon(img));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            layeredPane.add(bottomPanel, gbc, Integer.valueOf(1));

            GridBagConstraints gbcImage = new GridBagConstraints();
            gbcImage.gridx = 0;
            gbcImage.gridy = 0;
            gbcImage.weightx = 1.0;
            gbcImage.weighty = 1.0;
            gbcImage.anchor = GridBagConstraints.NORTHEAST;
            gbcImage.insets = new Insets(20, 0, 0, 35);
            layeredPane.add(imageLabel, gbcImage, Integer.valueOf(0));

            frame.setVisible(true);
        });
    }
}