package lk.ijse.bookshop.entity;

import java.sql.Date;
import java.sql.Time;

public class CustomEntity {
    private String cusOrderId;
    private Date date;
    private Time time;
    private String cusId;
    private String employeeId;

    private String itemId;
    private double unitPrice;
    private int quantity;
    private double totalPrice;

    private String cusReloadId;

    private String reloadId;

    private String name;
    private int phoneNumber;
    private Date joinedDate;

    private String address;
    private double salary;
    private String userName;

    private String expenditureId;
    private String description;
    private double amount;

    private int batchNumber;
    private double buyingUnitPrice;
    private double sellingUnitPrice;
    private int quantityOnHand;
    private String offerId;

    private Date startedDate;
    private Date endedDate;

    private String paymentId;
    private String supOrderId;

    private String serviceProvider;
    private double reloadAmount;
    private double profitPercentage;

    private String supplierId;

    private String location;

    private String password;
    private String role;

    public CustomEntity() {
    }

    public CustomEntity(String cusOrderId, Date date, Time time, String cusId, String employeeId,
                        String itemId, double unitPrice, int quantity, double totalPrice, String cusReloadId,
                        String reloadId, String name, int phoneNumber, Date joinedDate, String address, double salary,
                        String userName, String expenditureId, String description, double amount, int batchNumber,
                        double buyingUnitPrice, double sellingUnitPrice, int quantityOnHand, String offerId,
                        Date startedDate, Date endedDate, String paymentId, String supOrderId, String serviceProvider,
                        double reloadAmount, double profitPercentage, String supplierId, String location,
                        String password, String role) {
        this.cusOrderId = cusOrderId;
        this.date = date;
        this.time = time;
        this.cusId = cusId;
        this.employeeId = employeeId;
        this.itemId = itemId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.cusReloadId = cusReloadId;
        this.reloadId = reloadId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.joinedDate = joinedDate;
        this.address = address;
        this.salary = salary;
        this.userName = userName;
        this.expenditureId = expenditureId;
        this.description = description;
        this.amount = amount;
        this.batchNumber = batchNumber;
        this.buyingUnitPrice = buyingUnitPrice;
        this.sellingUnitPrice = sellingUnitPrice;
        this.quantityOnHand = quantityOnHand;
        this.offerId = offerId;
        this.startedDate = startedDate;
        this.endedDate = endedDate;
        this.paymentId = paymentId;
        this.supOrderId = supOrderId;
        this.serviceProvider = serviceProvider;
        this.reloadAmount = reloadAmount;
        this.profitPercentage = profitPercentage;
        this.supplierId = supplierId;
        this.location = location;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "CustomEntity{" +
                "cusOrderId='" + cusOrderId + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", cusId='" + cusId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", cusReloadId='" + cusReloadId + '\'' +
                ", reloadId='" + reloadId + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", joinedDate=" + joinedDate +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", userName='" + userName + '\'' +
                ", expenditureId='" + expenditureId + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", batchNumber=" + batchNumber +
                ", buyingUnitPrice=" + buyingUnitPrice +
                ", sellingUnitPrice=" + sellingUnitPrice +
                ", quantityOnHand=" + quantityOnHand +
                ", offerId='" + offerId + '\'' +
                ", startedDate=" + startedDate +
                ", endedDate=" + endedDate +
                ", paymentId='" + paymentId + '\'' +
                ", supOrderId='" + supOrderId + '\'' +
                ", serviceProvider='" + serviceProvider + '\'' +
                ", reloadAmount=" + reloadAmount +
                ", profitPercentage=" + profitPercentage +
                ", supplierId='" + supplierId + '\'' +
                ", location='" + location + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
