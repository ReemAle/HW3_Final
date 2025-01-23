package Entity;

public class Wine {
    private int catalogNum;
    private String name;
    private String description;
    private int productionYear;
    private double pricePerBottle;
    private String sweetness;
    private int manufacturerID;
    private String productImage;

    public Wine(int catalogNum, String name, String description, int productionYear, double pricePerBottle, String sweetness, int manufacturerID, String productImage) {
        this.catalogNum = catalogNum;
        this.name = name;
        this.description = description;
        this.productionYear = productionYear;
        this.pricePerBottle = pricePerBottle;
        this.sweetness = sweetness;
        this.manufacturerID = manufacturerID;
        this.productImage = productImage;
    }

    // Getters and setters
    public int getCatalogNum() {
        return catalogNum;
    }

    public void setCatalogNum(int catalogNum) {
        this.catalogNum = catalogNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public double getPricePerBottle() {
        return pricePerBottle;
    }

    public void setPricePerBottle(double pricePerBottle) {
        this.pricePerBottle = pricePerBottle;
    }

    public String getSweetness() {
        return sweetness;
    }

    public void setSweetness(String sweetness) {
        this.sweetness = sweetness;
    }

    public int getManufacturerID() {
        return manufacturerID;
    }

    public void setManufacturerID(int manufacturerID) {
        this.manufacturerID = manufacturerID;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
    @Override
    public String toString() {
        return "Wine{" +
                "catalogNum=" + catalogNum +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", productionYear=" + productionYear +
                ", pricePerBottle=" + pricePerBottle +
                ", sweetness='" + sweetness + '\'' +
                ", manufacturerID=" + manufacturerID +
                ", productImage='" + productImage + '\'' +
                '}';
    }

}
