package dk.easv.easvbar.be;

public class Ticket {
    private int id;
    private int event_id;
    private String name;
    private String description;
    private double price;

    public Ticket(int id, int event_id, String name, String description, double price) {
        this.id = id;
        this.event_id = event_id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() { return id; }
    public int getEventId() { return event_id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }

    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { this.price = price; }
}