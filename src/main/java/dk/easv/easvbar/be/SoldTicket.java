package dk.easv.easvbar.be;

public class SoldTicket {
    private String customerName;
    private String customerEmail;
    private String ticketType;
    private String uniqueCode;

    public SoldTicket(String customerName, String customerEmail, String ticketType, String uniqueCode) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.ticketType = ticketType;
        this.uniqueCode = uniqueCode;
    }

    public String getCustomerName() { return customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public String getTicketType() { return ticketType; }
    public String getUniqueCode() { return uniqueCode; }
}
