package dk.easv.easvbar.be;

public class Event {
    private int id, price;
    private String name, location, notes, startTime, endTime, locationGuidance;

    public Event(int id, String name, String startTime, String endTime, String location, String locationGuidance, String notes, int price) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.locationGuidance = locationGuidance;
        this.notes = notes;
        this.price = price;
    }

    public String getName() { return name; }
    public String getStartTime() { return startTime; }
    public String getLocation() { return location; }
    public String getNotes() { return notes; }
    public int getPrice() { return price; }

}