package Control;

import Entity.Manufacturer;
import java.sql.*;
import java.util.ArrayList;
import Entity.Consts;

public class ManufacturerManagement {
    public ArrayList<Manufacturer> getManufacturers() {
        ArrayList<Manufacturer> results = new ArrayList<>();

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Manufacturer");
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    results.add(new Manufacturer(
                        rs.getInt("ID"),
                        rs.getString("ManufacturerName"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Address"),
                        rs.getString("Email")
                    ));
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Database driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection or query error.");
            e.printStackTrace();
        }

        return results;
    }

    public int getNextId() {
        String sql = "SELECT MAX(ID) FROM Manufacturer";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) + 1;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 1; // Default to 1 if table is empty
    }

    public boolean addManufacturer(Manufacturer manufacturer) {
        String sql = "INSERT INTO Manufacturer (ID, ManufacturerName, PhoneNumber, Address, Email) VALUES (?, ?, ?, ?, ?)";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, manufacturer.getId());
                stmt.setString(2, manufacturer.getName());
                stmt.setString(3, manufacturer.getPhoneNumber());
                stmt.setString(4, manufacturer.getAddress());
                stmt.setString(5, manufacturer.getEmail());
                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Manufacturer added successfully.");
                    return true;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error adding manufacturer: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateManufacturer(Manufacturer manufacturer) {
        String sql = "UPDATE Manufacturer SET ManufacturerName = ?, PhoneNumber = ?, Address = ?, Email = ? WHERE ID = ?";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, manufacturer.getName());
                stmt.setString(2, manufacturer.getPhoneNumber());
                stmt.setString(3, manufacturer.getAddress());
                stmt.setString(4, manufacturer.getEmail());
                stmt.setInt(5, manufacturer.getId());
                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Manufacturer updated successfully.");
                    return true;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error updating manufacturer: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteManufacturer(int id) {
        String sql = "DELETE FROM Manufacturer WHERE ID = ?";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Manufacturer deleted successfully.");
                    return true;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error deleting manufacturer: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}