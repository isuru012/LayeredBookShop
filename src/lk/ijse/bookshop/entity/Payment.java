package lk.ijse.bookshop.entity;

import java.sql.Date;
import java.sql.Time;

public class Payment {
    private String paymentId;
    private double amount;
    private Date date;
    private Time time;
    private String userName;
    private String supOrderId;
    private String expenditureId;

    public Payment() {
    }

    public Payment(String paymentId, double amount, Date date, Time time, String userName, String supOrderId, String expenditureId) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.userName = userName;
        this.supOrderId = supOrderId;
        this.expenditureId = expenditureId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSupOrderId() {
        return supOrderId;
    }

    public void setSupOrderId(String supOrderId) {
        this.supOrderId = supOrderId;
    }

    public String getExpenditureId() {
        return expenditureId;
    }

    public void setExpenditureId(String expenditureId) {
        this.expenditureId = expenditureId;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", time=" + time +
                ", userName='" + userName + '\'' +
                ", supOrderId='" + supOrderId + '\'' +
                ", expenditureId='" + expenditureId + '\'' +
                '}';
    }
}
