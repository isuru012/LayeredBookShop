package lk.ijse.bookshop.dto;

import java.util.Date;

public class CustomerDTO {
    private String CusId;
    private String Name;
    private int PhoneNumber;
    private Date JoindDate;
    private String EmployeeId;

    public CustomerDTO() {
    }

    public CustomerDTO(String cusId, String name, int phoneNumber, Date joindDate, String employeeId) {
        CusId = cusId;
        Name = name;
        PhoneNumber = phoneNumber;
        JoindDate = joindDate;
        EmployeeId = employeeId;
    }

    public String getCusId() {
        return CusId;
    }

    public void setCusId(String cusId) {
        CusId = cusId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Date getJoindDate() {
        return JoindDate;
    }

    public void setJoindDate(Date joindDate) {
        JoindDate = joindDate;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }
}
