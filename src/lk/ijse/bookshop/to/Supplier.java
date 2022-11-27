package lk.ijse.bookshop.to;

public class Supplier {
    private String SupplierId;
    private String Name;
    private String Location;
    private int phoneNumber;
    private String userName;

    public Supplier() {
    }

    public Supplier(String supplierId, String name, String location, int phoneNumber, String userName) {
        SupplierId = supplierId;
        Name = name;
        Location = location;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
    }

    public String getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(String supplierId) {
        SupplierId = supplierId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
