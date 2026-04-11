package dk.easv.easvbar.dal;

import dk.easv.easvbar.be.Event;
import dk.easv.easvbar.be.EventException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EventsDAO {
    private final ConnectionManager cm = new ConnectionManager();

    public List<Event> getAllEvents() throws EventException {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM Events";
        try (Connection con = cm.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                events.add(new Event(
                        rs.getInt("id"), rs.getString("name"),
                        rs.getString("start_time"), rs.getString("end_time"),
                        rs.getString("location"), rs.getString("location_guidance"),
                        rs.getString("notes"), rs.getInt("price")
                ));
            }
        } catch (SQLException e) {
            throw new EventException("Could not get the list of events", e);
        }
        return events;
    }

    public void createEvent(String name, String start, String end, String loc, String guide, String notes, int price) throws EventException {
        String sqlEvent = "INSERT INTO Events (name, start_time, end_time, location, location_guidance, notes, price) VALUES (?,?,?,?,?,?,?)";
        String sqlDefaultTicket = "INSERT INTO TicketTypes (event_id, name, description, price) VALUES (?, ?, ?, ?)";

        try (Connection con = cm.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement psEvent = con.prepareStatement(sqlEvent, Statement.RETURN_GENERATED_KEYS)) {
                psEvent.setString(1, name);
                psEvent.setString(2, start);
                psEvent.setString(3, end);
                psEvent.setString(4, loc);
                psEvent.setString(5, guide);
                psEvent.setString(6, notes);
                psEvent.setInt(7, price);
                psEvent.executeUpdate();

                ResultSet rs = psEvent.getGeneratedKeys();
                if (rs.next()) {
                    int newEventId = rs.getInt(1);
                    try (PreparedStatement psTicket = con.prepareStatement(sqlDefaultTicket)) {
                        psTicket.setInt(1, newEventId);
                        psTicket.setString(2, "Standard");
                        psTicket.setString(3, "Standart ticket");
                        psTicket.setDouble(4, price);
                        psTicket.executeUpdate();
                    }
                }
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new EventException("Could not create event and default ticket", e);
        }
    }

    public void assignCoordinatorToEvent(int userId, int eventId) throws EventException {
        String sql = "INSERT INTO EventCoordinators (user_id, event_id) VALUES (?, ?)";
        try (Connection con = cm.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, eventId);
            ps.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 2627) {
                throw new EventException("This coordinator is already assigned to this event.", e);
            }
        }
    }
}
