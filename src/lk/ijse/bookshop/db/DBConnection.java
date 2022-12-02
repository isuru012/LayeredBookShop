package lk.ijse.bookshop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    private Connection connection;

    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookShop?allowPublicKeyRetrieval=true&useSSL=false", "root", "1234");
    }

    public static DBConnection getDBConnection() throws SQLException, ClassNotFoundException {
        return (null == dbConnection) ? dbConnection=new DBConnection() : dbConnection;
    }
    public Connection getConnection() {

        return connection;
    }

/*
    =================================My DataBase============================================

            -- MySQL Workbench Forward Engineering

    SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
    SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
    SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
        -- Schema mydb
-- -----------------------------------------------------
        -- -----------------------------------------------------
        -- Schema bookshop
-- -----------------------------------------------------
    DROP SCHEMA IF EXISTS `bookshop` ;

-- -----------------------------------------------------
        -- Schema bookshop
-- -----------------------------------------------------
    CREATE SCHEMA IF NOT EXISTS `bookshop` DEFAULT CHARACTER SET utf8mb3 ;
    USE `bookshop` ;

-- -----------------------------------------------------
        -- Table `bookshop`.`user`
            -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS `bookshop`.`user` (
            `Username` VARCHAR(15) NOT NULL,
  `Password` VARCHAR(10) NOT NULL,
  `Role` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`Username`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
        -- Table `bookshop`.`employee`
            -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS `bookshop`.`employee` (
            `EmployeeId` VARCHAR(5) NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Address` VARCHAR(45) NOT NULL,
  `PhoneNumber` INT NOT NULL,
            `Salary` DOUBLE NOT NULL,
            `Username` VARCHAR(15) NOT NULL,
    PRIMARY KEY (`EmployeeId`),
    INDEX `user_idx` (`Username` ASC) VISIBLE,
    CONSTRAINT `user`
    FOREIGN KEY (`Username`)
    REFERENCES `bookshop`.`user` (`Username`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
        -- Table `bookshop`.`attendance`
            -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS `bookshop`.`attendance` (
            `AttendanceId` VARCHAR(10) NOT NULL,
  `Date` DATE NOT NULL,
            `Time` TIME NOT NULL,
            `EmployeeId` VARCHAR(5) NOT NULL,
    PRIMARY KEY (`AttendanceId`),
    INDEX `Em_idx` (`EmployeeId` ASC) VISIBLE,
    CONSTRAINT `Em`
    FOREIGN KEY (`EmployeeId`)
    REFERENCES `bookshop`.`employee` (`EmployeeId`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
        -- Table `bookshop`.`customer`
            -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS `bookshop`.`customer` (
            `CusId` VARCHAR(10) NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `PhoneNumber` INT NOT NULL,
            `JoinedDate` DATE NOT NULL,
            `EmployeeId` VARCHAR(5) NOT NULL,
    PRIMARY KEY (`CusId`),
    INDEX `EmployeeId_idx` (`EmployeeId` ASC) VISIBLE,
    CONSTRAINT `EmployeesId`
    FOREIGN KEY (`EmployeeId`)
    REFERENCES `bookshop`.`employee` (`EmployeeId`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
        -- Table `bookshop`.`cusorder`
            -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS `bookshop`.`cusorder` (
            `CusOrderId` VARCHAR(10) NOT NULL,
  `Date` DATE NOT NULL,
            `Time` TIME NOT NULL,
            `CusId` VARCHAR(10) NOT NULL,
  `EmployeeId` VARCHAR(5) NOT NULL,
    PRIMARY KEY (`CusOrderId`),
    INDEX `CusId_idx` (`CusId` ASC) VISIBLE,
    INDEX ` _idx` (`EmployeeId` ASC) VISIBLE,
    CONSTRAINT `cus1`
    FOREIGN KEY (`CusId`)
    REFERENCES `bookshop`.`customer` (`CusId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `emp2`
    FOREIGN KEY (`EmployeeId`)
    REFERENCES `bookshop`.`employee` (`EmployeeId`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
        -- Table `bookshop`.`offer`
            -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS `bookshop`.`offer` (
            `OfferId` VARCHAR(10) NOT NULL,
  `Amount` DOUBLE NOT NULL,
            `StartedDate` DATE NOT NULL,
            `EndedDate` DATE NOT NULL,
    PRIMARY KEY (`OfferId`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
        -- Table `bookshop`.`item`
            -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS `bookshop`.`item` (
            `ItemId` VARCHAR(10) NOT NULL,
  `BatchNumber` INT NOT NULL,
            `Description` VARCHAR(45) NOT NULL,
  `BuyingUnitPrice` DOUBLE NOT NULL,
            `SellingUnitPrice` DOUBLE NOT NULL,
            `QuantityOnHand` INT NOT NULL,
            `OfferId` VARCHAR(10) NULL DEFAULT NULL,
    PRIMARY KEY (`ItemId`),
    INDEX `OfferId_idx` (`OfferId` ASC) VISIBLE,
    CONSTRAINT `OffersId`
    FOREIGN KEY (`OfferId`)
    REFERENCES `bookshop`.`offer` (`OfferId`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
        -- Table `bookshop`.`cusorderdetails`
            -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS `bookshop`.`cusorderdetails` (
            `CusOrderId` VARCHAR(10) NOT NULL,
  `ItemId` VARCHAR(10) NOT NULL,
  `UnitPrice` DOUBLE NOT NULL,
            `Quantity` INT NOT NULL,
            `TotalPrice` DOUBLE NOT NULL,
    PRIMARY KEY (`CusOrderId`, `ItemId`),
    INDEX `ItemId_idx` (`ItemId` ASC) VISIBLE,
    CONSTRAINT `CustOrderId`
    FOREIGN KEY (`CusOrderId`)
    REFERENCES `bookshop`.`cusorder` (`CusOrderId`),
    CONSTRAINT `ItemsId`
    FOREIGN KEY (`ItemId`)
    REFERENCES `bookshop`.`item` (`ItemId`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
        -- Table `bookshop`.`reload/bill`
            -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS `bookshop`.`reload/bill` (
            `ReloadId` VARCHAR(10) NOT NULL,
  `Phone Number` INT NOT NULL,
            `Amount` DOUBLE NOT NULL,
    PRIMARY KEY (`ReloadId`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
        -- Table `bookshop`.`cusreloaddetails`
            -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS `bookshop`.`cusreloaddetails` (
            `CusOrderId` VARCHAR(10) NOT NULL,
  `ReloadId` VARCHAR(10) NOT NULL,
  `TotalPrice` DOUBLE NOT NULL,
    PRIMARY KEY (`CusOrderId`, `ReloadId`),
    INDEX `ReloadId_idx` (`ReloadId` ASC) VISIBLE,
    CONSTRAINT `CustoOrderId`
    FOREIGN KEY (`CusOrderId`)
    REFERENCES `bookshop`.`cusorder` (`CusOrderId`),
    CONSTRAINT `ReloadBId`
    FOREIGN KEY (`ReloadId`)
    REFERENCES `bookshop`.`reload/bill` (`ReloadId`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
        -- Table `bookshop`.`expenditure`
            -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS `bookshop`.`expenditure` (
            `ExpenditureId` VARCHAR(10) NOT NULL,
  `Description` VARCHAR(45) NOT NULL,
  `Amount` DOUBLE NOT NULL,
            `Date` DATE NOT NULL,
            `Time` TIME NOT NULL,
            `Username` VARCHAR(15) NOT NULL,
    PRIMARY KEY (`ExpenditureId`),
    INDEX `usern2_idx` (`Username` ASC) VISIBLE,
    CONSTRAINT `usern2`
    FOREIGN KEY (`Username`)
    REFERENCES `bookshop`.`user` (`Username`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
        -- Table `bookshop`.`supplier`
            -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS `bookshop`.`supplier` (
            `SupplierId` VARCHAR(10) NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Location` VARCHAR(45) NOT NULL,
  `PhoneNumber` INT NOT NULL,
            `Username` VARCHAR(15) NOT NULL,
    PRIMARY KEY (`SupplierId`),
    INDEX `usern6_idx` (`Username` ASC) VISIBLE,
    CONSTRAINT `usern6`
    FOREIGN KEY (`Username`)
    REFERENCES `bookshop`.`user` (`Username`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
        -- Table `bookshop`.`suporder`
            -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS `bookshop`.`suporder` (
            `SupOrderId` VARCHAR(10) NOT NULL,
  `Date` DATE NOT NULL,
            `Time` TIME NOT NULL,
            `SupplierId` VARCHAR(10) NOT NULL,
  `Username` VARCHAR(15) NOT NULL,
    PRIMARY KEY (`SupOrderId`),
    INDEX `SupplierId_idx` (`SupplierId` ASC) VISIBLE,
    INDEX `usern4_idx` (`Username` ASC) VISIBLE,
    CONSTRAINT `SuppliersId`
    FOREIGN KEY (`SupplierId`)
    REFERENCES `bookshop`.`supplier` (`SupplierId`),
    CONSTRAINT `usern4`
    FOREIGN KEY (`Username`)
    REFERENCES `bookshop`.`user` (`Username`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
        -- Table `bookshop`.`payment`
            -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS `bookshop`.`payment` (
            `PaymentId` VARCHAR(10) NOT NULL,
  `Amount` DOUBLE NOT NULL,
            `Date` DATE NOT NULL,
            `Time` TIME NOT NULL,
            `Username` VARCHAR(15) NOT NULL,
  `SupOrderId` VARCHAR(10) NULL DEFAULT NULL,
  `ExpenditureId` VARCHAR(10) NULL DEFAULT NULL,
    PRIMARY KEY (`PaymentId`),
    INDEX `usern3_idx` (`Username` ASC) VISIBLE,
    INDEX `suporder2_idx` (`SupOrderId` ASC) VISIBLE,
    INDEX `expenditure2_idx` (`ExpenditureId` ASC) VISIBLE,
    CONSTRAINT `expenditure2`
    FOREIGN KEY (`ExpenditureId`)
    REFERENCES `bookshop`.`expenditure` (`ExpenditureId`),
    CONSTRAINT `suporder2`
    FOREIGN KEY (`SupOrderId`)
    REFERENCES `bookshop`.`suporder` (`SupOrderId`),
    CONSTRAINT `usern3`
    FOREIGN KEY (`Username`)
    REFERENCES `bookshop`.`user` (`Username`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
        -- Table `bookshop`.`suporderdetails`
            -- -----------------------------------------------------
    CREATE TABLE IF NOT EXISTS `bookshop`.`suporderdetails` (
            `SupOrderId` VARCHAR(10) NOT NULL,
  `ItemId` VARCHAR(10) NOT NULL,
  `UnitPrice` DOUBLE NOT NULL,
            `Quantity` INT NOT NULL,
            `TotalPrice` DOUBLE NOT NULL,
    PRIMARY KEY (`SupOrderId`, `ItemId`),
    INDEX `item_idx` (`ItemId` ASC) VISIBLE,
    CONSTRAINT `item`
    FOREIGN KEY (`ItemId`)
    REFERENCES `bookshop`.`item` (`ItemId`),
    CONSTRAINT `sup`
    FOREIGN KEY (`SupOrderId`)
    REFERENCES `bookshop`.`suporder` (`SupOrderId`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


    SET SQL_MODE=@OLD_SQL_MODE;
    SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
    SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
        -- Data for table `bookshop`.`user`
            -- -----------------------------------------------------
    START TRANSACTION;
    USE `bookshop`;
    INSERT INTO `bookshop`.`user` (`Username`, `Password`, `Role`) VALUES ('sumudu12', '1234Sumudu', 'Admin');
    INSERT INTO `bookshop`.`user` (`Username`, `Password`, `Role`) VALUES ('ishanka12', '234Ishanka', 'Employee');

    COMMIT;


-- -----------------------------------------------------
        -- Data for table `bookshop`.`employee`
            -- -----------------------------------------------------
    START TRANSACTION;
    USE `bookshop`;
    INSERT INTO `bookshop`.`employee` (`EmployeeId`, `Name`, `Address`, `PhoneNumber`, `Salary`, `Username`) VALUES ('E001', 'Ishanka', 'pinnaduwa', 0775468973, 30000, 'ishanka12');

    COMMIT;


-- -----------------------------------------------------
        -- Data for table `bookshop`.`attendance`
            -- -----------------------------------------------------
    START TRANSACTION;
    USE `bookshop`;
    INSERT INTO `bookshop`.`attendance` (`AttendanceId`, `Date`, `Time`, `EmployeeId`) VALUES ('A001', '2022-11-20', '09:34:21', 'E001');

    COMMIT;


-- -----------------------------------------------------
        -- Data for table `bookshop`.`customer`
            -- -----------------------------------------------------
    START TRANSACTION;
    USE `bookshop`;
    INSERT INTO `bookshop`.`customer` (`CusId`, `Name`, `PhoneNumber`, `JoinedDate`, `EmployeeId`) VALUES ('C001', 'isuru', 0702784657, '2022-05-18', 'E001');
    INSERT INTO `bookshop`.`customer` (`CusId`, `Name`, `PhoneNumber`, `JoinedDate`, `EmployeeId`) VALUES ('C002', 'ishan', 0712456498, '2020-06-04', 'E001');

    COMMIT;


-- -----------------------------------------------------
        -- Data for table `bookshop`.`cusorder`
            -- -----------------------------------------------------
    START TRANSACTION;
    USE `bookshop`;
    INSERT INTO `bookshop`.`cusorder` (`CusOrderId`, `Date`, `Time`, `CusId`, `EmployeeId`) VALUES ('O001', '2022-11-21', '09:34:21', 'C001', 'E001');
    INSERT INTO `bookshop`.`cusorder` (`CusOrderId`, `Date`, `Time`, `CusId`, `EmployeeId`) VALUES ('O002', '2022-10-22', '19:34:21', 'C002', 'E001');

    COMMIT;


-- -----------------------------------------------------
        -- Data for table `bookshop`.`offer`
            -- -----------------------------------------------------
    START TRANSACTION;
    USE `bookshop`;
    INSERT INTO `bookshop`.`offer` (`OfferId`, `Amount`, `StartedDate`, `EndedDate`) VALUES ('F001', 20, '2022-11-21', '2022-11-22');

    COMMIT;


-- -----------------------------------------------------
        -- Data for table `bookshop`.`item`
            -- -----------------------------------------------------
    START TRANSACTION;
    USE `bookshop`;
    INSERT INTO `bookshop`.`item` (`ItemId`, `BatchNumber`, `Description`, `BuyingUnitPrice`, `SellingUnitPrice`, `QuantityOnHand`, `OfferId`) VALUES ('I001', 1, 'crbook', 400, 450, 20, NULL);
    INSERT INTO `bookshop`.`item` (`ItemId`, `BatchNumber`, `Description`, `BuyingUnitPrice`, `SellingUnitPrice`, `QuantityOnHand`, `OfferId`) VALUES ('I002', 1, 'pencilbook', 80, 100, 30, 'F001');

    COMMIT;


-- -----------------------------------------------------
        -- Data for table `bookshop`.`cusorderdetails`
            -- -----------------------------------------------------
    START TRANSACTION;
    USE `bookshop`;
    INSERT INTO `bookshop`.`cusorderdetails` (`CusOrderId`, `ItemId`, `UnitPrice`, `Quantity`, `TotalPrice`) VALUES ('O001', 'I001', 450, 2, 900);
    INSERT INTO `bookshop`.`cusorderdetails` (`CusOrderId`, `ItemId`, `UnitPrice`, `Quantity`, `TotalPrice`) VALUES ('O001', 'I002', 100, 4, 400);
    INSERT INTO `bookshop`.`cusorderdetails` (`CusOrderId`, `ItemId`, `UnitPrice`, `Quantity`, `TotalPrice`) VALUES ('O002', 'I001', 450, 5, 2250);

    COMMIT;


-- -----------------------------------------------------
        -- Data for table `bookshop`.`reload/bill`
            -- -----------------------------------------------------
    START TRANSACTION;
    USE `bookshop`;
    INSERT INTO `bookshop`.`reload/bill` (`ReloadId`, `Phone Number`, `Amount`) VALUES ('R001', 0784568978, 100);

    COMMIT;


-- -----------------------------------------------------
        -- Data for table `bookshop`.`cusreloaddetails`
            -- -----------------------------------------------------
    START TRANSACTION;
    USE `bookshop`;
    INSERT INTO `bookshop`.`cusreloaddetails` (`CusOrderId`, `ReloadId`, `TotalPrice`) VALUES ('O001', 'R001', 100);
    INSERT INTO `bookshop`.`cusreloaddetails` (`CusOrderId`, `ReloadId`, `TotalPrice`) VALUES ('O002', 'R001', 100);

    COMMIT;


-- -----------------------------------------------------
        -- Data for table `bookshop`.`expenditure`
            -- -----------------------------------------------------
    START TRANSACTION;
    USE `bookshop`;
    INSERT INTO `bookshop`.`expenditure` (`ExpenditureId`, `Description`, `Amount`, `Date`, `Time`, `Username`) VALUES ('X001', 'electricity', 1500, '2022-11-21', '10:34:21', 'ishanka12');

    COMMIT;


-- -----------------------------------------------------
        -- Data for table `bookshop`.`supplier`
            -- -----------------------------------------------------
    START TRANSACTION;
    USE `bookshop`;
    INSERT INTO `bookshop`.`supplier` (`SupplierId`, `Name`, `Location`, `PhoneNumber`, `Username`) VALUES ('S001', 'kokila', 'galle', 0775601312, 'ishanka12');

    COMMIT;


-- -----------------------------------------------------
        -- Data for table `bookshop`.`suporder`
            -- -----------------------------------------------------
    START TRANSACTION;
    USE `bookshop`;
    INSERT INTO `bookshop`.`suporder` (`SupOrderId`, `Date`, `Time`, `SupplierId`, `Username`) VALUES ('O001', '2022-11-20', '20:44:21', 'S001', 'ishanka12');

    COMMIT;


-- -----------------------------------------------------
        -- Data for table `bookshop`.`payment`
            -- -----------------------------------------------------
    START TRANSACTION;
    USE `bookshop`;
    INSERT INTO `bookshop`.`payment` (`PaymentId`, `Amount`, `Date`, `Time`, `Username`, `SupOrderId`, `ExpenditureId`) VALUES ('P001', 1200, '2022-11-21', '09:34:21', 'ishanka12', 'O001', NULL);
    INSERT INTO `bookshop`.`payment` (`PaymentId`, `Amount`, `Date`, `Time`, `Username`, `SupOrderId`, `ExpenditureId`) VALUES ('P002', 800, '2022-11-14', '01:36:21', 'ishanka12', NULL, 'X001');

    COMMIT;


-- -----------------------------------------------------
        -- Data for table `bookshop`.`suporderdetails`
            -- -----------------------------------------------------
    START TRANSACTION;
    USE `bookshop`;
    INSERT INTO `bookshop`.`suporderdetails` (`SupOrderId`, `ItemId`, `UnitPrice`, `Quantity`, `TotalPrice`) VALUES ('O001', 'I001', 400, 10, 4000);

    COMMIT;

*/

}
