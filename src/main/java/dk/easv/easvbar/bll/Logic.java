package dk.easv.easvbar.bll;

import dk.easv.easvbar.be.User;
import dk.easv.easvbar.dal.DALManager;

import java.sql.SQLException;

public class Logic {
    private static Logic instance;
    private Logic() {}

    public static Logic getInstance() {
        if (instance == null) {
            instance = new Logic();
        }
        return instance;
    }

    public User login(String username, String password) throws SQLException {
        if (username.isEmpty() || password.isEmpty()) {
            return null;
        }

        return DALManager.getInstance().getUsersDAO().login(username, password);
    }
}
