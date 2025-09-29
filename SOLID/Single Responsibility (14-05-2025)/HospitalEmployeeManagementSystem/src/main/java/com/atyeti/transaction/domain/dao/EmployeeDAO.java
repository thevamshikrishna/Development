package com.atyeti.transaction.domain.dao;

import com.atyeti.transaction.domain.Employee;

import java.sql.SQLException;

public class EmployeeDAO {

    public void saveEmployee(Employee employee) throws SQLException {
//        DatabaseConnectionManager connectionManager=DatabaseConnectionManager.getManagerInstance();
//        connectionManager.connect();
//        connectionManager.getConnectionObject().prepareStatement("insert into Emplo");

        System.out.println("Saved Employee to the database..."+employee);
    }

    public void deleteEmployee(Employee employee){
        System.out.println("Deleted Employee ....");
    }

}
