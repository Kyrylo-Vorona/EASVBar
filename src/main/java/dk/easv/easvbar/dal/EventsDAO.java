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
                        rs.getString("notes"), rs.getInt("total_tickets")
                ));
            }
        } catch (SQLException e) {
            throw new EventException("Could not get the list of events", e);
        }
        return events;
    }

    public void createEvent(String name, String start, String end, String loc, String guide, String notes, int tix) throws EventException {
        String sql = "INSERT INTO Events (name, start_time, end_time, location, location_guidance, notes, total_tickets) VALUES (?,?,?,?,?,?,?)";
        try (Connection con = cm.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, start);
            ps.setString(3, end);
            ps.setString(4, loc);
            ps.setString(5, guide);
            ps.setString(6, notes);
            ps.setInt(7, tix);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EventException("Could not create an event", e);
        }
    }
}
