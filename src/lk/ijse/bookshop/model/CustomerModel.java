package lk.ijse.bookshop.model;

import lk.ijse.bookshop.dto.CustomerDTO;
import lk.ijse.bookshop.dao.SQLUtil;
import lk.ijse.bookshop.view.tm.CustomerTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CustomerModel {
    /*public static CustomerDTO search(String text) throws SQLException, ClassNotFoundException {
        String searchText="%"+text+"%";
        String sql="SELECT * FROM customer WHERE Name LIKE ? OR PhoneNumber LIKE ?";
        ResultSet resultSet= SQLUtil.execute(sql,searchText,searchText);
        if (resultSet.next()){
            return new CustomerDTO(resultSet.getString(1),resultSet.getString(2),Integer.parseInt(resultSet.getString(3)),
                    (Date) resultSet.getObject(4),resultSet.getString(5));
        }
        return null;
    }

    public static ArrayList getAllDetails() throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM customer";
        ResultSet resultSet= SQLUtil.execute(sql);
        ArrayList arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(new CustomerTm(resultSet.getString(1), resultSet.getString(2),resultSet.getInt(3), resultSet.getDate(4)));
        }
        return arrayList;
    }


    public static String getOrderId() throws SQLException, ClassNotFoundException {
        String sql="SELECT CusId FROM customer ORDER BY CusId DESC LIMIT 1";
        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public static boolean insertCustomerData(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO Customer VALUES (?,?,?,?,?)";
        return SQLUtil.execute(sql, customerDTO.getCusId(), customerDTO.getName(), customerDTO.getPhoneNumber(), customerDTO.getJoindDate(), customerDTO.getEmployeeId());

    }

    public static boolean updateCustomer(String name, int phoneNumber, String cusId) throws SQLException, ClassNotFoundException {
        String sql="UPDATE customer SET Name=?,PhoneNumber=? WHERE CusId=? ";
        return SQLUtil.execute(sql,name,phoneNumber,cusId);
    }


    public static boolean deleteCustomer(String cusId) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM customer WHERE CusId=? ";
        return SQLUtil.execute(sql,cusId);
    }*/
}
