package lk.ijse.bookshop.dao.custom;

import lk.ijse.bookshop.dao.CrudDAO;
import lk.ijse.bookshop.entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User>,SuperDAO {
    String getUserName() throws SQLException, ClassNotFoundException;
}
