package lk.ijse.bookshop.model;

import lk.ijse.bookshop.dto.SupplierDTO;
import lk.ijse.bookshop.dao.SQLUtil;
import lk.ijse.bookshop.view.tm.SupplierDetailTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {

    public static ArrayList getAllDetails() throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM supplier";
        ResultSet resultSet= SQLUtil.execute(sql);
        ArrayList arrayList=new ArrayList();
        while (resultSet.next()){
            String sql1="SELECT COUNT(*)AS number FROM suporder  WHERE SupplierId=?  GROUP BY SupplierId";
            ResultSet resultSet1= SQLUtil.execute(sql1,resultSet.getString(1));
            if (resultSet1.next()) {
                arrayList.add(new SupplierDetailTm(resultSet.getString(2), resultSet.getInt(4),
                        resultSet.getString(3),resultSet1.getInt(1)));
            }else {
                arrayList.add(new SupplierDetailTm(resultSet.getString(2), resultSet.getInt(4),
                        resultSet.getString(3),0));
            }

            }
        return arrayList;
    }

    public static String getSupplierId() throws SQLException, ClassNotFoundException {
        String sql="SELECT SupplierId FROM supplier ORDER BY SupplierId DESC LIMIT 1";
        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;

    }

    public static boolean insertSupplierData(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO supplier VALUES (?,?,?,?,?)";
        return SQLUtil.execute(sql, supplierDTO.getSupplierId(), supplierDTO.getName(), supplierDTO.getLocation(),
                supplierDTO.getPhoneNumber(), supplierDTO.getUserName());

    }

    public static boolean deleteSupplier(String supId) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM supplier WHERE SupplierId=? ";
        return SQLUtil.execute(sql,supId);
    }

    public static boolean updateSupplier(String name, String address, int phoneNumber, String supId) throws SQLException, ClassNotFoundException {
        String sql="UPDATE supplier SET Name=?,PhoneNumber=?,Location=? WHERE SupplierId=? ";
        return SQLUtil.execute(sql,name,phoneNumber,address,supId);
    }

    public static String getSupplierIdFromNumber(String valueOf) throws SQLException, ClassNotFoundException {
        String sql="SELECT SupplierId FROM supplier WHERE PhoneNumber=?";
        ResultSet resultSet= SQLUtil.execute(sql,valueOf);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public static String getAdminUsername() throws SQLException, ClassNotFoundException {
        String sql="SELECT Username FROM user WHERE Role='Admin' ";
        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }
}
