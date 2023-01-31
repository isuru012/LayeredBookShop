package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.dao.custom.CusReloadDetailsDAO;
import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.dto.CusReloadDetailsDTO;
import lk.ijse.bookshop.dto.OrderDetailDTO;
import lk.ijse.bookshop.entity.CusOrder;
import lk.ijse.bookshop.entity.CusOrderDetails;
import lk.ijse.bookshop.entity.CusReloadDetails;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CusReloadDetailsDAOImpl implements CusReloadDetailsDAO {
    public boolean saveReloadDetails(ArrayList<CusReloadDetails> cusReloadDetailsDTOS) throws SQLException, ClassNotFoundException {
        for (CusReloadDetails ord : cusReloadDetailsDTOS) {
            if (!addReloadDetail(ord)) {
                return false;
            }
        }
        return true;
    }

    private boolean addReloadDetail(CusReloadDetails ord) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DBConnection.getDBConnection().getConnection().prepareStatement("INSERT INTO cusreloaddetails values(?,?,?)");

        statement.setObject(1, ord.getCusReloadId());
        statement.setObject(2, ord.getReloadId());
        statement.setObject(3, ord.getTotalPrice());


        return statement.executeUpdate() > 0;
    }

    @Override
    public ArrayList<CusReloadDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean insert(CusReloadDetails customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(CusReloadDetails customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public CusReloadDetails search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveO(CusOrder cusOrder) throws SQLException, ClassNotFoundException {
        return false;
    }


}
