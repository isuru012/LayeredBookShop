package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.dao.SQLUtil;
import lk.ijse.bookshop.dao.custom.ExpenditureDAO;
import lk.ijse.bookshop.dto.ExpenditureDTO;
import lk.ijse.bookshop.dto.OrderDetailDTO;
import lk.ijse.bookshop.entity.Expenditure;
import lk.ijse.bookshop.view.tm.ExpenditureTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ExpenditureDAOImpl implements ExpenditureDAO {
    public ArrayList getAll() throws SQLException, ClassNotFoundException {
        String sql = "select * from expenditure";
        ResultSet resultSet = SQLUtil.execute(sql);
        ArrayList arrayList = new ArrayList();
        while (resultSet.next()) {
            arrayList.add(new ExpenditureTm(resultSet.getString(1), resultSet.getString(2), resultSet.getTime(5),
                    resultSet.getDate(4), resultSet.getDouble(3)));
        }
        return arrayList;

    }

    public String getId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT ExpenditureId FROM expenditure ORDER BY ExpenditureId DESC LIMIT 1";
        ResultSet resultSet = SQLUtil.execute(sql);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean saveO(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean saveD(OrderDetailDTO detail, String orderId) throws SQLException, ClassNotFoundException {
        return false;
    }

    public String getUsername() throws SQLException, ClassNotFoundException {
        String sql = "SELECT Username FROM  user WHERE Role='Admin' ";
        ResultSet resultSet = SQLUtil.execute(sql);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public boolean insert(Expenditure expenditure) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO expenditure VALUES (?,?,?,?,?,?)";
        return SQLUtil.execute(sql, expenditure.getExpenditureId(), expenditure.getDescription(),
                expenditure.getAmount(), expenditure.getDate(),
                expenditure.getTime(), expenditure.getUserName());
    }


    public boolean update(Expenditure expenditure) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE  expenditure SET Description=?,Amount=? WHERE ExpenditureId=?";
        return SQLUtil.execute(sql, expenditure.getDescription(), expenditure.getAmount(),
                expenditure.getExpenditureId());
    }

    public boolean delete(String expenditureId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM expenditure WHERE ExpenditureId=?";
        return SQLUtil.execute(sql, expenditureId);
    }

    @Override
    public Expenditure search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }
}
