package lk.ijse.bookshop.bo.custom.impl;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:10 AM

*/


import lk.ijse.bookshop.bo.custom.PasswordResetFormBO;
import lk.ijse.bookshop.dao.DAOFactory;
import lk.ijse.bookshop.dao.custom.UserDAO;

import java.sql.SQLException;

public class PasswordResetFormBOImpl implements PasswordResetFormBO {

    UserDAO userDAO= (UserDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.USER);
    @Override
    public boolean checkUsername(String Username) throws SQLException, ClassNotFoundException {
        return userDAO.checkUsername(Username);
    }
}
