package lk.ijse.bookshop.bo.custom.impl;


/*

   ` Coded By Isuru Dulakshana
   ` Date     1/19/2023 9:03 AM

*/


import lk.ijse.bookshop.bo.custom.AdminEmployeeBO;
import lk.ijse.bookshop.dao.DAOFactory;
import lk.ijse.bookshop.dao.custom.EmployeeDAO;
import lk.ijse.bookshop.dto.EmployeeDTO;
import lk.ijse.bookshop.dto.ItemDTO;
import lk.ijse.bookshop.entity.Employee;
import lk.ijse.bookshop.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminEmployeeBOImpl implements AdminEmployeeBO {
    EmployeeDAO employeeDAO= (EmployeeDAO) DAOFactory.getDAOFactory().getDAOTypes(DAOFactory.DAOTypes.EMPLOYEE);
    @Override
    public ArrayList<EmployeeDTO> getAllDetails() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();
        ArrayList<Employee> employees = employeeDAO.getAll();
        for (Employee i : employees) {
            employeeDTOS.add(new EmployeeDTO(i.getEmployeeId(),i.getName(),i.getAddress(),i.getPhoneNumber(),i.getSalary(),i.getUserName()));

        }
        return employeeDTOS;
    }

    @Override
    public boolean deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(employeeId);
    }

    @Override
    public boolean updateSalary(EmployeeDTO employee) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(employee.getEmployeeId(), employee.getName(),employee.getAddress(),employee.getPhoneNumber(),
                employee.getSalary(),employee.getUserName()));
    }
}
