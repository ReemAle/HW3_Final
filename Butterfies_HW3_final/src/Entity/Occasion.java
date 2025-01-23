package Entity;

public class Occasion {
    private int occasionID;
    private String description;
    private String location;
    private String season;

    public Occasion(int occasionID, String description, String location, String season) {
        this.occasionID = occasionID;
        this.description = description;
        this.location = location;
        this.season = season;
    }

    public int getOccasionID() {
        return occasionID;
    }

    public void setOccasionID(int occasionID) {
        this.occasionID = occasionID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return "Occasion{" +
                "occasionID=" + occasionID +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", season='" + season + '\'' +
                '}';
    }
}
