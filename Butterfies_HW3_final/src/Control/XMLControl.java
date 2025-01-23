package Control;

import Entity.Consts;
import Entity.Manufacturer;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class XMLControl {

    public ArrayList<Manufacturer> getManufacturersFromXML(String filePath) {
        ArrayList<Manufacturer> manufacturers = new ArrayList<>();
        try {
            // Parse the XML file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filePath));
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("Manufacturer");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    int id = Integer.parseInt(element.getElementsByTagName("ID").item(0).getTextContent());
                    String name = element.getElementsByTagName("Name").item(0).getTextContent();
                    String phoneNumber = element.getElementsByTagName("PhoneNumber").item(0).getTextContent();
                    String address = element.getElementsByTagName("Address").item(0).getTextContent();
                    String email = element.getElementsByTagName("Email").item(0).getTextContent();

                    manufacturers.add(new Manufacturer(id, name, phoneNumber, address, email));
                }
            }
        } catch (Exception e) {
            System.err.println("Error parsing XML: " + e.getMessage());
            e.printStackTrace();
        }
        return manufacturers;
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
                    System.out.println("Manufacturer added successfully: " + manufacturer.getName());
                    return true;
                } else {
                    System.err.println("No rows affected while adding manufacturer: " + manufacturer.getName());
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error adding manufacturer: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public void importManufacturersToAccess(String filePath) {
        ArrayList<Manufacturer> manufacturers = getManufacturersFromXML(filePath);
        for (Manufacturer manufacturer : manufacturers) {
            // Check if the manufacturer already exists
            if (!manufacturerExists(manufacturer.getId())) {
                addManufacturer(manufacturer);
            } else {
                System.out.println("Manufacturer already exists with ID: " + manufacturer.getId());
            }
        }
    }

    private boolean manufacturerExists(int id) {
        String sql = "SELECT COUNT(*) FROM Manufacturer WHERE ID = ?";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        return true;
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error checking if manufacturer exists: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
