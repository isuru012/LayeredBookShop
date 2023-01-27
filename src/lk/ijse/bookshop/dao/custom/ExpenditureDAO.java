package lk.ijse.bookshop.dao.custom;

import lk.ijse.bookshop.dao.CrudDAO;
import lk.ijse.bookshop.entity.Expenditure;

import java.sql.SQLException;

public interface ExpenditureDAO extends CrudDAO<Expenditure>,SuperDAO {
    boolean updatePayment(String expenditureId) throws SQLException,
            ClassNotFoundException;
}
