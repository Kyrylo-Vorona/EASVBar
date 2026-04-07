package dk.easv.easvbar.bll;

import dk.easv.easvbar.be.EventException;
import dk.easv.easvbar.be.User;
import dk.easv.easvbar.dal.DALManager;

import java.sql.SQLException;
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

    public List<User> getAllUsers() throws EventException {
        return DALManager.getInstance().getUsersDAO().getAllUsers();
    }
}
