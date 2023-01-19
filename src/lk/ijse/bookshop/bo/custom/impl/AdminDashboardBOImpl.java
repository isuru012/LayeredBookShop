package lk.ijse.bookshop.bo.custom.impl;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:02 AM

*/


import lk.ijse.bookshop.bo.custom.AdminDashboardBO;
import lk.ijse.bookshop.dao.DAOFactory;
import lk.ijse.bookshop.dao.custom.CusOrderDAO;
import lk.ijse.bookshop.dao.custom.CustomerDAO;
import lk.ijse.bookshop.dao.custom.ItemDAO;
import lk.ijse.bookshop.dao.custom.QueryDAO;
import lk.ijse.bookshop.dto.ItemDTO;
import lk.ijse.bookshop.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDashboardBOImpl implements AdminDashboardBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.CUSTOMER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.ITEM);
    CusOrderDAO cusOrderDAO = (CusOrderDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.CUSORDER);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.QUERYDAO);

    @Override
    public ArrayList<ItemDTO> getTrendingItems() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        ArrayList<Item> items = itemDAO.getAll();
        for (Item i : items) {
            itemDTOS.add(new ItemDTO(i.getItemId(), i.getBatchNumber(), i.getDescription(),
                    i.getBuyingUnitPrice(), i.getSellingUnitPrice(), i.getQuantityOnHand(),
                    i.getOfferId()));

        }
        return itemDTOS;
    }

    @Override
    public int getOrdersAmount() throws SQLException, ClassNotFoundException {
        return cusOrderDAO.getOrdersAmount();
    }

    @Override
    public int getProductsAmount() throws SQLException, ClassNotFoundException {
        return itemDAO.getProductsAmount();
    }

    @Override
    public int getCustomersAmount() throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomersAmount();
    }

    @Override
    public double getMonday() throws SQLException, ClassNotFoundException {
        return queryDAO.getMonday();
    }

    @Override
    public double getTuesday() throws SQLException, ClassNotFoundException {
        return queryDAO.getTuesday();
    }

    @Override
    public double getWednesday() throws SQLException, ClassNotFoundException {
        return queryDAO.getWednesday();
    }

    @Override
    public double getThursday() throws SQLException, ClassNotFoundException {
        return queryDAO.getThursday();
    }

    @Override
    public double getFriday() throws SQLException, ClassNotFoundException {
        return queryDAO.getFriday();
    }

    @Override
    public double getSaturday() throws SQLException, ClassNotFoundException {
        return queryDAO.getSaturday();
    }

    @Override
    public double getSunday() throws SQLException, ClassNotFoundException {
        return queryDAO.getSunday();
    }
}
