package Control;

import Entity.Wine;
import java.sql.*;
import java.util.ArrayList;
import Entity.Consts;

public class WineManagement {
    public ArrayList<Wine> getWines() {
        ArrayList<Wine> results = new ArrayList<>();

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Wine");
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    results.add(new Wine(
                        rs.getInt("catalogNum"),
                        rs.getString("WineName"),
                        rs.getString("Description"),
                        rs.getInt("ProductionYear"),
                        rs.getDouble("PricePerBottle"),
                        rs.getString("Sweetness"),
                        rs.getInt("ManufacturerID"),
                        rs.getString("ProductImage")
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

    public int getNextCatalogNum() {
        String sql = "SELECT MAX(catalogNum) FROM Wine";
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

    public boolean addWine(Wine wine) {
        String sql = "INSERT INTO Wine (catalogNum, WineName, Description, ProductionYear, PricePerBottle, Sweetness, ManufacturerID, ProductImage) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, wine.getCatalogNum());
                stmt.setString(2, wine.getName());
                stmt.setString(3, wine.getDescription());
                stmt.setInt(4, wine.getProductionYear());
                stmt.setDouble(5, wine.getPricePerBottle());
                stmt.setString(6, wine.getSweetness());
                stmt.setInt(7, wine.getManufacturerID());
                stmt.setString(8, wine.getProductImage());

                // Log values being inserted
                System.out.println("Inserting Wine: " + wine);

                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Wine added successfully.");
                    return true;
                } else {
                    System.err.println("No rows affected during insertion.");
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Database driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error adding wine: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateWine(Wine wine) {
        String sql = "UPDATE Wine SET WineName = ?, Description = ?, ProductionYear = ?, PricePerBottle = ?, Sweetness = ?, ManufacturerID = ?, ProductImage = ? WHERE catalogNum = ?";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, wine.getName());
                stmt.setString(2, wine.getDescription());
                stmt.setInt(3, wine.getProductionYear());
                stmt.setDouble(4, wine.getPricePerBottle());
                stmt.setString(5, wine.getSweetness());
                stmt.setInt(6, wine.getManufacturerID());
                stmt.setString(7, wine.getProductImage());
                stmt.setInt(8, wine.getCatalogNum());

                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Wine updated successfully.");
                    return true;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error updating wine: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteWine(int catalogNum) {
        String sql = "DELETE FROM Wine WHERE catalogNum = ?";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, catalogNum);
                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Wine deleted successfully.");
                    return true;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error deleting wine: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
   
    
    
}
