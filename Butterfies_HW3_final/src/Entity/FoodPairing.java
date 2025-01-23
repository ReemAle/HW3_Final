package Entity;

public class FoodPairing {
    private int foodRecipeID;
    private String foodName;

    // Constructor
    public FoodPairing(int foodRecipeID, String foodName) {
        this.foodRecipeID = foodRecipeID;
        this.foodName = foodName;
    }

    // Getters and Setters
    public int getFoodRecipeID() {
        return foodRecipeID;
    }

    public void setFoodRecipeID(int foodRecipeID) {
        this.foodRecipeID = foodRecipeID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    // toString Method
    @Override
    public String toString() {
        return "FoodPairing{" +
                "foodRecipeID=" + foodRecipeID +
                ", foodName='" + foodName + '\'' +
                '}';
    }
}
