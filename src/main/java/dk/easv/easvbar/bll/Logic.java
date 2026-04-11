package dk.easv.easvbar.bll;

import dk.easv.easvbar.be.Event;
import dk.easv.easvbar.be.EventException;
import dk.easv.easvbar.be.User;
import dk.easv.easvbar.dal.DALManager;

import java.util.List;

public class Logic {
    private static Logic instance;
    private Logic() {}

    public static Logic getInstance() {
        if (instance == null) {
            instance = new Logic();
        }
        return instance;
    }

    public User login(String username, String password) throws EventException {
        if (username.isEmpty() || password.isEmpty()) {
            return null;
        }
        return DALManager.getInstance().getUsersDAO().login(username, password);
    }

    public void addUser(String username, String password, String email, String role) throws EventException {
        if (username.isEmpty() || password.isEmpty() || email.isEmpty() || role.isEmpty()) {
            return;
        }
        DALManager.getInstance().getUsersDAO().addUser(username, password, email, role);
    }

    public void editUser(User user) throws EventException {
        DALManager.getInstance().getUsersDAO().editUser(user);
    }

    public void deleteUser(User user) throws EventException {
        DALManager.getInstance().getUsersDAO().deleteUser(user);
    }

    public List<User> getAllUsers() throws EventException {
        return DALManager.getInstance().getUsersDAO().getAllUsers();
    }

    public void createEvent(String name, String start, String end, String loc, String guide, String notes, int price) throws EventException {
        DALManager.getInstance().getEventsDAO().createEvent(name, start,  end, loc, guide, notes, price);
    }

    public void editEvent(Event event) throws EventException {
        DALManager.getInstance().getEventsDAO().editEvent(event);
    }

    public void deleteEvent(Event event) throws EventException {
        DALManager.getInstance().getEventsDAO().deleteEvent(event);
    }

    public void assignCoordinatorToEvent(int userId, int eventId) throws EventException {
        DALManager.getInstance().getEventsDAO().assignCoordinatorToEvent(userId, eventId);
    }

    public List<Event> getAllEvents() throws EventException {
        return DALManager.getInstance().getEventsDAO().getAllEvents();
    }

    public List<User> getAllCoordinators() throws EventException {
        return DALManager.getInstance().getUsersDAO().getAllCoordinators();
    }

    public String getCoordinatorsForEvent(int eventId) throws EventException {
        return DALManager.getInstance().getUsersDAO().getCoordinatorsForEvent(eventId);
    }
}
