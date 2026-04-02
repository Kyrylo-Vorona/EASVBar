package dk.easv.easvbar.dal;

public class DALManager {
    private UsersDAO usersDAO;
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
}
