package lk.ijse.bookshop.view.tm;

import java.time.LocalDate;
import java.util.Date;

public class CustomerTm {
    private String code;
    private String name;
    private int phoneNumber;
    private Date joinedDate;


    public CustomerTm() {
    }

    public CustomerTm(String code, String name, int phoneNumber, Date joinedDate) {
        this.code = code;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.joinedDate = joinedDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
