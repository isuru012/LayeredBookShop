package lk.ijse.bookshop.dao.custom;

import lk.ijse.bookshop.dao.CrudDAO;
import lk.ijse.bookshop.entity.Item;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item>,SuperDAO {
    int getProductsAmount() throws SQLException, ClassNotFoundException;
}
