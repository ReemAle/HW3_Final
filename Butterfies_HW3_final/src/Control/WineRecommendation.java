package Control;

import Entity.Wine;
import Entity.Consts;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WineRecommendation {

    public List<Wine> getRecommendedWines(String wineType, List<String> foodPairings, List<String> occasions) {
        List<Wine> recommendedWines = new ArrayList<>();
        StringBuilder query = new StringBuilder(
                "SELECT DISTINCT w.* " +
                "FROM Wine w " +
                "LEFT JOIN WineType wt ON w.catalogNum = wt.CatalogNum " +
                "LEFT JOIN Food_Type ft ON wt.SerialNum = ft.TypeID " +
                "LEFT JOIN FoodPairing fp ON ft.FoodID = fp.FoodRecipeID " +
                "LEFT JOIN Occasion_Type ot ON wt.SerialNum = ot.TypeID " +
                "LEFT JOIN Occasion o ON ot.OccasionID = o.OccasionID " +
                "WHERE 1=1 "
        );

        if (wineType != null) {
            query.append("AND wt.WineTypeName = ? ");
        }
        if (foodPairings != null && !foodPairings.isEmpty()) {
            query.append("AND fp.FoodName IN (");
            query.append("?, ".repeat(foodPairings.size()));
            query.setLength(query.length() - 2); // Remove the trailing comma
            query.append(") ");
        }
        if (occasions != null && !occasions.isEmpty()) {
            query.append("AND o.Description IN (");
            query.append("?, ".repeat(occasions.size()));
            query.setLength(query.length() - 2); // Remove the trailing comma
            query.append(") ");
        }

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(query.toString())) {

                int parameterIndex = 1;

                if (wineType != null) {
                    stmt.setString(parameterIndex++, wineType);
                }
                if (foodPairings != null && !foodPairings.isEmpty()) {
                    for (String food : foodPairings) {
                        stmt.setString(parameterIndex++, food);
                    }
                }
                if (occasions != null && !occasions.isEmpty()) {
                    for (String occasion : occasions) {
                        stmt.setString(parameterIndex++, occasion);
                    }
                }

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    recommendedWines.add(new Wine(
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

        return recommendedWines;
    }
}
