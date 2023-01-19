package lk.ijse.bookshop.dao;

import lk.ijse.bookshop.bo.BOFactory;
import lk.ijse.bookshop.bo.custom.SuperBO;
import lk.ijse.bookshop.bo.custom.impl.AdminDashboardBOImpl;
import lk.ijse.bookshop.dao.custom.SuperDAO;
import lk.ijse.bookshop.dao.custom.impl.*;
import lk.ijse.bookshop.entity.Supplier;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }
    public static DAOFactory getDAOFactory(){
        if (daoFactory==null){
            daoFactory=new DAOFactory();
        }
        return daoFactory;
    }
    public enum DAOTypes{
        CUSTOMER,ITEM,CUSORDER,CUSORDERDETAILS,CUSRELOAD,EMPLOYEE,EXPENDITURE,OFFER,PAYMENT,QUERYDAO,SUPORDER,SUPORDERDETAILS,
        SUPPLIER,USER
    }
    public SuperDAO getDAOTypes(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case USER:
                return new UserDAOImpl();
            case OFFER:
                return new OfferDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case CUSORDER:
                return new CusOrderDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case QUERYDAO:
                return new QueryDAOImpl();
            case SUPORDER:
                return new SupOrderDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case CUSRELOAD:
                return new CusReloadDAOImpl();
            case EXPENDITURE:
                return new ExpenditureDAOImpl();
            case CUSORDERDETAILS:
                return new CusOrderDetailsDAOImpl();
            case SUPORDERDETAILS:
                return new SupOrderDetailsDAOImpl();

            default:
                return null;
        }
    }
}
