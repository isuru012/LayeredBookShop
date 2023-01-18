package lk.ijse.bookshop.entity;

import java.sql.Date;

public class Customer {
    private String cusId;
    private String name;
    private int phoneNumber;
    private Date joinedDate;
    private String employeeId;

    public Customer() {
    }

    public Customer(String cusId, String name, int phoneNumber, Date joinedDate, String employeeId) {
        this.cusId = cusId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.joinedDate = joinedDate;
        this.employeeId = employeeId;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cusId='" + cusId + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", joinedDate=" + joinedDate +
                ", employeeId='" + employeeId + '\'' +
                '}';
    }
}
