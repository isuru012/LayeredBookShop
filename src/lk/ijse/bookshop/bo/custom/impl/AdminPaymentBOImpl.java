package lk.ijse.bookshop.bo.custom.impl;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:04 AM

*/


import lk.ijse.bookshop.bo.custom.AdminPaymentBO;
import lk.ijse.bookshop.dao.DAOFactory;
import lk.ijse.bookshop.dao.custom.PaymentDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminPaymentBOImpl implements AdminPaymentBO {
    PaymentDAO paymentDAO= (PaymentDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.PAYMENT);
    @Override
    public ArrayList getAllDetails() throws SQLException, ClassNotFoundException {
        return paymentDAO.getAll();
    }
}
