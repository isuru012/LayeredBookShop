package lk.ijse.bookshop.bo.custom.impl;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:11 AM

*/


import lk.ijse.bookshop.bo.custom.TrueResetPasswordBO;
import lk.ijse.bookshop.dao.DAOFactory;
import lk.ijse.bookshop.dao.custom.UserDAO;

import java.sql.SQLException;

public class TrueResetPasswordBOImpl  implements TrueResetPasswordBO {
    UserDAO userDAO= (UserDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.USER);
    @Override
    public boolean passwordReset(String username, String password) throws SQLException, ClassNotFoundException {
        return userDAO.passwordReset(username,password);
    }
}
