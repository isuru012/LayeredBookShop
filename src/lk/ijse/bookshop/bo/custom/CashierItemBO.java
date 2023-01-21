package lk.ijse.bookshop.bo.custom;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:07 AM

*/


import java.sql.SQLException;
import java.util.ArrayList;

public interface CashierItemBO extends SuperBO {
    ArrayList getAllDetails() throws SQLException, ClassNotFoundException;
}
