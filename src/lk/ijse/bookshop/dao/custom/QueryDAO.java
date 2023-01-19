package lk.ijse.bookshop.dao.custom;

import java.sql.SQLException;

public interface QueryDAO extends SuperDAO{
    double getMonday() throws SQLException, ClassNotFoundException;
    double getTuesday() throws SQLException, ClassNotFoundException;
    double getWednesday() throws SQLException, ClassNotFoundException;
    double getThursday() throws SQLException, ClassNotFoundException;
    double getFriday() throws SQLException, ClassNotFoundException;
    double getSaturday() throws SQLException, ClassNotFoundException;
    double getSunday() throws SQLException, ClassNotFoundException;

}
