package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.dao.SQLUtil;
import lk.ijse.bookshop.dao.custom.CusReloadDAO;
import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.dto.OrderDetailDTO;
import lk.ijse.bookshop.entity.CusReload;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CusReloadDAOImpl implements CusReloadDAO {
    @Override
    public ArrayList<CusReload> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean insert(CusReload customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(CusReload customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public CusReload search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    public  String getId() throws SQLException, ClassNotFoundException {
        String sql="SELECT CusReloadId FROM cusreload ORDER BY CusReloadId DESC LIMIT 1";
        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
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
    public boolean saveReload(CusReload cusReloadDTO) throws SQLException, ClassNotFoundException {
        PreparedStatement statement= DBConnection.getDBConnection().
                getConnection().prepareStatement("INSERT " +
                "INTO cusreload VALUES(?,?,?,?,?) ");
        statement.setObject(1, cusReloadDTO.getCusReloadId());
        statement.setObject(2, cusReloadDTO.getDate());
        statement.setObject(3, cusReloadDTO.getTime());
        statement.setObject(4, cusReloadDTO.getCusId());
        statement.setObject(5, cusReloadDTO.getEmployeeId());
        return statement.executeUpdate()>0;
    }
}
