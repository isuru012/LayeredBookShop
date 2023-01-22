package lk.ijse.bookshop.bo.custom.impl;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:03 AM

*/


import lk.ijse.bookshop.bo.custom.AdminExpenditureBO;
import lk.ijse.bookshop.dao.DAOFactory;
import lk.ijse.bookshop.dao.custom.ExpenditureDAO;
import lk.ijse.bookshop.dao.custom.UserDAO;
import lk.ijse.bookshop.dto.ExpenditureDTO;
import lk.ijse.bookshop.entity.Expenditure;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminExpenditureBOImpl implements AdminExpenditureBO {
    ExpenditureDAO expenditureDAO= (ExpenditureDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.EXPENDITURE);
    UserDAO userDAO= (UserDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.USER);

    @Override
    public ArrayList getAllDetails() throws SQLException, ClassNotFoundException {
        /*ArrayList<ExpenditureDTO> expenditureDTOS=new ArrayList<>();
        ArrayList<Expenditure> expenditures= expenditureDAO.getAll();
        for (Expenditure i:expenditures) {
            expenditureDTOS.add(new ExpenditureDTO(i.getExpenditureId(),i.getDescription(),i.getAmount(),i.getDate(),i.getTime(),i.getUserName()));
        }
        return expenditureDTOS;*/
        return expenditureDAO.getAll();
    }

    @Override
    public boolean updateExpenditure(ExpenditureDTO expenditureDTO) throws SQLException, ClassNotFoundException {
        return expenditureDAO.update(new Expenditure(expenditureDTO.getExpenditure(),expenditureDTO.getDescription(),expenditureDTO.getAmount(),
                expenditureDTO.getDate(),expenditureDTO.getTime(),expenditureDTO.getUserName()));
    }

    @Override
    public boolean deleteExpenditure(String id) throws SQLException, ClassNotFoundException {
        return expenditureDAO.delete(id);
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return expenditureDAO.getId();
    }

    @Override
    public String getUsername() throws SQLException, ClassNotFoundException {
        return userDAO.getUserName();
    }

    @Override
    public boolean insertExpenditureData(ExpenditureDTO expenditureDTO) throws SQLException, ClassNotFoundException {
        return expenditureDAO.insert(new Expenditure(expenditureDTO.getExpenditure(),expenditureDTO.getDescription(),expenditureDTO.getAmount(),
                expenditureDTO.getDate(),expenditureDTO.getTime(),expenditureDTO.getUserName()));
    }
}
