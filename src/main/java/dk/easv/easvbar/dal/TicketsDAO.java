package dk.easv.easvbar.dal;

import dk.easv.easvbar.be.EventException;
import dk.easv.easvbar.be.Ticket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketsDAO {
    private final ConnectionManager cm = new ConnectionManager();

    public List<Ticket> getTicketsForEvent(int eventId) throws EventException {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM TicketTypes WHERE event_id = ?";
        try (Connection con = cm.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, eventId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tickets.add(new Ticket(
                        rs.getInt("id"),
                        rs.getInt("event_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            throw new EventException("Could not get the list of tickets for selected event", e);
        }
        return tickets;
    }

    public void createTicketType(int eventId, String name, String desc, double price) throws EventException {
        String sql = "INSERT INTO TicketTypes (event_id, name, description, price) VALUES (?, ?, ?, ?)";
        try (Connection con = cm.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, eventId);
            ps.setString(2, name);
            ps.setString(3, desc);
            ps.setDouble(4, price);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new EventException("Could not create ticket type", e);
        }
    }

    public void editTicketType(Ticket ticket) throws EventException {
        String sql = "UPDATE TicketTypes SET name = ?, description = ?, price = ? WHERE id = ?";

        try (Connection con = cm.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ticket.getName());
            ps.setString(2, ticket.getDescription());
            ps.setDouble(3, ticket.getPrice());
            ps.setInt(4, ticket.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new EventException("Could not edit ticket type: ", e);
        }
    }

    public void deleteTicketType(int id) throws EventException {
        String sql = "DELETE FROM TicketTypes WHERE id = ?";
        try (Connection con = cm.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new EventException("Could not delete selected event", e);
        }
    }
}
