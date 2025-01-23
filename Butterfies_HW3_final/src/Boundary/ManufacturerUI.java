//package Boundary;
//
//import Control.ManufacturerManagement;
//import Entity.Manufacturer;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//
//public class ManufacturerUI {
//    public static void display() {
//        JFrame frame = new JFrame("Manufacturers");
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.setSize(600, 400);
//
//        ManufacturerManagement management = new ManufacturerManagement();
//        ArrayList<Manufacturer> manufacturers = management.getManufacturers();
//
//        String[] columnNames = {"ID", "Name", "Phone Number", "Address", "Email"};
//        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
//            @Override
//            public Class<?> getColumnClass(int columnIndex) {
//                if (columnIndex == 0) {
//                    return Integer.class; // Ensure ID is treated as Integer
//                }
//                return String.class;
//            }
//        };
//
//        for (Manufacturer m : manufacturers) {
//            tableModel.addRow(new Object[]{m.getId(), m.getName(), m.getPhoneNumber(), m.getAddress(), m.getEmail()});
//        }
//
//        JTable table = new JTable(tableModel);
//        JScrollPane scrollPane = new JScrollPane(table);
//        frame.add(scrollPane, BorderLayout.CENTER);
//
//        JPanel buttonPanel = new JPanel();
//        JButton addButton = new JButton("Add");
//        JButton deleteButton = new JButton("Delete");
//        JButton saveButton = new JButton("Save");
//
//        buttonPanel.add(addButton);
//        buttonPanel.add(deleteButton);
//        buttonPanel.add(saveButton);
//        frame.add(buttonPanel, BorderLayout.SOUTH);
//
//        addButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int nextId = management.getNextId();
//                tableModel.addRow(new Object[]{nextId, "", "", "", ""});
//            }
//        });
//
//        deleteButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int selectedRow = table.getSelectedRow();
//                if (selectedRow != -1) {
//                    Integer id = (Integer) tableModel.getValueAt(selectedRow, 0);
//                    if (id != null) {
//                        management.deleteManufacturer(id);
//                    }
//                    tableModel.removeRow(selectedRow);
//                }
//            }
//        });
//
//        saveButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                for (int i = 0; i < tableModel.getRowCount(); i++) {
//                    Integer id = (Integer) tableModel.getValueAt(i, 0);
//                    String name = (String) tableModel.getValueAt(i, 1);
//                    String phoneNumber = (String) tableModel.getValueAt(i, 2);
//                    String address = (String) tableModel.getValueAt(i, 3);
//                    String email = (String) tableModel.getValueAt(i, 4);
//
//                    Entity.Manufacturer manufacturer = new Entity.Manufacturer(id, name, phoneNumber, address, email);
//                    if (management.getManufacturers().stream().noneMatch(m -> m.getId() == id)) {
//                        if (management.addManufacturer(manufacturer)) {
//                            System.out.println("New manufacturer added: " + name);
//                        }
//                    } else {
//                        if (management.updateManufacturer(manufacturer)) {
//                            System.out.println("Manufacturer updated: " + name);
//                        }
//                    }
//                }
//            }
//        });
//
//        frame.setVisible(true);
//        
//        
//        
//    }
//}


package Boundary;

import Control.XMLControl;
import Control.ManufacturerManagement;
import Entity.Manufacturer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;



public class ManufacturerUI {

    public static void display() {
        JFrame frame = new JFrame("Manufacturers");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);

        ManufacturerManagement management = new ManufacturerManagement();
        ArrayList<Manufacturer> manufacturers = management.getManufacturers();

        String[] columnNames = {"ID", "Name", "Phone Number", "Address", "Email"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Integer.class; // Ensure ID is treated as Integer
                }
                return String.class;
            }
        };

        for (Manufacturer m : manufacturers) {
            tableModel.addRow(new Object[]{m.getId(), m.getName(), m.getPhoneNumber(), m.getAddress(), m.getEmail()});
        }

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton saveButton = new JButton("Save");
        JButton importXMLButton = new JButton("Import XML");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(importXMLButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nextId = management.getNextId();
                tableModel.addRow(new Object[]{nextId, "", "", "", ""});
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    Integer id = (Integer) tableModel.getValueAt(selectedRow, 0);
                    if (id != null) {
                        management.deleteManufacturer(id);
                    }
                    tableModel.removeRow(selectedRow);
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    Integer id = (Integer) tableModel.getValueAt(i, 0);
                    String name = (String) tableModel.getValueAt(i, 1);
                    String phoneNumber = (String) tableModel.getValueAt(i, 2);
                    String address = (String) tableModel.getValueAt(i, 3);
                    String email = (String) tableModel.getValueAt(i, 4);

                    Manufacturer manufacturer = new Manufacturer(id, name, phoneNumber, address, email);
                    if (management.getManufacturers().stream().noneMatch(m -> m.getId() == id)) {
                        if (management.addManufacturer(manufacturer)) {
                            System.out.println("New manufacturer added: " + name);
                        }
                    } else {
                        if (management.updateManufacturer(manufacturer)) {
                            System.out.println("Manufacturer updated: " + name);
                        }
                    }
                }
            }
        });

        importXMLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("XML Files", "xml"));

                int returnValue = fileChooser.showOpenDialog(frame);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());

                    // Use XMLControl to import data into the database
                    XMLControl xmlControl = new XMLControl();
                    xmlControl.importManufacturersToAccess(selectedFile.getAbsolutePath());

                    JOptionPane.showMessageDialog(frame, "XML Data Imported Successfully!");
                } else {
                    System.out.println("No file selected");
                }
            }
        });

        frame.setVisible(true);
    }
}
