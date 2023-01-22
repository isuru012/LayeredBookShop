package lk.ijse.bookshop.bo.custom.impl;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:04 AM

*/


import lk.ijse.bookshop.bo.custom.AdminOfferBO;
import lk.ijse.bookshop.dao.DAOFactory;
import lk.ijse.bookshop.dao.custom.ItemDAO;
import lk.ijse.bookshop.dao.custom.OfferDAO;
import lk.ijse.bookshop.dto.ItemDTO;
import lk.ijse.bookshop.dto.OfferDTO;
import lk.ijse.bookshop.entity.Item;
import lk.ijse.bookshop.entity.Offer;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminOfferBOImpl implements AdminOfferBO {
    ItemDAO itemDAO= (ItemDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.ITEM);
    OfferDAO offerDAO= (OfferDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.OFFER);

    @Override
    public ArrayList getAllDetails() throws SQLException, ClassNotFoundException {
        return offerDAO.getAll();
    }

    @Override
    public ArrayList loadAllItemNames() throws SQLException, ClassNotFoundException {
        return itemDAO.loadAllDescriptionIds();
    }

    @Override
    public ArrayList loadAllItemPrices(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.loadAllItemPrices(id);
    }

    @Override
    public String getNowOfferId() throws SQLException, ClassNotFoundException {
        return offerDAO.getId();
    }

    @Override
    public int getBatchNumber(Double sellingPrice, String itemCode) throws SQLException, ClassNotFoundException {
        return itemDAO.getBatchNumber(sellingPrice,itemCode);
    }

    @Override
    public boolean updateItemData(String itemCode, String code, int batchNumber) throws SQLException, ClassNotFoundException {
        return itemDAO.updateItemData(itemCode,code,batchNumber);
    }

    @Override
    public boolean insertOfferData(OfferDTO offerDTO) throws SQLException, ClassNotFoundException {
        return offerDAO.insert(new Offer(offerDTO.getOfferId(),offerDTO.getAmount(),offerDTO.getStartDate(),
                offerDTO.getEndDate()));
    }

    @Override
    public String getOfferId(String itemCode) throws SQLException, ClassNotFoundException {
        return itemDAO.getOfferId(itemCode);
    }

    @Override
    public boolean deleteOffer(String itemCode) throws SQLException, ClassNotFoundException {
        return  itemDAO.deleteOffer(itemCode);
    }

    @Override
    public boolean deleteOfferData(String offerId) throws SQLException, ClassNotFoundException {
        return offerDAO.delete(offerId);
    }

    @Override
    public String getItemName(String itemId) throws SQLException, ClassNotFoundException {
        return itemDAO.getDescription(itemId);
    }

    @Override
    public boolean updateOffer(OfferDTO offerDTO) throws SQLException, ClassNotFoundException {
        return offerDAO.update(new Offer(offerDTO.getOfferId(),offerDTO.getAmount(),offerDTO.getStartDate(),
                offerDTO.getEndDate()));
    }

    @Override
    public String getItemId(String itemName) throws SQLException, ClassNotFoundException {
        return itemDAO.getItemId(itemName);
    }
}
