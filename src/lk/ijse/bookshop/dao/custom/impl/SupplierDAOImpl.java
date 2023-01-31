package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.dao.SQLUtil;
import lk.ijse.bookshop.dao.custom.SupplierDAO;
import lk.ijse.bookshop.dto.OrderDetailDTO;
import lk.ijse.bookshop.dto.SupplierDTO;
import lk.ijse.bookshop.entity.CusOrder;
import lk.ijse.bookshop.entity.CusOrderDetails;
import lk.ijse.bookshop.entity.Supplier;
import lk.ijse.bookshop.view.tm.SupplierDetailTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {


    public ArrayList loadAllSupplierNames() throws SQLException, ClassNotFoundException {
        String sql="SELECT Name FROM supplier";
        ResultSet resultSet= SQLUtil.execute(sql);
        ArrayList <String> arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }
    public Supplier search(String text) throws SQLException, ClassNotFoundException {
        String searchText="%"+text+"%";
        String sql="SELECT * FROM supplier WHERE Name LIKE ?";
        ResultSet resultSet= SQLUtil.execute(sql,searchText);
        if (resultSet.next()){
            return new Supplier(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5) );
        }
        return null;
    }

    public ArrayList getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM supplier";
        ResultSet resultSet = SQLUtil.execute(sql);
        ArrayList arrayList = new ArrayList();
        while (resultSet.next()) {
            String sql1 = "SELECT COUNT(*)AS number FROM suporder  WHERE SupplierId=?  GROUP BY SupplierId";
            ResultSet resultSet1 = SQLUtil.execute(sql1, resultSet.getString(1));
            if (resultSet1.next()) {
                arrayList.add(new SupplierDetailTm(resultSet.getString(2), resultSet.getInt(4),
                        resultSet.getString(3), resultSet1.getInt(1)));
            } else {
                arrayList.add(new SupplierDetailTm(resultSet.getString(2), resultSet.getInt(4),
                        resultSet.getString(3), 0));
            }

        }
        return arrayList;
    }

    public String getId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT SupplierId FROM supplier ORDER BY SupplierId DESC LIMIT 1";
        ResultSet resultSet = SQLUtil.execute(sql);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;

    }

    @Override
    public boolean saveO(CusOrder cusOrder) throws SQLException, ClassNotFoundException {
        return false;
    }



    public boolean insert(Supplier supplier) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO supplier VALUES (?,?,?,?,?)";
        return SQLUtil.execute(sql, supplier.getSupplierId(), supplier.getName(), supplier.getLocation(),
                supplier.getPhoneNumber(), supplier.getUserName());

    }

    public boolean delete(String supId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM supplier WHERE SupplierId=? ";
        return SQLUtil.execute(sql, supId);
    }

    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE supplier SET Name=?,PhoneNumber=?,Location=? WHERE SupplierId=? ";
        return SQLUtil.execute(sql, supplier.getName(),supplier.getPhoneNumber(),
                supplier.getLocation(),supplier.getSupplierId());
    }

    public String getSupplierIdFromNumber(String valueOf) throws SQLException, ClassNotFoundException {
        String sql = "SELECT SupplierId FROM supplier WHERE PhoneNumber=?";
        ResultSet resultSet = SQLUtil.execute(sql, valueOf);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }


}
