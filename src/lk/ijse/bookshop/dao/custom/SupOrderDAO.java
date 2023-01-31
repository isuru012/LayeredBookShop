package lk.ijse.bookshop.dao.custom;

import lk.ijse.bookshop.dao.CrudDAO;
import lk.ijse.bookshop.entity.Payment;
import lk.ijse.bookshop.entity.SupOrder;

import java.sql.SQLException;

public interface SupOrderDAO extends CrudDAO<SupOrder>{
    boolean saveOrder(SupOrder supOrder) throws SQLException, ClassNotFoundException;
}
