package dk.easv.easvbar.dal;

public class DALManager {
    private UsersDAO usersDAO;
    private EventsDAO eventsDAO;
    private TicketsDAO ticketsDAO;
    private static DALManager instance;

    public static DALManager getInstance() {
        if (instance == null) {
            instance = new DALManager();
        }
        return instance;
    }

    private final ConnectionManager cm;

    private DALManager() {
        cm = new ConnectionManager();
    }

    public UsersDAO getUsersDAO() {
        if (usersDAO == null) usersDAO = new UsersDAO();
        return usersDAO;
    }

    public EventsDAO getEventsDAO() {
        if (eventsDAO ==  null) eventsDAO = new EventsDAO();
        return eventsDAO;
    }

    public TicketsDAO getTicketsDAO() {
        if (ticketsDAO ==  null) ticketsDAO = new TicketsDAO();
        return ticketsDAO;
    }
}
