package Entity;

public class WineType {
    private int serialNum;
    private int catalogNum;
    private String wineTypeName;

    public WineType(int serialNum, int catalogNum, String wineTypeName) {
        this.serialNum = serialNum;
        this.catalogNum = catalogNum;
        this.wineTypeName = wineTypeName;
    }

    public int getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(int serialNum) {
        this.serialNum = serialNum;
    }

    public int getCatalogNum() {
        return catalogNum;
    }

    public void setCatalogNum(int catalogNum) {
        this.catalogNum = catalogNum;
    }

    public String getWineTypeName() {
        return wineTypeName;
    }

    public void setWineTypeName(String wineTypeName) {
        this.wineTypeName = wineTypeName;
    }

    @Override
    public String toString() {
        return "WineType{" +
                "serialNum=" + serialNum +
                ", catalogNum=" + catalogNum +
                ", wineTypeName='" + wineTypeName + '\'' +
                '}';
    }

}
