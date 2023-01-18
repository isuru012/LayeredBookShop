package lk.ijse.bookshop.model;

import lk.ijse.bookshop.dto.ExpenditureDTO;
import lk.ijse.bookshop.dao.SQLUtil;
import lk.ijse.bookshop.view.tm.ExpenditureTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExpenditureModel {

    public static ArrayList getAllDetails() throws SQLException, ClassNotFoundException {
        String sql = "select * from expenditure";
        ResultSet resultSet = SQLUtil.execute(sql);
        ArrayList arrayList = new ArrayList();
        while (resultSet.next()) {
            arrayList.add(new ExpenditureTm(resultSet.getString(1), resultSet.getString(2), resultSet.getTime(5),
                    resultSet.getDate(4), resultSet.getDouble(3)));
        }
        return arrayList;

    }

    public static String getCurrentId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT ExpenditureId FROM expenditure ORDER BY ExpenditureId DESC LIMIT 1";
        ResultSet resultSet = SQLUtil.execute(sql);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public static String getUsername() throws SQLException, ClassNotFoundException {
        String sql = "SELECT Username FROM  user WHERE Role='Admin' ";
        ResultSet resultSet = SQLUtil.execute(sql);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public static boolean insertExpenditureData(ExpenditureDTO expenditureDTO) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO expenditure VALUES (?,?,?,?,?,?)";
        return SQLUtil.execute(sql, expenditureDTO.getExpenditure(), expenditureDTO.getDescription(), expenditureDTO.getAmount(), expenditureDTO.getDate(),
                expenditureDTO.getTime(), expenditureDTO.getUserName());
    }


    public static boolean updateExpenditure(String expenditureId, String desCriptionText, double amount) throws SQLException, ClassNotFoundException {
        String sql="UPDATE  expenditure SET Description=?,Amount=? WHERE ExpenditureId=?";
        return SQLUtil.execute(sql,desCriptionText,amount,expenditureId);
    }

    public static boolean deleteExpenditure(String expenditureId) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM expenditure WHERE ExpenditureId=?";
        return SQLUtil.execute(sql,expenditureId);
    }
}
