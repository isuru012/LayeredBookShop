package lk.ijse.bookshop.bo;

import lk.ijse.bookshop.bo.custom.SuperBO;
import lk.ijse.bookshop.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }
    public static BOFactory getBOFactory(){
        if (boFactory==null){
            boFactory=new BOFactory();
        }
        return boFactory;
    }
    public enum BOTypes{
        DASHBOARD,EMPLOYEE,EXPENDITURE,ADMINITEM,ADMINOFFER,ADMINPAYMENT,ADMINSUPPLIER,SUPPLIERDETAILS,ADMINWINDOW,CASHIERCUSTOMER,CASHIERITEM,
        CASHIERMAKERELOAD,CASHIERPLACEORDER,CASHIERWINDOW,CREATEACCOUNT,LOADINGSCREEN,LOGINFORM,PASSWORDRESETFORM,TRUERESETPASSWORD
    }
    public SuperBO getBOTypes(BOTypes boTypes){
        switch (boTypes){
            case DASHBOARD:
                return new AdminDashboardBOImpl();
            case CASHIERITEM:
                return new CashierItemBOImpl();
            case EXPENDITURE:
                return new AdminExpenditureBOImpl();
            case EMPLOYEE:
                return new AdminEmployeeBOImpl();
            case ADMINITEM:
                return new AdminItemBOImpl();
            case LOGINFORM:
                return new LoginFormBOImpl();
            case ADMINOFFER:
                return new AdminOfferBOImpl();
            case ADMINWINDOW:
                return new AdminWindowBOImpl();
            case ADMINPAYMENT:
                return new AdminPaymentBOImpl();
            case ADMINSUPPLIER:
                return new AdminSupplierOrderBOImpl();
            case CASHIERWINDOW:
                return new CashierWindowBOImpl();
            case CREATEACCOUNT:
                return new CreateAccountBOImpl();
            case LOADINGSCREEN:
                return new LoadingScreenBOImpl();
            case CASHIERCUSTOMER:
                return new CashierCustomerBOImpl();
            case SUPPLIERDETAILS:
                return new AdminSupplierDetailsBOImpl();
            case CASHIERMAKERELOAD:
                return new CashierMakeReloadBOImpl();
            case CASHIERPLACEORDER:
                return new CashierPlaceOrderBOImpl();
            case PASSWORDRESETFORM:
                return new PasswordResetFormBOImpl();
            case TRUERESETPASSWORD:
                return new TrueResetPasswordBOImpl();
            default:
                return null;
        }
    }
}
