package Entity;

import java.net.URLDecoder;

public class Consts {
    
    private Consts() {
        throw new AssertionError();
    }

    // Connection string to the database
    public static final String CONN_STR = "jdbc:ucanaccess://" + getDBPath() + ";COLUMNORDER=DISPLAY";

    private static String getDBPath() {
        try {
            String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            String decoded = URLDecoder.decode(path, "UTF-8");
            if (decoded.contains(".jar")) {
                decoded = decoded.substring(0, decoded.lastIndexOf('/'));
                System.out.println(decoded);
                return decoded + "/database/Techen2.accdb";
            } else {
                decoded = decoded.substring(0, decoded.lastIndexOf("/bin"));
                return decoded + "/src/Techen2.accdb";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // SQL queries for Manufacturer table
    public static final String SQL_SHOW_MANUFACTURERS = "SELECT * FROM Manufacturer";
    public static final String SQL_ADD_MANUFACTURER = "INSERT INTO Manufacturer (ID, ManufacturerName, PhoneNumber, Address, Email) VALUES (?, ?, ?, ?, ?)";
    public static final String SQL_UPDATE_MANUFACTURER = "UPDATE Manufacturer SET ManufacturerName = ?, PhoneNumber = ?, Address = ?, Email = ? WHERE ID = ?";
    public static final String SQL_DELETE_MANUFACTURER = "DELETE FROM Manufacturer WHERE ID = ?";

    // SQL queries for Wine table
    public static final String SQL_SHOW_WINES = "SELECT * FROM Wine";
    public static final String SQL_ADD_WINE = "INSERT INTO Wine (catalogNum, WineName, Description, ProductionYear, PricePerBottle, Sweetness, Manufacturer, ProductImage) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_UPDATE_WINE = "UPDATE Wine SET WineName = ?, Description = ?, ProductionYear = ?, PricePerBottle = ?, Sweetness = ?, Manufacturer = ?, ProductImage = ? WHERE catalogNum = ?";
    public static final String SQL_DELETE_WINE = "DELETE FROM Wine WHERE catalogNum = ?";

    // SQL queries for WineType table
    public static final String SQL_SHOW_WINE_TYPE = "SELECT * FROM WineType";
    public static final String SQL_ADD_WINE_TYPE = "INSERT INTO WineType (SerialNum, CatalogNum, WineTypeName) VALUES (?, ?, ?)";
    public static final String SQL_UPDATE_WINE_TYPE = "UPDATE WineType SET CatalogNum = ?, WineTypeName = ? WHERE SerialNum = ?";
    public static final String SQL_DELETE_WINE_TYPE = "DELETE FROM WineType WHERE SerialNum = ?";

    // SQL queries for Occasion table
    public static final String SQL_SHOW_OCCASION = "SELECT * FROM Occasion";
    public static final String SQL_ADD_OCCASION = "INSERT INTO Occasion (OccasionID, Description, Location, Season) VALUES (?, ?, ?, ?)";
    public static final String SQL_UPDATE_OCCASION = "UPDATE Occasion SET Description = ?, Location = ?, Season = ? WHERE OccasionID = ?";
    public static final String SQL_DELETE_OCCASION = "DELETE FROM Occasion WHERE OccasionID = ?";

    // SQL queries for FoodPairing table
    public static final String SQL_SHOW_FOOD_PAIRING = "SELECT * FROM FoodPairing";
    public static final String SQL_ADD_FOOD_PAIRING = "INSERT INTO FoodPairing (FoodRecipeID, FoodName) VALUES (?, ?)";
    public static final String SQL_UPDATE_FOOD_PAIRING = "UPDATE FoodPairing SET FoodName = ? WHERE FoodRecipeID = ?";
    public static final String SQL_DELETE_FOOD_PAIRING = "DELETE FROM FoodPairing WHERE FoodRecipeID = ?";

    // SQL queries for Food_Type table
    public static final String SQL_SHOW_FOOD_TYPE = "SELECT * FROM Food_Type";
    public static final String SQL_ADD_FOOD_TYPE = "INSERT INTO Food_Type (FoodID, TypeID) VALUES (?, ?)";
    public static final String SQL_DELETE_FOOD_TYPE = "DELETE FROM Food_Type WHERE FoodID = ? AND TypeID = ?";

    // SQL queries for Occasion_Type table
    public static final String SQL_SHOW_OCCASION_TYPE = "SELECT * FROM Occasion_Type";
    public static final String SQL_ADD_OCCASION_TYPE = "INSERT INTO Occasion_Type (TypeID, OccasionID) VALUES (?, ?)";
    public static final String SQL_DELETE_OCCASION_TYPE = "DELETE FROM Occasion_Type WHERE TypeID = ? AND OccasionID = ?";
}
