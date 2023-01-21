package lk.ijse.bookshop.bo.custom.impl;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:06 AM

*/


import lk.ijse.bookshop.bo.custom.AdminSupplierDetailsBO;
import lk.ijse.bookshop.dao.DAOFactory;
import lk.ijse.bookshop.dao.custom.SupplierDAO;
import lk.ijse.bookshop.dao.custom.UserDAO;
import lk.ijse.bookshop.dto.SupplierDTO;
import lk.ijse.bookshop.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminSupplierDetailsBOImpl implements AdminSupplierDetailsBO {
    SupplierDAO supplierDAO= (SupplierDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.SUPPLIER);
    UserDAO userDAO= (UserDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.USER);

    @Override
    public ArrayList getAllDetails() throws SQLException, ClassNotFoundException {
        return supplierDAO.getAll();
    }

    @Override
    public boolean deleteSupplier(String SupId) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(SupId);
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(supplierDTO.getSupplierId(),
                supplierDTO.getName(),supplierDTO.getSupplierId(),supplierDTO.getPhoneNumber(),
                supplierDTO.getUserName()));
    }

    @Override
    public String getSupplierIdFromNumber(String phoneNumber) throws SQLException, ClassNotFoundException {
        return supplierDAO.getSupplierIdFromNumber(phoneNumber);
    }

    @Override
    public String getSupplierId() throws SQLException, ClassNotFoundException {
        return supplierDAO.getId();
    }

    @Override
    public String getUserName() throws SQLException, ClassNotFoundException {
        return userDAO.getUserName();
    }

    @Override
    public boolean insertSupplierData(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.insert(new Supplier(supplierDTO.getSupplierId(),
                supplierDTO.getName(),supplierDTO.getSupplierId(),supplierDTO.getPhoneNumber(),
                supplierDTO.getUserName()));
    }
}
