package lk.ijse.bookshop.dao.custom;

import lk.ijse.bookshop.dao.CrudDAO;
import lk.ijse.bookshop.entity.CusOrder;

import java.sql.SQLException;

public interface CusOrderDAO extends CrudDAO<CusOrder>,SuperDAO{
    int getOrdersAmount() throws SQLException, ClassNotFoundException;
    boolean saveCusOrder(CusOrder cusOrderDTO) throws SQLException, ClassNotFoundException;
}
