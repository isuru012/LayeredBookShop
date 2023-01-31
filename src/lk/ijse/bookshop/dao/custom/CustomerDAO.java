package lk.ijse.bookshop.dao.custom;

import lk.ijse.bookshop.dao.CrudDAO;
import lk.ijse.bookshop.entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer>{
    int getCustomersAmount() throws SQLException, ClassNotFoundException;
}
