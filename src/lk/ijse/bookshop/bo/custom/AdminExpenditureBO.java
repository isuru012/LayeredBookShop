package lk.ijse.bookshop.bo.custom;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:03 AM

*/


import lk.ijse.bookshop.dto.ExpenditureDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminExpenditureBO extends SuperBO {
    ArrayList <ExpenditureDTO> getAllDetails() throws SQLException, ClassNotFoundException;
    boolean updateExpenditure(ExpenditureDTO expenditureDTO) throws SQLException, ClassNotFoundException;
    boolean deleteExpenditure(String id) throws SQLException, ClassNotFoundException;
    String getCurrentId() throws SQLException, ClassNotFoundException;
    String getUsername() throws SQLException, ClassNotFoundException;
    boolean insertExpenditureData(ExpenditureDTO expenditureDTO) throws SQLException, ClassNotFoundException;

}
