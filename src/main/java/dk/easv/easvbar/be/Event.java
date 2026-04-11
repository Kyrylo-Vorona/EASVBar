package dk.easv.easvbar.be;

public class Event {
    private int id, price;
    private String name, location, notes, startTime, endTime, locationGuidance;
    private String coordinatorNames;

    public Event(int id, String name, String startTime, String endTime, String location, String locationGuidance, String notes, int price) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.locationGuidance = locationGuidance;
        this.notes = notes;
        this.price = price;
        this.coordinatorNames = "";
    }

    public int getId() {
        return id;
    }
    public String getName() { return name; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
    public String getLocation() { return location; }
    public String getLocationGuidance() { return locationGuidance; }
    public String getNotes() { return notes; }
    public int getPrice() { return price; }
    public String getCoordinatorNames() { return coordinatorNames; }

    public void setName(String name) { this.name = name; }
    public void setStartTime(String startTime) { this.startTime = startTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
    public void setLocation(String location) { this.location =  location; }
    public void setLocationGuidance(String locationGuidance) { this.locationGuidance = locationGuidance; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setPrice(int price) { this.price = price; }
    public void setCoordinatorNames(String coordinatorNames) { this.coordinatorNames = coordinatorNames; }
}