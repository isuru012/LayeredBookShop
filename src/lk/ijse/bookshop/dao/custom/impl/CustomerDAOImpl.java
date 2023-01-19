package lk.ijse.bookshop.dao.custom.impl;

import lk.ijse.bookshop.dao.SQLUtil;
import lk.ijse.bookshop.dao.custom.CustomerDAO;

import lk.ijse.bookshop.dto.OrderDetailDTO;
import lk.ijse.bookshop.entity.Customer;
import lk.ijse.bookshop.view.tm.CustomerTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class CustomerDAOImpl implements CustomerDAO {

    public Customer search(String text) throws SQLException, ClassNotFoundException {
        String searchText="%"+text+"%";
        String sql="SELECT * FROM customer WHERE Name LIKE ? OR PhoneNumber LIKE ?";
        ResultSet resultSet= SQLUtil.execute(sql,searchText,searchText);
        if (resultSet.next()){
            return new Customer(resultSet.getString(1),resultSet.getString(2),Integer.parseInt(resultSet.getString(3)),
                    (java.sql.Date) resultSet.getObject(4),resultSet.getString(5));
        }
        return null;
    }
    public  int getCustomersAmount() throws SQLException, ClassNotFoundException {
        String sql="SELECT COUNT(CusId) FROM customer";
        ResultSet resultSet= SQLUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return -1;
    }

    public  ArrayList getAll() throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM customer";
        ResultSet resultSet= SQLUtil.execute(sql);
        ArrayList arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(new CustomerTm(resultSet.getString(1), resultSet.getString(2),resultSet.getInt(3), resultSet.getDate(4)));
        }
        return arrayList;
    }


    public  String getId() throws SQLException, ClassNotFoundException {
        String sql="SELECT CusId FROM customer ORDER BY CusId DESC LIMIT 1";
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

    public  boolean insert(Customer customer) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO Customer VALUES (?,?,?,?,?)";
        return SQLUtil.execute(sql, customer.getCusId(), customer.getName(), customer.getPhoneNumber(), customer.getJoinedDate(), customer.getEmployeeId());

    }

    /*public  boolean update(String name, int phoneNumber, String cusId) throws SQLException, ClassNotFoundException {
        String sql="UPDATE customer SET Name=?,PhoneNumber=? WHERE CusId=? ";
        return SQLUtil.execute(sql,name,phoneNumber,cusId);
    }*/
    public  boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        String sql="UPDATE customer SET Name=?,PhoneNumber=? WHERE CusId=? ";
        return SQLUtil.execute(sql,customer.getName(),customer.getPhoneNumber(),customer.getCusId());
    }

    public  boolean delete(String cusId) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM customer WHERE CusId=? ";
        return SQLUtil.execute(sql,cusId);
    }
}
