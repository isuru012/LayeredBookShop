package lk.ijse.bookshop.bo.custom.impl;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:08 AM

*/


import lk.ijse.bookshop.bo.custom.CashierMakeReloadBO;
import lk.ijse.bookshop.dao.DAOFactory;
import lk.ijse.bookshop.dao.custom.*;
import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.dto.*;
import lk.ijse.bookshop.entity.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class CashierMakeReloadBOImpl implements CashierMakeReloadBO {
    CusReloadDetailsDAO cusReloadDetailsDAO= (CusReloadDetailsDAO) DAOFactory.getDAOFactory().
            getDAOTypes(DAOFactory.DAOTypes.CUSRELOADDETAILS);
    ReloadDAO reloadDAO= (ReloadDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.RELOAD);
    CusReloadDAO cusReloadDAO= (CusReloadDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.CUSRELOAD);

    CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.CUSTOMER);
    ItemDAO itemDAO= (ItemDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList getAllDetails() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }

    @Override
    public String getOrderId() throws SQLException, ClassNotFoundException {
        return cusReloadDAO.getId();
    }

    @Override
    public ArrayList loadAllServiceProviders() throws SQLException, ClassNotFoundException {
        return reloadDAO.loadAllServiceProviders();
    }

    @Override
    public String getCusId() throws SQLException, ClassNotFoundException {
        return customerDAO.getId();
    }

    @Override
    public boolean insertCustomerData(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.insert(new Customer(customerDTO.getCusId(),customerDTO.getName(),
                customerDTO.getPhoneNumber(), (Date) customerDTO.getJoindDate(),customerDTO.getEmployeeId()));
    }

    @Override
    public ReloadDTO searchDescription(String text) throws SQLException, ClassNotFoundException {
        Reload reload=reloadDAO.search(text);

        return new ReloadDTO(reload.getReloadId(),reload.getServiceProvider(),reload.getReloadAmount(),
                reload.getProfitPercentage());
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customerDTO.getCusId(),customerDTO.getName(),customerDTO
                .getPhoneNumber(), (Date) customerDTO.getJoindDate(),customerDTO.getEmployeeId()));
    }

    public  boolean placeReload(CusReloadDTO cusReloadDTO) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getDBConnection().getConnection().setAutoCommit(false);


            boolean isAddedOrder=cusReloadDAO.saveReload(new CusReload(cusReloadDTO.getCusReloadId(),
                    cusReloadDTO.getDate(),cusReloadDTO.getTime(),cusReloadDTO.getCusId(),
                    cusReloadDTO.getEmployeeId()));
            if (isAddedOrder){

                ArrayList<CusReloadDetailsDTO> cusReloadDTOS = cusReloadDTO.getCustomerReloadDetails();
                ArrayList<CusReloadDetails> cusReloadDetails =new ArrayList<>();
                for (CusReloadDetailsDTO i : cusReloadDTOS) {
                    cusReloadDetails.add(new CusReloadDetails(i.getCusReloadId(),i.getReloadId(),i.getTotalPrice()
                    ));
                }
                boolean saveReloadDetails= cusReloadDetailsDAO.saveReloadDetails(cusReloadDetails);
                if (saveReloadDetails){
                    boolean updateAmount= reloadDAO.updateAmount(cusReloadDetails);
                    if (updateAmount){
                        DBConnection.getDBConnection().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getDBConnection().getConnection().rollback();
            return false;

        }finally {
            DBConnection.getDBConnection().getConnection().setAutoCommit(true);
        }

    }
}
