package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.dao.SQLUtil;
import lk.ijse.bookshop.dao.custom.SupOrderDAO;
import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.dto.OrderDetailDTO;
import lk.ijse.bookshop.entity.CusOrder;
import lk.ijse.bookshop.entity.CusOrderDetails;
import lk.ijse.bookshop.entity.SupOrder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SupOrderDAOImpl implements SupOrderDAO {
    public  String getId() throws SQLException, ClassNotFoundException {
        String sql="SELECT SupOrderId FROM suporder ORDER BY SupOrderId DESC LIMIT 1";
        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public ArrayList<SupOrder> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean insert(SupOrder customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(SupOrder customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public SupOrder search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }


    @Override
    public boolean saveO(CusOrder cusOrder) throws SQLException, ClassNotFoundException {
        return false;
    }


    public boolean saveOrder(SupOrder supOrder) throws SQLException, ClassNotFoundException {

        PreparedStatement statement=DBConnection.getDBConnection().getConnection().prepareStatement("INSERT " +
                "INTO suporder VALUES(?,?,?,?,?) ");
        statement.setObject(1, supOrder.getSupOrderId());
        statement.setObject(2, supOrder.getDate());
        statement.setObject(3, supOrder.getTime());
        statement.setObject(4, supOrder.getSupplierId());
        statement.setObject(5, supOrder.getUserName());

        return statement.executeUpdate()>0;
    }
}
