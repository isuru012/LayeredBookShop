package lk.ijse.bookshop.bo.custom.impl;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:07 AM

*/


import lk.ijse.bookshop.bo.custom.CashierItemBO;
import lk.ijse.bookshop.dao.DAOFactory;
import lk.ijse.bookshop.dao.custom.ItemDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CashierItemBOImpl implements CashierItemBO {
    ItemDAO itemDAO= (ItemDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.ITEM);
    @Override
    public ArrayList getAllDetails() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }
}
