package lk.ijse.bookshop.dao.custom;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO{
    ArrayList getTrendingItems() throws SQLException, ClassNotFoundException;
    ArrayList getAllDetailsFromOffers() throws SQLException, ClassNotFoundException;
    double getMonday() throws SQLException, ClassNotFoundException;
    double getTuesday() throws SQLException, ClassNotFoundException;
    double getWednesday() throws SQLException, ClassNotFoundException;
    double getThursday() throws SQLException, ClassNotFoundException;
    double getFriday() throws SQLException, ClassNotFoundException;
    double getSaturday() throws SQLException, ClassNotFoundException;
    double getSunday() throws SQLException, ClassNotFoundException;

    double getOfferAmount(String code, double sellingUnitPrice) throws SQLException, ClassNotFoundException;

}
