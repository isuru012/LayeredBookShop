package lk.ijse.bookshop.bo.custom;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:15 AM

*/


import lk.ijse.bookshop.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminDashboardBO extends SuperBO {
    ArrayList getTrendingItems() throws SQLException, ClassNotFoundException;
    int getOrdersAmount() throws SQLException, ClassNotFoundException;
    int getProductsAmount() throws SQLException, ClassNotFoundException;
    int getCustomersAmount() throws SQLException, ClassNotFoundException;
    double getMonday() throws SQLException, ClassNotFoundException;
    double getTuesday() throws SQLException, ClassNotFoundException;
    double getWednesday() throws SQLException, ClassNotFoundException;
    double getThursday() throws SQLException, ClassNotFoundException;
    double getFriday() throws SQLException, ClassNotFoundException;
    double getSaturday() throws SQLException, ClassNotFoundException;
    double getSunday() throws SQLException, ClassNotFoundException;
}
