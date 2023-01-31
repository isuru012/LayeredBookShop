package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.dao.SQLUtil;
import lk.ijse.bookshop.dao.custom.CusOrderDAO;
import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.dto.OrderDetailDTO;
import lk.ijse.bookshop.entity.CusOrder;
import lk.ijse.bookshop.entity.CusOrderDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CusOrderDAOImpl implements CusOrderDAO {
    @Override
    public ArrayList<CusOrder> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean insert(CusOrder customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(CusOrder customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public CusOrder search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    public  String getId() throws SQLException, ClassNotFoundException {
        String sql="SELECT CusOrderId FROM cusorder ORDER BY CusOrderId DESC LIMIT 1";
        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }





    public  int getOrdersAmount() throws SQLException, ClassNotFoundException {
        String sql="SELECT COUNT(CusOrderId) FROM cusorder";
        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return -1;
    }
    public boolean saveO(CusOrder cusOrderDTO) throws SQLException, ClassNotFoundException {
        PreparedStatement statement= DBConnection.getDBConnection().getConnection().prepareStatement("INSERT " +
                "INTO cusorder VALUES(?,?,?,?,?) ");
        statement.setObject(1, cusOrderDTO.getCusOrderId());
        statement.setObject(2, cusOrderDTO.getDate());
        statement.setObject(3, cusOrderDTO.getTime());
        statement.setObject(4, cusOrderDTO.getCusId());
        statement.setObject(5, cusOrderDTO.getEmployeeId());
        return statement.executeUpdate()>0;
    }
}
