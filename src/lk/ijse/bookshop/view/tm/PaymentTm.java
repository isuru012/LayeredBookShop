package lk.ijse.bookshop.view.tm;

import java.sql.Date;
import java.sql.Time;

public class PaymentTm {
    private String paymentId;
    private double amount;
    private Date date;
    private Time time;

    private String supplierName;
    private String expenditure;


    public PaymentTm() {

    }

    public PaymentTm(String paymentId, double amount, Date date, Time time, String supplierName, String expenditure) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.supplierName = supplierName;
        this.expenditure = expenditure;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(String expenditure) {
        this.expenditure = expenditure;
    }

}
