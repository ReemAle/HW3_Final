//package Boundary;
//
//import Control.WineManagement;
//import Entity.Wine;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.List;
//
//public class WineUI {
//    public static void display() {
//        JFrame frame = new JFrame("Wines");
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.setSize(400, 300);
//
//        WineManagement management = new WineManagement();
//        List<Wine> wines = management.getWines();
//
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//
//        for (Wine w : wines) {
//            panel.add(new JLabel(w.getName() + " - $" + w.getPricePerBottle()));
//        }
//
//        frame.add(new JScrollPane(panel));
//        frame.setVisible(true);
//    }
//}

package Boundary;

import Control.ManufacturerManagement;
import Entity.Wine;
import Control.WineManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WineUI {
    public static void display() {
        JFrame frame = new JFrame("Wines");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 400);

        WineManagement management = new WineManagement();
        ArrayList<Wine> wines = management.getWines();

        String[] columnNames = {"Catalog Number", "Name", "Description", "Production Year", "Price Per Bottle", "Sweetness", "Manufacturer ID", "Product Image"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0 || columnIndex == 3 || columnIndex == 6) {
                    return Integer.class; // Ensure integers are handled correctly
                } else if (columnIndex == 4) {
                    return Double.class; // Ensure price is handled as double
                }
                return String.class;
            }
        };

        for (Wine w : wines) {
            tableModel.addRow(new Object[]{w.getCatalogNum(), w.getName(), w.getDescription(), w.getProductionYear(), w.getPricePerBottle(), w.getSweetness(), w.getManufacturerID(), w.getProductImage()});
        }

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton saveButton = new JButton("Save");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nextCatalogNum = management.getNextCatalogNum();
                tableModel.addRow(new Object[]{nextCatalogNum, "", "", 2023, 0.0, "", 0, ""});
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    Integer catalogNum = (Integer) tableModel.getValueAt(selectedRow, 0);
                    if (catalogNum != null) {
                        management.deleteWine(catalogNum);
                    }
                    tableModel.removeRow(selectedRow);
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    Integer catalogNum = (Integer) tableModel.getValueAt(i, 0);
                    String name = (String) tableModel.getValueAt(i, 1);
                    String description = (String) tableModel.getValueAt(i, 2);
                    Integer productionYear = (Integer) tableModel.getValueAt(i, 3);
                    Double pricePerBottle = (Double) tableModel.getValueAt(i, 4);
                    String sweetness = (String) tableModel.getValueAt(i, 5);
                    Integer manufacturerID = (Integer) tableModel.getValueAt(i, 6);
                    String productImage = (String) tableModel.getValueAt(i, 7);

                    Wine wine = new Wine(catalogNum, name, description, productionYear, pricePerBottle, sweetness, manufacturerID, productImage);
                    if (management.getWines().stream().noneMatch(w -> w.getCatalogNum() == catalogNum)) {
                        if (management.addWine(wine)) {
                            System.out.println("New wine added: " + name);
                        }
                    } else {
                        if (management.updateWine(wine)) {
                            System.out.println("Wine updated: " + name);
                        }
                    }
                }
            }
        });

        frame.setVisible(true);
    }
}
