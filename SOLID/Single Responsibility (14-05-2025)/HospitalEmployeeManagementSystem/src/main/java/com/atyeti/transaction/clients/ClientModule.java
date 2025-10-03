package com.atyeti.transaction.clients;

import com.atyeti.transaction.domain.Employee;
import com.atyeti.transaction.domain.dao.EmployeeDAO;
import com.atyeti.transaction.reporting.EmployeeReportFormatter;
import com.atyeti.transaction.reporting.FormateType;

import java.sql.SQLException;

public class ClientModule {
    public static void main(String[] args) throws SQLException {
      //  ClientModule.hireNewEmployee(new Employee(1,"vamshi","Delivery",true));

        Employee peggy=new Employee(1,"Althaf","Devops",true);

        ClientModule.hireNewEmployee(peggy);
        ClientModule.terminateEmployee(peggy);
        ClientModule.printEmployeeReport(peggy,FormateType.CSV);

    }

    public static void hireNewEmployee(Employee employee) throws SQLException {
        EmployeeDAO employeeDAO=new EmployeeDAO();
        employeeDAO.saveEmployee(employee);
    }

    public static void terminateEmployee(Employee employee){
        EmployeeDAO employeeDAO=new EmployeeDAO();
        employeeDAO.deleteEmployee(employee);
    }

    public static void printEmployeeReport(Employee employee, FormateType formateType){
        EmployeeReportFormatter formatter=new EmployeeReportFormatter(employee,formateType);
        System.out.println(formatter.getFormattedEmployee());
    }

}
