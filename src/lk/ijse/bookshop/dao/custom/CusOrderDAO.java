package lk.ijse.bookshop.dao.custom;

import lk.ijse.bookshop.dao.CrudDAO;
import lk.ijse.bookshop.entity.CusOrder;

import java.sql.SQLException;

public interface CusOrderDAO extends CrudDAO<CusOrder>{
    int getOrdersAmount() throws SQLException, ClassNotFoundException;
}
